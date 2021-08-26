package com.example.schoolorgonizer.alarmсlock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.schoolorgonizer.databinding.FragmentRingtonBinding
import org.koin.core.component.KoinApiExtension


class RingtonFragment : Fragment() {

    private var binding: FragmentRingtonBinding? = null
    private var trek: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRingtonBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @KoinApiExtension
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding!!.mainSubmit.setOnClickListener {
            it.findNavController().popBackStack()

            var track = ""
            when {

                binding!!.trek1.isChecked -> {
                    track = AlarmService.TRACK1
                }
                binding!!.trek2.isChecked -> {
                    track = AlarmService.TRACK2
                }
                binding!!.trek3.isChecked -> {
                    track = AlarmService.TRACK3
                }
                binding!!.trek4.isChecked -> {
                    track = AlarmService.TRACK4
                }
            }
            setFragmentResult(TEST, Bundle().apply {
                putString(KEY1, track)
            })

        }

    }
    companion object{
        const val TEST = "TEST"
        const val KEY1 = "KEY1"
    }
}