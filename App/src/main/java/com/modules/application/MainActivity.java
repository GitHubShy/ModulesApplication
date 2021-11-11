package com.modules.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.modules.b_editormodule.TestActivity;
import com.modules.baselibraries.SharedPreferencesManager;
import com.modules.testjni.Test;

public class MainActivity extends AppCompatActivity {

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
        });
    }
}