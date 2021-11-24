package com.modules.baselibraries;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class LogManager {

    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void d(@Nullable Object object) {
        Logger.d(object);
    }

    public static void e(@NonNull String message){
        Logger.e(message);
    }
}
