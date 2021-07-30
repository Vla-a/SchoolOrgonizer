package com.example.schoolorgonizer.notes

import androidx.lifecycle.*
import com.example.schoolorgonizer.notes.database.NoteEntity
import com.example.schoolorgonizer.notes.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.text.SimpleDateFormat
import java.util.*

class NoteFragmentViewModel (
    private val noteRepository: NoteRepository
) : ViewModel(), KoinComponent {


    val notesListLiveData: LiveData<List<Notes>> =
        noteRepository.getMessagesList().asLiveData()

    fun addMessageToDatabase(message: String) {

        val newMessage = NoteEntity(
            message,
            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT).format(System.currentTimeMillis())
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


