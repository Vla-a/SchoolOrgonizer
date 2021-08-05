package com.example.schoolorgonizer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.ActivityMainBinding.inflate
import com.example.schoolorgonizer.databinding.FragmentMainBinding
import java.util.*

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

        // Новости школы
        binding!!.btnNews.setOnClickListener {

        }
        //Будила
        binding!!.btnAlarmClock.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toAlarmClockFragment())

        }
        //Заметки
        binding!!.btnNotes.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.toNoteFragment())
        }
    }
    companion object {
        const val TAG = "MainFragment"

    }
}