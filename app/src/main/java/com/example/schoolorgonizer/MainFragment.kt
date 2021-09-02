package com.example.schoolorgonizer

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentMainBinding

class MainFragment: Fragment() {


    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Расписание звонков
        binding?.btnCallSchedule?.setOnClickListener {

            it.findNavController().navigate(MainFragmentDirections.toCallScheduleFragment())

        }

        //Будила
        binding!!.btnAlarmClock.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toAlarmClockFragment())

        }
        //Заметки
        binding!!.btnNotes.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toNoteFragment())
        }

        //Расписание
        binding!!.btnLessonSchedule.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toLessonFragment())
        }

        binding!!.btnMap.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toMapFragment())
        }
    }
    companion object {
        const val TAG = "MainFragment"

    }
}
