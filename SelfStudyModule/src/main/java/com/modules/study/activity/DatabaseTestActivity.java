package com.modules.study.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.modules.study.R;
import com.modules.basemodule.room.AppRoomDatabase;
import com.modules.basemodule.room.entity.Memory;

import java.util.List;
import java.util.Random;

public class DatabaseTestActivity extends AppCompatActivity {

    private List<Integer> mIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity_database_test);

        mIds =  AppRoomDatabase.getInstance(getApplication()).getMemoryDao().getAllIds();

        updateData();

        findViewById(R.id.add).setOnClickListener(view -> {
            AppRoomDatabase.getInstance(getApplication()).getMemoryDao().insert(new Memory(getRandomString(2),getRandomString(5)));
            updateData();
        });

        findViewById(R.id.delete).setOnClickListener(view -> {
            AppRoomDatabase.getInstance(getApplication()).getMemoryDao().delete(mIds.get(0));
            mIds =  AppRoomDatabase.getInstance(getApplication()).getMemoryDao().getAllIds();
            updateData();
        });

        findViewById(R.id.delete_all).setOnClickListener(view -> {
            AppRoomDatabase.getInstance(getApplication()).getMemoryDao().deleteAll();
            mIds =  AppRoomDatabase.getInstance(getApplication()).getMemoryDao().getAllIds();
            updateData();
        });


    }

    private void updateData() {
        TextView tv = findViewById(R.id.content);
        List<Memory> all = AppRoomDatabase.getInstance(getApplication()).getMemoryDao().getAll();
        tv.setText(all.toString());
    }

    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}