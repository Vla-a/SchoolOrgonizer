package com.example.schoolorgonizer.notes.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.schoolorgonizer.notes.Notes
import com.example.schoolorgonizer.notes.database.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
class NoteService : Service(), KoinComponent {

    private val notesRepository: NoteRepository by inject()
    private var noteId: Long = -1
    private var mesage: String? = ""
    private var date: String? = ""

    override fun onBind(p0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent!!.let {
            mesage = it.getStringExtra(NotificationReceiver.NOTIFICATION_KEY_NOTE_TEXT)
            date = it.getStringExtra(NotificationReceiver.NOTIFICATION_KEY_NOTE_OWNER)
            val note = mesage?.let { it1 -> date?.let { it2 -> Notes(it1, it2) } }


            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            when (it.action) {
                NotificationReceiver.ACTION_DELETE -> {
                    GlobalScope.launch {

                        note?.let { it1 -> notesRepository.deleteMessage(it1) }
                    }
                   notificationManager.cancel(0)
                }

                NotificationReceiver.ACTION_POSTPONE -> {
                    GlobalScope.launch {

                        note?.let { it1 -> notesRepository.postponeNoteById(it1) }

                    }
                    notificationManager.cancel(0)
                }
                else -> Unit
            }
        }

        return START_NOT_STICKY
    }


}