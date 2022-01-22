package com.modules.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.modules.study.service.MyService;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("shy","自启动了 ！！！！！");
//            Intent newIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            Intent newIntent2 = new Intent(context, MyService.class);  // 要启动的Activity
            //1.如果自启动APP，参数为需要自动启动的应用包名
            //Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
            //这句话必须加上才能开机自动运行app的界面
//            newIntent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //2.如果自启动Activity
//            context.startActivity(newIntent);
            //3.如果自启动服务
            context.startService(newIntent2);
        }

    }
}
