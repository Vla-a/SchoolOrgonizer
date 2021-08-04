package com.example.schoolorgonizer.alarmсlock

import android.app.AlarmManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schoolorgonizer.databinding.FragmentAlarmClockBinding
import com.example.schoolorgonizer.databinding.FragmentRingtonBinding
import org.koin.android.ext.android.inject
import java.util.*

class RingtonFragment: Fragment() {

    private var binding: FragmentRingtonBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRingtonBinding.inflate(inflater, container, false)
        return binding?.root
    }
}