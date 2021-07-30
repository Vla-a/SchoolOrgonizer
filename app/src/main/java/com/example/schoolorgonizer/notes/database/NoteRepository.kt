package com.example.schoolorgonizer.notes.database

import com.example.schoolorgonizer.notes.Notes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepository(
    private val noteDao: NoteDao
) {

    fun getMessagesList(): Flow<List<Notes>> =
        noteDao.getMessageList().map { messageEntities ->
            messageEntities.map { noteEntities ->
                Notes(noteEntities.message, noteEntities.date)
            }
        }

    suspend fun addMessages(note: NoteEntity) {
        noteDao.addMessage(note)

    }

    suspend fun deleteMessage(notes: Notes) {

        noteDao.deleteMessage(notes.entity())
    }

    private fun Notes.entity() = NoteEntity(this.message, this.date)
}