package com.blockmay.brooksdistributor.distribution_data

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.blockmay.brooksdistributor.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "com.mings.pi2p.ui.notifications"

class MyFirebaseMessagingService: FirebaseMessagingService() {

    private val tag = "FirebaseMessagingService"


//    override fun onMessageReceived(message: RemoteMessage) {
//
//        if (message.notification != null){
//            generateNotification(message.notification!!.title!!, message.notification!!.body!!)
//        }
//    }
//
//    @SuppressLint("RemoteViewLayout")
//    fun getRemoteView(title: String, description: String): RemoteViews {
//
//        val remoteViews = RemoteViews("com.mings.pi2p.ui.notifications", R.layout.custom_notification_layout)
//
//        remoteViews.setTextViewText(R.id.title_txt, title)
//        remoteViews.setTextViewText(R.id.description_txt, description)
//
//        return remoteViews
//
//    }


    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        try {

            if (remoteMessage.notification != null) {
                showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)

            } else {
                showNotification(remoteMessage.data["title"], remoteMessage.data["message"])
            }

        } catch (e: Exception) {
            println("$tag error -->${e.localizedMessage}")
        }
    }

//    fun generateNotification(title: String, description: String){
//
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
//
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setAutoCancel(false)
//            .setVibrate(longArrayOf (1000,1000,1000,1000))
//            .setOnlyAlertOnce(true)
//            .setContentIntent(pendingIntent)
//
//        builder = builder.setContent(getRemoteView(title, description))
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//
//        notificationManager.notify(0,builder.build())
//    }

    private fun showNotification(
        title: String?,
        body: String?,
    ) {
        val intent = Intent(this, DistributionDataActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("body", body)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = getString(R.string.channel_id)
        val channelName = getString(R.string.channel_name)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupNotificationChannels(channelId, channelName, notificationManager)
        }

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.app_icon)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        notificationManager.notify(0, notificationBuilder.build())
    }
//
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupNotificationChannels(
        channelId: String,
        channelName: String,
        notificationManager: NotificationManager
    ) {

        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.enableLights(true)
        channel.lightColor = Color.BLUE
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)
    }





}
