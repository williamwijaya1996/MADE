package com.example.movieandtvshowcatalogue.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.movieandtvshowcatalogue.MainActivity;
import com.example.movieandtvshowcatalogue.R;

public class NotificationReceiver extends BroadcastReceiver {
    NotificationCompat.Builder mBuilder;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "daily_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_notifications_white_48px))
                .setContentTitle("title")
                .setContentText("kangen")
                .setSubText("yihui")
                .setAutoCancel(true);

        // Untuk android Oreo ke atas perlu menambahkan notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(100, notification);
        }
    }
}
