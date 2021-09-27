package com.example.schoolorgonizer.notes.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.schoolorgonizer.MainActivity
import com.example.schoolorgonizer.R
import com.example.schoolorgonizer.alarmсlock.AlarmClockFragment
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
    private var noteId: String = ""


    override fun onBind(p0: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
           noteId = it.getStringExtra(NotificationReceiver.NOTIFICATION_KEY_NOTE_ID).toString()
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            when (it.action) {
                NotificationReceiver.ACTION_DELETE -> {
                    GlobalScope.launch {
//                 notesRepository.deleteMessage(Notes(noteId,))
                    }
                    notificationManager.cancel(0)
                }
                NotificationReceiver.ACTION_POSTPONE -> {
                    GlobalScope.launch {

                    }
                    notificationManager.cancel(0)
                }
                else -> Unit
            }
        }
        stopSelf()
        return START_NOT_STICKY
    }


}