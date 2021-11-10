package com.modules.basemodule;

import androidx.annotation.NonNull;


public class Configuration {

    public static final String EXTERNAL_DIR_ROOT = BuildConfig.APP_EXTERNAL_ROOT_DIR;

    @NonNull
    @Override
    public String toString() {
        return "EXTERNAL_DIR_ROOT=" + EXTERNAL_DIR_ROOT;
    }
}
