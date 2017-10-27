package com.byteshaft.hotelbooking.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.byteshaft.hotelbooking.MainActivity;
import com.byteshaft.hotelbooking.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;



public class Service extends FirebaseMessagingService {
//    private String message;

    public static int APPOINTMENT_NOTIFICATION_ID = 101;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("DATA" + " good ", remoteMessage.getData().toString());
        Log.i("DATA" + " boolean ", String.valueOf(remoteMessage.getData().containsKey("status")));
    }

    private void sendNotification(String messageBody, String doctorName, String appointmentReason) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, APPOINTMENT_NOTIFICATION_ID, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher_round)
                .showImageOnLoading(R.mipmap.ic_launcher_round)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(false)
                .cacheOnDisc(false).considerExifParams(true).build();


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker(appointmentReason)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setContentTitle(doctorName)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(APPOINTMENT_NOTIFICATION_ID, notificationBuilder.build());
    }
}
