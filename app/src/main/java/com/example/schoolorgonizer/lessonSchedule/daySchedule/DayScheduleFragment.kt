package com.example.schoolorgonizer.lessonSchedule.daySchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolorgonizer.databinding.FragmentDayScheduleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class DayScheduleFragment : Fragment(), KoinComponent {

    private lateinit var binding: FragmentDayScheduleBinding
    private val viewModels: DayScheduleViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDayScheduleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        setFragmentResultListener("DAY") { key, bundle ->
            binding.string.text = bundle.getString("KEY2")
            viewModels.getList( binding.string.text.toString())
        }


        val dayAdapter = DayAdapter()

        binding?.rvMessage?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvMessage?.adapter = dayAdapter

        viewModels.lessonsListLiveData.observe(this.viewLifecycleOwner, Observer {
            dayAdapter.submitList(it)
        })


    }

}