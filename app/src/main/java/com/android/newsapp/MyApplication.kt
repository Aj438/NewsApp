package com.android.newsapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "News"
        private const val CHANNEL_NAME = "dicoding channel"
    }
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        notificationChannel()
    }
    private fun notificationChannel(){
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_NAME
            mNotificationManager.createNotificationChannel(channel)
        }
    }
}