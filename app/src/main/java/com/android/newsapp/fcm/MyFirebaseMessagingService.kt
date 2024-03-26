package com.android.newsapp.fcm

import android.app.NotificationManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.android.newsapp.MyApplication
import com.android.newsapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        remoteMessage.data.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            sendNotification(it.title!!, it.body!!, it.icon, it.imageUrl)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }
    @Suppress("UNUSED_PARAMETER")
    private fun sendNotification(title: String, desc: String, icon: String?, imageUrl: Uri?) {

        applicationContext.sendNotification(
            title,
            desc,
            imageUrl
        )
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}

 fun Context.sendNotification(title: String,desc: String,imageUrl: Uri?){
     val mNotificationManager =
         getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
     val mBuilder = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
         .setSmallIcon(R.drawable.ic_baseline_notifications_24)
         .setPriority(NotificationCompat.PRIORITY_HIGH)
         .setContentTitle(title)
         .setContentText(desc)
         .setAutoCancel(true)

     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         mBuilder.setChannelId(MyApplication.CHANNEL_ID)
     }
     val notification = mBuilder.build()
     mNotificationManager.notify(MyApplication.NOTIFICATION_ID, notification)
 }