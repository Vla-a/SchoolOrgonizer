package com.example.schoolorgonizer.notes.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent

import com.example.schoolorgonizer.notes.Notes
import java.text.SimpleDateFormat
import java.util.*

class NotificationRepository(private val context: Context, private val alarmManager: AlarmManager) {

    fun setNotification(note: Notes) {
        val alarmTimeAtUTC = Date(note.date)

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmTimeAtUTC.time,
            makeIntent(note)
        )
    }


    private fun makeIntent(note: Notes): PendingIntent {
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.action = NotificationReceiver.ACTION
        intent.putExtra(NotificationReceiver.NOTIFICATION_KEY_NOTE_OWNER, note.date)
        return PendingIntent.getBroadcast(context, 0, intent, FLAG_UPDATE_CURRENT)
    }

    fun postponeNoteTimeByFiveMins(note: Notes): Notes {

        val time = Date()
        val calendar = Calendar.getInstance()
        calendar.time = time
        calendar.add(Calendar.SECOND, 5)
        return note.copy(date = calendar.timeInMillis.toString())
    }
}