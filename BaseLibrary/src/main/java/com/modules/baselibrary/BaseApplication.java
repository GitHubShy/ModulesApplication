package com.modules.baselibrary;


import androidx.multidex.MultiDexApplication;


public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        //Log
        LogProxy.init();

        //SharedPreferences
        SharedPreferencesProxy.init(this);

        //统计信息
//        StatisticsManager.init(this);


    }
}
