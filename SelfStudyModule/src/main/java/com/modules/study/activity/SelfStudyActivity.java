package com.modules.study.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.modules.basemodule.pojo.KeyValue;
import com.modules.study.BuildConfig;
import com.modules.study.R;
import com.modules.study.generic.Apple;
import com.modules.study.generic.Basket;
import com.modules.study.generic.Fruit;
import com.modules.study.service.MyService;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SelfStudyActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<KeyValue> mData;

    private Basket<Fruit> mBasket;

    private Map mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_study);


        mRecyclerView = findViewById(R.id.list_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(manager);

        mData = new ArrayList<>();
        KeyValue item = new KeyValue("Callable");
        mData.add(item);
        KeyValue item2 = new KeyValue("Generic");
        mData.add(item2);
        mRecyclerView.setAdapter(new MyAdapter(mData, this));
//        mMap = BuildConfig.;
//        KLog.d("111111111111111","mMap="+mMap.get(1));
    }

    @Override
    public void onClick(View v) {
        String text = (String) v.getTag();
        if (text.equals("Callable")) {
            testCallable();
        } else if (text.equals("Generic")) {
            testGeneric();
        }
    }

    private void testCallable() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<ArrayList<String>> submit = threadPool.submit(new Callable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() {
                ArrayList<String> list = new ArrayList<>();
                list.add("a");
                list.add("b");

                KLog.d("111111111111", "111=" + Thread.currentThread().getName());
                return null;
            }
        });
        try {
            KLog.d("111111111111", "222=" + Thread.currentThread().getName());
            ArrayList<String> strings = submit.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testGeneric() {
        Basket<? extends Fruit> appleBasket = new Basket<Apple>();
//        mBasket = appleBasket;
    }

    final class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<KeyValue> data;

        private View.OnClickListener listener;


        public MyAdapter(List<KeyValue> data, View.OnClickListener listener) {
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
            KeyValue item = data.get(position);
            holder.button.setText(item.key);
            holder.button.setTag(item.key);
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
}