package com.modules.baselibraries;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.tencent.mmkv.MMKV;

public class SharedPreferencesManager {

    public static String MMKV_ROOT_DIR;
    public static MMKV mmkv;

    public static void init(Context context) {
        MMKV_ROOT_DIR = MMKV.initialize(context);
        mmkv = MMKV.defaultMMKV();
        LogManager.d(MMKV_ROOT_DIR);
    }

    /**
     * 存放放字符串到默认sp
     *
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        mmkv.encode(key, value);
    }

    /**
     * 从默认sp获取字符串
     *
     * @param key
     * @param defaultValue
     */
    public static String getString(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    /**
     * 存放放字符串到指定sp
     *
     * @param sp    sp id.如果为空，存入默认的sp
     * @param key
     * @param value
     */
    public static void putString(String sp, String key, String value) {
        if (StringUtils.isEmpty(sp)) {
            putString(key, value);
        } else {
            MMKV mmkv = MMKV.mmkvWithID(sp);
            mmkv.encode(key, value);
        }
    }

    /**
     * @param sp
     * @param key
     * @param value
     * @return
     */
    public static String getString(String sp, String key, String value) {
        if (StringUtils.isEmpty(sp)) {
            return getString(key, value);
        } else {
            return MMKV.mmkvWithID(sp).decodeString(key, value);
        }
    }

    /**
     * 存放int到默认sp
     *
     * @param key
     * @param value
     */
    public static void putInt(String key, int value) {
        mmkv.encode(key, value);
    }

    /**
     * 从默认sp获取Int
     *
     * @param key
     * @param defaultValue
     */
    public static int getInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    /**
     * 存放int串到指定sp
     *
     * @param sp    sp id.如果为空，存入默认的sp
     * @param key
     * @param value
     */
    public static void putInt(String sp, String key, int value) {
        if (StringUtils.isEmpty(sp)) {
            putInt(key, value);
        } else {
            MMKV mmkv = MMKV.mmkvWithID(sp);
            mmkv.encode(key, value);
        }
    }

    /**
     * @param sp           sp的id,如果传null，则总默认的sp中查找
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String sp, String key, int defaultValue) {
        if (StringUtils.isEmpty(sp)) {
            return getInt(key, defaultValue);
        }
        return MMKV.mmkvWithID(sp).decodeInt(key, defaultValue);
    }

    /**
     * 存放boolean到默认sp
     *
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        mmkv.encode(key, value);
    }

    /**
     * 从默认sp获取Boolean
     *
     * @param key
     * @param defaultValue
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    /**放入一个boolean到指定sp中
     * @param sp 传空的话写入默认sp中
     * @param key
     * @param value
     */
    public static void putBoolean(String sp, String key, boolean value) {
        if (StringUtils.isEmpty(sp)) {
            putBoolean(key, value);
        } else {
            putBoolean(sp, key, value);
        }
    }

    /**获取一个boolean
     * @param sp 传空的话，从默认sp中查找
     * @param key
     * @param value
     * @return
     */
    public static boolean getBoolean(String sp, String key, boolean value) {
        if (StringUtils.isEmpty(sp)) {
            return getBoolean(key, value);
        } else {
            return getBoolean(sp, key, value);
        }
    }


}
