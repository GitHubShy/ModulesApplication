package com.modules.basemodule;

import androidx.annotation.NonNull;


public class Configuration {

    //网络请求相关
    public static final String ALIYUN_DOMAIN_CDN_ROOT = BuildConfig.ALIYUN_REMOTE_CDN_ROOT;
    public static final String REMOTE_CONFIG_JSON = BuildConfig.REMOTE_CONFIG_JSON;

    //路径相关
    public static final String EXTERNAL_DIR_ROOT = BuildConfig.APP_EXTERNAL_ROOT_DIR;

    //key相关
    public static final String SHARED_PREFERENCE_UPDATE_KEY = "update";
    public static final String SHARED_PREFERENCE_HISTORY_KEY = "history";

    @NonNull
    @Override
    public String toString() {
        return "EXTERNAL_DIR_ROOT=" + EXTERNAL_DIR_ROOT;
    }
}
