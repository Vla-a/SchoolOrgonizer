package com.example.schoolorgonizer.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.schoolorgonizer.notes.database.NoteEntity
import com.example.schoolorgonizer.notes.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NoteFragmentViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {


    val notesListLiveData: LiveData<List<Notes>> =
        noteRepository.getMessagesList().asLiveData()

    fun addMessageToDatabase(message: String, date: String) {

        val newMessage = NoteEntity(
            message, date
//            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT).format(System.currentTimeMillis())
        )

        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addMessages(newMessage)
        }
    }

    fun deleteMessage(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteMessage(notes)
        }
    }
}


