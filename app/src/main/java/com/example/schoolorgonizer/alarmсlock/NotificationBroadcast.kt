package com.example.schoolorgonizer.alarmсlock

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.schoolorgonizer.R


class NotificationBroadcast : BroadcastReceiver() {

    lateinit var  mp: MediaPlayer

    override fun onReceive(context: Context, intent: Intent) {

        //player
        mp = MediaPlayer.create(context, R.raw.trek)
        mp.start()

        // notification
//        try {
//            val contentIntent = PendingIntent.getActivity(
//                context, 0,
//                Intent(context, NotificationBroadcast::class.java), 0
//            )
//
//            val notificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                createChannel(notificationManager)
//            }
//
//            val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
//                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
//                .setContentText("Пора вставать!!!")
//                .setContentIntent(contentIntent)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setAutoCancel(true)
//            notificationManager.notify(0, builder.build())
//        } catch (e: Exception) {
//            println("===============================")
//            e.printStackTrace()
//            println("===============================")
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            onStartNitification(
                context,
                intent,
                intent.getIntExtra("TIME", 0),
                intent.getIntExtra("MINUTE", 0)
            )
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(notificationManager: NotificationManager) {
        if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL) == null) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL,
                NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "WrongConstant")

    fun onStartNitification(context: Context, intent: Intent, hour: Int, minutes: Int) {

        val notificationBuilder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setContentTitle(context.getString(R.string.alarm))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentText("$hour : $minutes")
                .addAction(makeDeleteAction(context))
//                .addAction(makePostponeAction(context))
                .setAutoCancel(true)

        val notificationIntent = Intent(context, NotificationBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationBuilder.setContentIntent(pendingIntent)

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = DESCRIPTION
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

    }

    private fun makeDeleteAction(context: Context): NotificationCompat.Action {
        val deleteIntent =
            Intent(context.applicationContext, NotificationBroadcast::class.java)
        deleteIntent.action = ACTION_DELETE
        val deletePendingIntent = PendingIntent.getService(
            context.applicationContext,
            1111,
            deleteIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Action.Builder(
            R.drawable.ic_baseline_delete_24,
            "Delete",
            deletePendingIntent
        ).build()


    }
//
//    private fun makePostponeAction(context: Context): NotificationCompat.Action {
//        val postponeIntent =
//            Intent(context.applicationContext, NotificationBroadcast::class.java)
//        postponeIntent.action = ACTION_POSTPONE
//        val postponePendingIntent = PendingIntent.getService(
//            context.applicationContext,
//            1112,
//            postponeIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        return NotificationCompat.Action.Builder(
//            R.drawable.ic_baseline_auto_delete_24,
//            "Postpone",
//            postponePendingIntent
//        ).build()
//    }

    companion object {

        private const val NOTIFICATION_CHANNEL = "PLANNER_APP_NOTIFICATION_CHANNEL"
        const val ACTION_POSTPONE = "PLANNER_APP_NOTIFICATION_POSTPONE"
        const val DESCRIPTION = "DESCRIPTION_TEXT"
        const val CHANNEL_ID = "CHANNEL_ID"
        const val ACTION_DELETE = "PLANNER_APP_NOTIFICATION_DELETE"
    }
}
