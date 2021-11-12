package com.modules.basemodule.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.modules.basemodule.R;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;
import com.modules.basemodule.viewmodel.RemoteData;

public class TestView extends ConstraintLayout {

    private ResponseRemoteConfig mData;

    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.base_test_view, this, true);
        TextView tv = findViewById(R.id.text);

        if (getContext() instanceof FragmentActivity) {
            FragmentActivity lifecycleOwner = (FragmentActivity) getContext();
            RemoteData model = new ViewModelProvider(lifecycleOwner).get(RemoteData.class);
            model.getConfigs().observe(lifecycleOwner, responseRemoteConfig -> {
                mData = responseRemoteConfig;
                tv.setText(mData.toString());
            });
            findViewById(R.id.button).setOnClickListener(view -> {
                mData.setMusic_name("FROMFFFFFF");
                model.setConfigs(mData);
            });

        }
    }

}
