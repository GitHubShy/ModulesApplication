package com.modules.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.modules.b_editormodule.test.DatabaseTestActivity;
import com.modules.b_editormodule.test.TestActivity;
import com.modules.baselibraries.LogManager;
import com.modules.baselibraries.SharedPreferencesManager;
import com.modules.basemodule.activity.BaseActivity;
import com.modules.basemodule.http.CommonCallback;
import com.modules.basemodule.http.FilePath;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;
import com.modules.basemodule.http.repository.RemoteDataRepository;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.text);
        SharedPreferencesManager.putString("1", "2");
        SharedPreferencesManager.putString("test", "1", "3");
        String text = SharedPreferencesManager.getString("1", "") + ";" + SharedPreferencesManager.getString("test", "1", "");
//        tv.setText(new Test().stringFromJNI());
        tv.setText(text);
        findViewById(R.id.go).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TestActivity.class));
            //RemoteDataRepository.download(FilePath.getInstance(this).getPicFolderPath() + "b.png");
        });
        findViewById(R.id.database).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DatabaseTestActivity.class)));
    }
}