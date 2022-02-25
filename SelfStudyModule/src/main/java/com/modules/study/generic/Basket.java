package com.modules.study.generic;

public class Basket<T>{
    public T t;
    public void setItem(T t) {
        this.t = t;
    }

    public T getItem() {
        return this.t;
    }
}
