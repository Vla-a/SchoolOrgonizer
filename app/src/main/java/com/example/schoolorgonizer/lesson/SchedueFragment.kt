package com.example.schoolorgonizer.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolorgonizer.databinding.FragmentScheduleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SchedueFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private val viewModels: SchedueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentScheduleBinding.inflate(inflater, container, false)
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
                binding.spinLesson.selectedItem.toString(),
                binding.spinDay.selectedItem.toString(),
                (binding.spinId.selectedItem.toString()).toInt()
            )
        }

        binding?.rvDay?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvDay?.adapter = messageAdapter

        viewModels.messageListLiveData.observe(this.viewLifecycleOwner, Observer {
            messageAdapter.submitList(it)
        })
    }

    fun clickListener(lesson: Message) {
        viewModels.deleteMessage(lesson)
    }
}