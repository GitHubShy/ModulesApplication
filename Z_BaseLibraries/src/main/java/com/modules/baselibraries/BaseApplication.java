package com.modules.baselibraries;

import android.app.Application;

import com.modules.testjni.Test;


public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.init();
        LogManager.d(new Test().stringFromJNI());
    }
}
