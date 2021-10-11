package com.example.schoolorgonizer.notes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolorgonizer.databinding.FragmentNoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : Fragment() {

    private var binding: FragmentNoteBinding? = null
    private val viewModels: NoteFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteAdapter = NoteAdapter {
            clickListener(it)
        }

        binding!!.btnReturn.setOnClickListener {
            it.findNavController().popBackStack()

        }

        binding!!.btnGreta.setOnClickListener {
//            binding!!.editTextNote.visibility = View.VISIBLE
//            binding!!.btnNote.visibility = View.VISIBLE
//            binding!!.btnGreta.visibility = View.INVISIBLE
            it.findNavController().navigate(NoteFragmentDirections.toAddNoteFragment())
        }

        binding?.btnNote?.setOnClickListener {

            binding!!.btnGreta.visibility = View.VISIBLE
            binding!!.editTextNote.visibility = View.INVISIBLE
            binding!!.btnNote.visibility = View.INVISIBLE

//            with(binding!!.editTextNote) {
//                viewModels.addMessageToDatabase(text.toString(),)
//                setText("")
//
//            }
        }
        setFragmentResultListener("TEST1") { _, bundle ->
            val track = bundle.getString("KEY5")
            val date = bundle.getString("KEY6")
            viewModels.addMessageToDatabase( track.toString(),date.toString())
        }

        binding?.rvNote?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvNote?.adapter = noteAdapter

        viewModels.notesListLiveData.observe(this.viewLifecycleOwner, Observer {
            noteAdapter.submitList(it)
        })



    }

    private fun clickListener(notes: Notes) {

        viewModels.deleteMessage(notes)
    }
}