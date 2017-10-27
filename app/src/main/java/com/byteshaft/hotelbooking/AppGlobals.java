package com.byteshaft.hotelbooking;

import android.app.Application;

/**
 * Created by s9iper1 on 10/24/17.
 */

public class AppGlobals extends Application {

    private static final String MARKER = "140500";
    private static final String TRAVEL_PAYOUTS_TOKEN = "538bbfc1878cc798b1d2920b793a3822";
    private final static String SDK_HOST = "www.travel-api.pw";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
