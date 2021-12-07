package com.modules.application;

import android.content.Intent;
import android.os.Bundle;

import com.modules.study.activity.DatabaseTestActivity;
import com.modules.study.activity.TestActivity;
import com.modules.study.touch.TouchActivity;
import com.modules.basemodule.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TestActivity.class));
            //RemoteDataRepository.download(FilePath.getInstance(this).getPicFolderPath() + "b.png");
        });
        findViewById(R.id.database).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DatabaseTestActivity.class)));
        findViewById(R.id.test_touch_activity).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, TouchActivity.class)));

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }
}