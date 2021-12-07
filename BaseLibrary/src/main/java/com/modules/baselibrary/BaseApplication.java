package com.modules.baselibrary;


import android.app.Application;


public class BaseApplication extends Application {


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
