package com.modules.study.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.modules.baselibrary.LogProxy;
import com.socks.library.KLog;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    // 通过静态方法创建ScheduledExecutorService的实例
    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(1);

    private Handler mHandler;

    private Runnable mRunnable;

    private ThreadFactory threadFactory = r -> new Thread() {
        @Override
        public void run() {
            r.run();
        }
    };

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogProxy.d("oncreate");
        mHandler = new Handler(Looper.myLooper());
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("shy","I am a runnable");
            }
        };
        mHandler.postDelayed(mRunnable,5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogProxy.d("onStartCommand"+Thread.currentThread().getName());
//        mHandler.removeCallbacksAndMessages(null);
//        // 延时任务
//        thread = threadFactory.newThread(new Runnable() {
//            @Override
//            public void run() {
//                LogProxy.d("" +
//                        "mScheduledExecutorService");
//            }
//        });
//        mScheduledExecutorService.schedule(thread, 10, TimeUnit.SECONDS);
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