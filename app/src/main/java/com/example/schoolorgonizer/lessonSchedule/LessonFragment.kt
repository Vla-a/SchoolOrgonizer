package com.example.schoolorgonizer.lessonSchedule

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentLessonBinding

class LessonFragment : Fragment() {


    private var binding: FragmentLessonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding?.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnMonday.setOnClickListener {
            it.findNavController().navigate(LessonFragmentDirections.toDayScheduleFragment())
            setFragmentResult("DAY", Bundle().apply {
                putString("KEY2", binding!!.btnMonday.text.toString())
            })
        }

        binding!!.btnTuesday.setOnClickListener {
            it.findNavController().navigate(LessonFragmentDirections.toDayScheduleFragment())
            setFragmentResult("DAY", Bundle().apply {
                putString("KEY2", binding!!.btnTuesday.text.toString())
            })
        }

        binding!!.btnWednesday.setOnClickListener {
            it.findNavController().navigate(LessonFragmentDirections.toDayScheduleFragment())
            setFragmentResult("DAY", Bundle().apply {
                putString("KEY2", binding!!.btnWednesday.text.toString())
            })
        }

        binding!!.btnThursday.setOnClickListener {
            it.findNavController().navigate(LessonFragmentDirections.toDayScheduleFragment())
            setFragmentResult("DAY", Bundle().apply {
                putString("KEY2", binding!!.btnThursday.text.toString())
            })
        }

        binding!!.btnFriday.setOnClickListener {
            it.findNavController().navigate(LessonFragmentDirections.toDayScheduleFragment())
            setFragmentResult("DAY", Bundle().apply {
                putString("KEY2", binding!!.btnFriday.text.toString())
            })
        }

        binding!!.btnEdit.setOnClickListener {
            it.findNavController().navigate(LessonFragmentDirections.toScheduleFragment())
        }

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}