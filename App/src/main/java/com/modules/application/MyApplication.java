package com.modules.application;

import android.app.Application;

import com.modules.baselibraries.LogManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.init();
    }
}
