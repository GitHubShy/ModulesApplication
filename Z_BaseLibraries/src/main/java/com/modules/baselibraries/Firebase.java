package com.modules.baselibraries;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Firebase {

    private static FirebaseAnalytics mFirebaseAnalytics = null;

    public static void init(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public static void sendStatistics(String info, String msg) {
        Bundle bundle = new Bundle();
        bundle.putString(info, msg);
        mFirebaseAnalytics.logEvent("Event_Click_Fotoplay", bundle);
    }


}
