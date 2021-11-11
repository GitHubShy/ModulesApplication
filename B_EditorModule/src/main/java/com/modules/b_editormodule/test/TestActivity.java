package com.modules.b_editormodule.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.modules.b_editormodule.R;
import com.modules.baselibraries.LogManager;
import com.modules.baselibraries.OkHttpClientUtils;
import com.modules.basemodule.Configuration;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity_main);
        String url = Configuration.ALIYUN_DOMAIN_CDN_ROOT + Configuration.REMOTE_CONFIG_JSON;
        //            OkHttpClientUtils.doGetAsyn(url, new Callback() {
//                @Override
//                public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                    LogManager.d(url);
//                    LogManager.d(e);
//                }
//
//                @Override
//                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                    LogManager.d(url);
//                    LogManager.d(response.body().toString());
//                }
//            });

        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = null;
                try {
                    text = OkHttpClientUtils.doGet(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LogManager.d(text);
            }
        }).start();

    }
}