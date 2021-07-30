package com.example.schoolorgonizer.callSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schoolorgonizer.R


class CallScheduleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return         inflater.inflate(R.layout.fragment_call_schedule, container, false)

    }

    companion object {
        const val TAG = "CallScheduleFragment"

    }
}