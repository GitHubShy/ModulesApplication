package com.modules.study.activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.modules.baselibrary.AndroidUtils;
import com.modules.baselibrary.LogProxy;
import com.modules.study.BuildConfig;
import com.modules.study.R;
import com.modules.basemodule.activity.BaseActivity;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;
import com.modules.basemodule.viewmodel.RemoteData;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;


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
                if (rrc != null) {
                    rrc.setMusic_name("xxxxxxxxxxxxxxxxxxxxxxxxx");
                    model.setConfigs(rrc);
                }
            }
        });
    }

    @Override
    protected void initViews() {
        AndroidUtils.getScreenRealSize(getApplication());
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

}