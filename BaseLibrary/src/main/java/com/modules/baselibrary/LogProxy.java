package com.modules.baselibrary;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class LogProxy {

    public static String DEFAULT_LOG_TAG = "shy";

    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void d(@Nullable Object object) {
        Logger.d(object);
    }

    public static void e(@NonNull String message){
        Logger.e(message);
    }

    /** 简单输出log
     * @param message
     */
    public static void e_simple(@NonNull String message){
        Log.e("shy",message);
    }
}
