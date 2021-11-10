package com.modules.baselibraries;

import android.app.Application;

import com.modules.testjni.Test;


public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Log
        LogManager.init();

        //统计信息
        StatisticsManager.init(getApplicationContext());

    }
}
