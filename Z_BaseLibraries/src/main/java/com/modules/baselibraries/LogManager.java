package com.modules.baselibraries;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class LogManager {

    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
