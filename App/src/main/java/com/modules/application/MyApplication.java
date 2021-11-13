package com.modules.application;

import android.app.Application;

import com.modules.baselibraries.BaseApplication;
import com.modules.baselibraries.LogManager;
import com.modules.basemodule.room.database.AppRoomDatabase;
import com.modules.basemodule.room.entity.Memory;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Memory m = new Memory("a","b");
        AppRoomDatabase.getInstance(this).getMemoryDao().insert(m);
    }
}
