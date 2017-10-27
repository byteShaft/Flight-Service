package com.byteshaft.hotelbooking;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by s9iper1 on 10/27/17.
 */

public class AppGlobals extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseMessaging.getInstance().subscribeToTopic("all_users");
            }
        }, 3000);

    }
}
