package com.example.schoolorgonizer.callSchedule

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.schoolorgonizer.R
import com.example.schoolorgonizer.databinding.FragmentCallScheduleBinding


class CallScheduleFragment : Fragment() {

    private lateinit var binding: FragmentCallScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                binding = FragmentCallScheduleBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnR.setOnClickListener {
            it.findNavController().popBackStack()
        }

        ObjectAnimator.ofFloat(binding?.nvText1,View.TRANSLATION_X, 800f, 0f).apply {
            duration = 1000
        }.start()
        ObjectAnimator.ofFloat(binding?.nvText2, View.TRANSLATION_Y, 800f, 0f).apply {
            duration = 2000
        }.start()
        ObjectAnimator.ofFloat(binding?.nvText3,  View.TRANSLATION_X, 800f, 0f).apply {
            duration = 1000
        }.start()
        ObjectAnimator.ofFloat(binding?.nvText4, View.TRANSLATION_Y, 800f, 0f).apply {
            duration = 2000
        }.start()
        ObjectAnimator.ofFloat(binding?.nvText5, View.TRANSLATION_X, 800f, 0f).apply {
            duration = 1000
        }.start()
        ObjectAnimator.ofFloat(binding?.nvText6, View.TRANSLATION_Y, 800f, 0f).apply {
            duration = 2000
        }.start()
        ObjectAnimator.ofFloat(binding?.nvText7, View.TRANSLATION_X, 800f, 0f).apply {
            duration = 1000
        }.start()
    }
    companion object {
        const val TAG = "CallScheduleFragment"

    }
}