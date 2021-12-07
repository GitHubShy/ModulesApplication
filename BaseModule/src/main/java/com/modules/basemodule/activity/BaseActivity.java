package com.modules.basemodule.activity;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.Locale;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initListeners();
        initData();
    }

    protected abstract void initViews();
    protected abstract void initListeners();
    protected abstract void initData();

    /**
     * 获取系统语言
     */
    protected Locale getSystemLanguage(){
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList locales = Resources.getSystem().getConfiguration().getLocales();
            locale = locales.get(0);
        } else {
            locale = Resources.getSystem().getConfiguration().locale;
        }
        return locale;
    }

}
