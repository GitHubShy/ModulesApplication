package com.modules.application;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.modules.baselibrary.LogProxy;
import com.modules.baselibrary.NewNetworkUtils;
import com.modules.study.activity.DatabaseTestActivity;
import com.modules.study.activity.TestActivity;
import com.modules.study.service.MyService;
import com.modules.study.touch.TouchActivity;
import com.modules.basemodule.activity.BaseActivity;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<Item> mData;

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

        mRecyclerView = findViewById(R.id.list_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(manager);

        mData = new ArrayList<>();
        Item item = new Item("service");
        mData.add(item);
        mRecyclerView.setAdapter(new MyAdapter(mData,this));
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


    public void getIP(View view) {
//        LogProxy.d(NetworkUtils.getLocalIpAddress());
//        LogProxy.d(NetworkUtils.getIPAddress(true));
//        NetworkUtils.getLocalIp();
//        LogProxy.e(NetworkUtils.getLocalIp());
//        LogProxy.e(NetworkUtils.getHotspotIPAddress(this));
        KLog.d("xin tong NetworkUtils===", NewNetworkUtils.getLocalIp(this, true));
        KLog.d("NetworkUtils===", NetworkUtils.getIPAddress(true));
    }

    @Override
    public void onClick(View v) {
        String text = (String) v.getTag();
        if (text.equals("service")) {
            Intent i = new Intent(this,MyService.class);
            startService(i);
        }
    }

    final class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Item> data;

        private View.OnClickListener listener;


        public MyAdapter(List<Item> data,View.OnClickListener listener) {
            this.data = data;
            this.listener = listener;
        }

        @NonNull
        @Override

        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.recycle_view_item, parent, false);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Item item = data.get(position);
            holder.button.setText(item.text);
            holder.button.setTag(item.text);
            holder.button.setOnClickListener(listener);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    final class MyViewHolder extends RecyclerView.ViewHolder {

        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }

    final class Item {
        public String text;

        public Item(String text) {
            this.text = text;
        }
    }

}