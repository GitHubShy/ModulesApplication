package com.modules.baselibraries;


import androidx.multidex.MultiDexApplication;


public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        //Log
        LogManager.init();

        //SharedPreferences
        SharedPreferencesManager.init(this);

        //统计信息
//        StatisticsManager.init(this);


    }
}
