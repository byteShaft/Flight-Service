package com.byteshaft.hotelbooking.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.byteshaft.hotelbooking.MainActivity;
import com.byteshaft.hotelbooking.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class Service extends FirebaseMessagingService {
//    private String message;

    public static int APPOINTMENT_NOTIFICATION_ID = 101;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("DATA" + " good ", remoteMessage.getData().toString());
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey("offer")) {
            sendNotification(data.get("offer"), "Offer");
        }

    }

    private void sendNotification(String messageBody, String title) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, APPOINTMENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon_sdk_app)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources() ,R.mipmap.icon_sdk_app))
                .setTicker("offer")
                .setStyle(new NotificationCompat.BigTextStyle())
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(APPOINTMENT_NOTIFICATION_ID, notificationBuilder.build());
    }
}
