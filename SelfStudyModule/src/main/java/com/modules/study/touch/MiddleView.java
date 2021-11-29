package com.modules.study.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.modules.study.R;
import com.modules.baselibrary.LogProxy;

public class MiddleView extends LinearLayout {
    public MiddleView(Context context) {
        super(context);
        init();
    }

    public MiddleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MiddleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MiddleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater LayoutInflater = (android.view.LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater.inflate(R.layout.self_middle_view,this,false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogProxy.d("middle onTouchEvent");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogProxy.d("middle onInterceptTouchEvent");
        return true;
    }
}
