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
import com.example.schoolorgonizer.R
import com.example.schoolorgonizer.databinding.FragmentAlarmClockBinding
import org.koin.android.ext.android.inject
import java.util.*

class AlarmClockFragment :Fragment() {

    private  var binding: FragmentAlarmClockBinding? = null
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



        binding?.timePicker?.setIs24HourView(true)

        binding?.timePicker?.setOnTimeChangedListener { view, hourOfDay, minute ->
            alarmDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
            alarmDate.set(Calendar.MINUTE, minute)

        }

        binding?.btnSt?.setOnClickListener {
            with(binding?.calendarView) {
                this?.year?.let { it1 -> alarmDate.set(Calendar.YEAR, it1) }
                this?.month?.let { it1 -> alarmDate.set(Calendar.MONTH, it1) }
                this?.dayOfMonth?.let { it1 -> alarmDate.set(Calendar.DAY_OF_MONTH, it1) }
            }

            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                alarmDate.timeInMillis,
                PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(context?.applicationContext, NotificationBroadcast::class.java).apply {
                        putExtra("TIME", binding?.timePicker?.hour)
                        putExtra("MINUTE", binding?.timePicker?.minute)
                    },
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
//            Toast.makeText(this, getString(R.string.add_new_alarm).toInt(), Toast.LENGTH_SHORT).show()

        }

    }

    companion object {
        const val TAG = "AlarmClockAFragment"

    }
}