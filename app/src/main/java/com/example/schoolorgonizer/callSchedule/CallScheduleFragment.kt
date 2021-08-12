package com.example.schoolorgonizer.callSchedule

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentCallScheduleBinding


class CallScheduleFragment : Fragment() {

    private var binding: FragmentCallScheduleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                binding = FragmentCallScheduleBinding.inflate(inflater, container, false)
        return binding?.root

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnR.setOnClickListener {
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




}