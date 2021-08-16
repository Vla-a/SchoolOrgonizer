package com.example.schoolorgonizer.myMap

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schoolorgonizer.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var binding: FragmentMapBinding? = null

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

        binding!!.mapView.onCreate(savedInstanceState)
        binding!!.mapView.getMapAsync(this)
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
        // Add a marker in Minsk and move the camera
        val minsk = LatLng(53.9, 27.5667)
        mMap.addMarker(MarkerOptions().position(minsk).title("Marker in Minsk"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(minsk))
    }

}
//class MapFragment : Fragment() {
//    var binding: LocationFragmentBinding? = null
//    var mMapView: MapView? = null
//    private var googleMap: GoogleMap? = null
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = LocationFragmentBinding.inflate(inflater, container, false)
//        mMapView = binding?.mapView as MapView
//        mMapView!!.onCreate(savedInstanceState)
//        mMapView!!.onResume() // needed to get the map to display immediately
//        try {
//            MapsInitializer.initialize(activity?.applicationContext)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        mMapView!!.getMapAsync { mMap ->
//            googleMap = mMap
//
//            val minsk = LatLng(53.9, 27.5667)
//        mMap.addMarker(MarkerOptions().position(minsk).title("Marker in Minsk"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(minsk))
//        }
//        return binding?.root
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mMapView!!.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mMapView!!.onPause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mMapView!!.onDestroy()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mMapView!!.onLowMemory()
//    }
//}