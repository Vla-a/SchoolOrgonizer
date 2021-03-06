package com.example.schoolorgonizer.lessonSchedule.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolorgonizer.R
import com.example.schoolorgonizer.databinding.FragmentScheduleBinding
import com.example.schoolorgonizer.lessonSchedule.Lessons
import com.example.schoolorgonizer.lessonSchedule.MessageAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ScheduleFragment : Fragment() {

    private var binding: FragmentScheduleBinding? = null
    private val viewModels: ScheduleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding = FragmentScheduleBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val messageAdapter = MessageAdapter {
            clickListener(it)
        }
        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        binding?.btnMessage?.setOnClickListener {

            viewModels.addMessageToDatabase(
                binding!!.spinLesson.selectedItem.toString(),
                binding!!.spinDay.selectedItem.toString(),
                (binding!!.spinId.selectedItem.toString()).toInt()
            )
        }

        binding?.rvDay?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvDay?.adapter = messageAdapter

        viewModels.lessonsListLiveData.observe(this.viewLifecycleOwner, Observer {
            messageAdapter.submitList(it)
        })
        val horizontalDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        horizontalDecoration.setDrawable(context?.applicationContext?.let {
            ContextCompat.getDrawable(
                it, R.drawable.line_divider)
        }!!)
        binding!!.rvDay.addItemDecoration(horizontalDecoration)
    }

    private fun clickListener(lesson: Lessons) {
        viewModels.deleteMessage(lesson)
    }
}