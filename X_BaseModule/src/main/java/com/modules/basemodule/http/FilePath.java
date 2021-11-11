package com.modules.basemodule.http;

import android.content.Context;
import android.os.Environment;

import com.modules.basemodule.BuildConfig;

import java.io.File;


public class FilePath {

    private static volatile FilePath mFilePath;

    public static String APP_EXTERNAL_ROOT;
    public static String APP_INTERNAL_ROOT;

    public static String SYS_EXTERNAL_ROOT;

    private FilePath(){}

    public static FilePath getInstance(Context context) {
        if (mFilePath == null) {
            synchronized (FilePath.class) {
                if (mFilePath == null) {

                    mFilePath = new FilePath();

                    APP_EXTERNAL_ROOT = context.getExternalFilesDir("").toString();
                    APP_INTERNAL_ROOT = context.getFilesDir().toString();

                    SYS_EXTERNAL_ROOT = Environment.getExternalStorageDirectory().toString();
                }
            }
        }
        return mFilePath;
    }


    public static void test() {
//        LogManager.d(APP_EXTERNAL_ROOT);
//        LogManager.d(APP_EXTERNAL_ROOT_2);
    }

    public static String getPicFolderPath() {
        return APP_EXTERNAL_ROOT+ File.separator+ BuildConfig.PICS_FOLDER;
    }
}
