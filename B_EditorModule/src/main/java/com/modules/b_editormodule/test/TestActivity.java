package com.modules.b_editormodule.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.modules.b_editormodule.R;
import com.modules.b_editormodule.activity.BaseActivity;
import com.modules.baselibraries.LogManager;
import com.modules.baselibraries.OkHttpClientUtils;
import com.modules.basemodule.Configuration;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;
import com.modules.basemodule.viewmodel.RemoteData;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class TestActivity extends BaseActivity {

    ResponseRemoteConfig rrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity_main);

        TextView tv = findViewById(R.id.aa);


        RemoteData model = new ViewModelProvider(this).get(RemoteData.class);
        model.getConfigs().observe(this, responseRemoteConfig -> {
            tv.setText(responseRemoteConfig.toString());
            rrc = responseRemoteConfig;
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup parent = findViewById(R.id.root);
                parent.removeView(findViewById(R.id.test_view));
            }
        });
        findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rrc.setMusic_name("xxxxxxxxxxxxxxxxxxxxxxxxx");
                model.setConfigs(rrc);
            }
        });

    }
}