package com.modules.basemodule.http.repository;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.modules.basemodule.BuildConfig;
import com.modules.basemodule.http.CommonCallback;
import com.modules.basemodule.http.bean.ResponseRemoteConfig;
import com.modules.basemodule.http.service.APIService;

import java.io.File;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataRepository {


    private  static Retrofit retrofit;

    static {
        //初始化一个okhttp用来添加请求日志拦截器
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //只有debug版本添加拦截器
        //https://www.jianshu.com/p/ab79b22b6d04
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        //初始化retrofit,并添加okhttpclinet
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.ALIYUN_REMOTE_CDN_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }


    public static void getAliRemoteConfig(CommonCallback<ArrayList<ResponseRemoteConfig>> callback) {
        APIService service = retrofit.create(APIService.class);
        Call<ArrayList<ResponseRemoteConfig>> call = service.getRemoteConfig();
        call.enqueue(new Callback<ArrayList<ResponseRemoteConfig>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseRemoteConfig>> call, Response<ArrayList<ResponseRemoteConfig>> response) {
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseRemoteConfig>> call, Throwable t) {
            }
        });
    }

    public static void download(String path) {
        APIService service = retrofit.create(APIService.class);
        Call<ResponseBody> responseBodyCall = service.downloadFileWithDynamicUrlAsync("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F19%2F03%2F15%2F75076c485081d15ed9c224ad3e4ce4a1.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639277891&t=8083b76a681645e0a90ba395037bc12a");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                FileIOUtils.writeFileFromIS(new File(path),response.body().byteStream());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }




}
