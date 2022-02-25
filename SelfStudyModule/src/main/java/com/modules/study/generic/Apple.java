package com.modules.study.generic;

import com.socks.library.KLog;

public class Apple extends Fruit{
    public String colour;
    @Override
    protected void say() {
        KLog.d("1111111111111111","I am an apple");
    }
}
