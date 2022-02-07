package com.example.schoolorgonizer.notes.database

import com.example.schoolorgonizer.notes.Notes
import com.example.schoolorgonizer.notes.notification.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteRepository(
    private val noteDao: NoteDao,
    private val notificationRepository: NotificationRepository
) {

    fun getMessagesList(): Flow<List<Notes>> =
        noteDao.getMessageList().map { messageEntities ->
            messageEntities.map { noteEntities ->
                Notes(  noteEntities.message, noteEntities.date)
            }
        }

     fun addMessages(note: NoteEntity) {

        noteDao.addMessage(note)
    }

    suspend fun deleteMessage(notes: Notes) {

        noteDao.deleteMessage(notes.entity())
    }

     fun postponeNoteById(note: Notes) {

                val postponedNote = notificationRepository.postponeNoteTimeByFiveMins(note)

                notificationRepository.setNotification(postponedNote)

        }


   private fun Notes.entity() = NoteEntity( this.message, this.date)
}