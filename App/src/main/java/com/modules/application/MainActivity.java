package com.modules.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.modules.b_editormodule.TestActivity;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TestActivity.class));
            Logger.d("aaaaaaaaaaaaa");
        });
    }
}