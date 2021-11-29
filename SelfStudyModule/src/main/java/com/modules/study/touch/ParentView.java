package com.modules.study.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.modules.study.R;
import com.modules.baselibrary.LogProxy;

public class ParentView extends LinearLayout {

    public ParentView(Context context) {
        super(context);
        init();
    }

    public ParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogProxy.d("parent onTouchEvent");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogProxy.d("parent onInterceptTouchEvent");
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void init() {
        LayoutInflater LayoutInflater = (android.view.LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater.inflate(R.layout.self_parent_view, this, true);
    }
}
