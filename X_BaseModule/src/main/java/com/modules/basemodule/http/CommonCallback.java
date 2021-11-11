package com.modules.basemodule.http;


/** 此项目的网络请求返回的封装，业务层只关心返回的实体bean或者错误码
 * @param <T>
 */
public interface CommonCallback<T> {


    void onResponse(T t);


    void onFailure(int errorCode, String msg);
}
