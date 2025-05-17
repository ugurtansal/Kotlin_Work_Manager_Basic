package com.ugurtansal.work_manager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorkerNotification(context: Context,workerParameters: WorkerParameters): Worker(context,workerParameters) {
    override fun doWork(): Result {
        createNotification()
        return Result.success()
    }

    fun createNotification() {
        val builder: NotificationCompat.Builder
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(applicationContext,MainActivity::class.java)
        val toGoIntent = PendingIntent.getActivity(applicationContext,1,intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //OS version >= Oreo
            //After Oreo version
            val channelId = "kanalId"
            val channelName = "kanalAd"
            val channelExplanation = "kanalAciklama"
            val channelPriority = NotificationManager.IMPORTANCE_HIGH

            var channel: NotificationChannel? = notificationManager.getNotificationChannel(channelId)

            if(channel == null){
                channel = NotificationChannel(channelId,channelName,channelPriority)
                channel.description = channelExplanation
                notificationManager.createNotificationChannel(channel)
            }

            builder = NotificationCompat.Builder(applicationContext,channelId)

            builder.setContentTitle("Title")
                .setContentText("Content")
                .setSmallIcon(R.drawable.notification_img)
                .setContentIntent(toGoIntent)
                .setAutoCancel(true)
        }else{
            //OS version < Oreo  =Before Oreo version
            builder = NotificationCompat.Builder(applicationContext)

            builder.setContentTitle("Title")
                .setContentText("Content")
                .setSmallIcon(R.drawable.notification_img)
                .setContentIntent(toGoIntent)
                .setAutoCancel(true)
                .priority = Notification.PRIORITY_HIGH
        }

        notificationManager.notify(1,builder.build())

    }
}