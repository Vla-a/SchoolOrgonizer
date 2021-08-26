package com.example.schoolorgonizer.alarmсlock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.schoolorgonizer.R
import com.example.schoolorgonizer.alarmсlock.RingtonFragment.Companion.KEY1
import com.example.schoolorgonizer.alarmсlock.RingtonFragment.Companion.TEST
import com.example.schoolorgonizer.databinding.FragmentAlarmClockBinding
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import java.util.*


@KoinApiExtension
class AlarmClockFragment : Fragment(), KoinComponent {

    private var binding: FragmentAlarmClockBinding? = null
    private var alarmDate: Calendar = Calendar.getInstance().apply { time = Date() }
    private val alarmManager: AlarmManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlarmClockBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(TEST) { key, bundle ->
            val track = bundle.getString(KEY1)
            binding!!.tvTex.text = track
        }

        binding!!.btnSingl.setOnClickListener {
            it.findNavController().navigate(AlarmClockFragmentDirections.toRingtonFragment())
        }

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding!!.btnDate.setOnClickListener {
            binding!!.calendarView.visibility = View.VISIBLE
            binding!!.timePicker.visibility = View.INVISIBLE
        }
        binding!!.btnTime.setOnClickListener {
            binding!!.calendarView.visibility = View.INVISIBLE
            binding!!.timePicker.visibility = View.VISIBLE
        }

        binding?.timePicker?.setIs24HourView(true)
        binding?.timePicker?.setOnTimeChangedListener { view, hourOfDay, minute ->
            alarmDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
            alarmDate.set(Calendar.MINUTE, minute)

        }

        binding?.btnSt?.setOnClickListener {

            startAlarmService()

            binding!!.calendarView.visibility = View.INVISIBLE
            binding!!.timePicker.visibility = View.INVISIBLE

            with(binding?.calendarView) {
                this?.year?.let { it1 -> alarmDate.set(Calendar.YEAR, it1) }
                this?.month?.let { it1 -> alarmDate.set(Calendar.MONTH, it1) }
                this?.dayOfMonth?.let { it1 -> alarmDate.set(Calendar.DAY_OF_MONTH, it1) }
            }

            Toast.makeText(
                context,
                "${getString(R.string.add_new_alarm)} ${binding?.calendarView?.dayOfMonth}" +
                        ".${binding?.calendarView?.month}.${binding?.calendarView?.year} " +
                        "в ${binding?.timePicker?.hour}:${binding?.timePicker?.minute}",
                Toast.LENGTH_SHORT
            ).show()

        }

        binding!!.btnStop.setOnClickListener {

            Toast.makeText( context,"${getString(R.string.stop_new_alarm)}",Toast.LENGTH_SHORT).show()

            alarmManager.cancel(
                PendingIntent.getService(
                    context?.applicationContext,
                    1,
                    Intent(activity, AlarmService::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun startAlarmService() {

        val myAnotherService = Intent(context, AlarmService::class.java).apply {
            putExtra(TRACK, binding?.tvTex?.text)
        }
        val pendingIntent = PendingIntent.getService(
            context?.applicationContext,
            1,
            myAnotherService,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmDate.timeInMillis,
            1000,
            pendingIntent
        )
    }
    companion object{
        const val TRACK = "TRACK"
    }
}

