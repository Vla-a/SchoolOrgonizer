package com.example.schoolorgonizer.notes

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolorgonizer.MySuperApp
import com.example.schoolorgonizer.databinding.FragmentNoteBinding


import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
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
            clickListener(it) }

binding.btnGreta.setOnClickListener {
    binding.editTextNote.visibility = View.VISIBLE
    binding.btnNote.visibility = View.VISIBLE
    binding.btnGreta.visibility = View.INVISIBLE
}

        binding?.btnNote?.setOnClickListener {

            binding.btnGreta.visibility = View.VISIBLE
            binding.editTextNote.visibility = View.INVISIBLE
            binding.btnNote.visibility = View.INVISIBLE

            with(binding.editTextNote) {
                viewModels.addMessageToDatabase(text.toString())
                setText("")

}
        }


        binding?.rvNote?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvNote?.adapter = noteAdapter

        viewModels.notesListLiveData.observe(this.viewLifecycleOwner, Observer {
            noteAdapter.submitList(it)
        })

////        Создание раздилительной полоски
//        val horizontalDecoration =
//            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//        horizontalDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_divider)!!)
//        binding.rvNote.addItemDecoration(horizontalDecoration)
   }

    fun clickListener(notes: Notes) {

        viewModels.deleteMessage(notes)
    }

    companion object {
        const val TAG = "NoteFragment"

    }
}