package com.byteshaft.hotelbooking;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

public class AppGlobals extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Locale.getDefault().getLanguage();
        changeLang(getApplicationContext(), Locale.getDefault().getLanguage());
        System.out.println(Locale.getDefault().getLanguage());
        FirebaseApp.initializeApp(getApplicationContext());
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseMessaging.getInstance().subscribeToTopic("all_users");
            }
        }, 3000);

    }

    public static void changeLang(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
