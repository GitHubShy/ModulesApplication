package com.modules.basemodule.http.service;

import com.modules.basemodule.BuildConfig;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIService {

    @GET(BuildConfig.REMOTE_CONFIG_JSON)
    Call<ArrayList<ResponseRemoteConfig>> getRemoteConfig();

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);
}
