package com.example.schoolorgonizer.alarmсlock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.schoolorgonizer.MainActivity
import com.example.schoolorgonizer.R
import kotlinx.serialization.json.Json.Default.context

class AlarmService : Service() {

    lateinit var  mp: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        mp = MediaPlayer.create(this,R.raw.carlson)
        mp.start()

        val s = intent?.getStringExtra("TREK") as String

        val notificationBuilder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setContentTitle(getString(R.string.alarm))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentText(getString(R.string.alarm))
                .setColor(Color.GREEN)
                .setAutoCancel(true)


//                .setContentText("$hour : $minutes")



        val notificationIntent = Intent(baseContext,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = getString(R.string.alarm)
            val channel = NotificationChannel(
                CHANNEL_ID,
                NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
        const val NOTIFICATION_CHANNEL = "NOTIFICATION_CHANNEL"
    }

}