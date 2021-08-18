package com.example.schoolorgonizer.myMap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentMapBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var binding: FragmentMapBinding? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var myLocation: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding!!.mapView.onCreate(savedInstanceState)
        binding!!.mapView.getMapAsync(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                myLocation = LatLng(p0.lastLocation.latitude, p0.lastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(myLocation!!).title("My location"))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16f))

            }

        }
        binding!!.btnMap.setOnClickListener {
            locationWizardry()
        }
    }

    override fun onResume() {
        super.onResume()
        binding!!.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding!!.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding!!.mapView.onDestroy()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

    }

    @SuppressLint("MissingPermission")
    private fun locationWizardry() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        //Initially, get last known location. We can refine this estimate later
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                myLocation = LatLng(location.latitude, location.longitude)
            }
        }

        //now for receiving constant location updates:
        createLocRequest()
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
        //actually start listening for updates: See on Resume(). It's done there so that conveniently we can stop listening in onPause
    }

    private fun createLocRequest() {
        locationRequest = LocationRequest.create()
        locationRequest.interval = 100000 //time in ms; every ~10 seconds
        locationRequest.fastestInterval = 500
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

}