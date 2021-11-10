package com.modules.baselibraries;

import android.app.Application;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.init();
    }
}
