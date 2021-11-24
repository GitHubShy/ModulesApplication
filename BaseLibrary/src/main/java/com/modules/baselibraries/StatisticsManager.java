package com.modules.baselibraries;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class StatisticsManager {

    private static FirebaseAnalytics mFirebaseAnalytics = null;

    public static void init(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public static void sendStatistics(String eventName, String key, String value) {
        if (mFirebaseAnalytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(key, value);
            mFirebaseAnalytics.logEvent(eventName, bundle);
        }

    }
}
