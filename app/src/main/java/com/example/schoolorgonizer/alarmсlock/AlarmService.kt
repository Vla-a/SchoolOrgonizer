package com.example.schoolorgonizer.alarmсlock

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi

class AlarmService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        when (intent.action) {

            NotificationBroadcast.ACTION_DELETE -> {
                Toast.makeText(applicationContext, "Будильник oтменен", Toast.LENGTH_SHORT).show()
                notificationManager.cancel(0)

            }
        }
        stopSelf()
        return Service.START_NOT_STICKY

    }

}