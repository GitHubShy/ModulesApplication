package com.modules.study.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.modules.baselibrary.LogProxy;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogProxy.d("oncreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogProxy.d("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogProxy.d("onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}