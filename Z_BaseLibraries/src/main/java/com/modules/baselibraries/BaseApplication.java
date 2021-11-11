package com.modules.baselibraries;

import android.app.Application;
import android.util.Log;

import com.modules.testjni.Test;
import com.tencent.mmkv.MMKV;


public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Log
        LogManager.init();

        //统计信息
        StatisticsManager.init(this);

        //SharedPreferences
        SharedPreferencesManager.init(this);

    }
}
