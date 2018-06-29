package com.rain.baselib.net;


import com.rain.baselib.base.BaseApplication;
import com.rain.baselib.mvp.model.api.UrlConstant;
import com.rain.baselib.util.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:rain
 * Date:2017/11/13 10:48
 * Description:retrofit工具类
 */

public class RetrofitHelper<T> {

    private static final int DEFAULT_TIMEOUT = 5;

    private static final String TAG = "RetrofitHelper";

    private static String default_base_url = UrlConstant.GAN_HUO_API;

    private String token = (String) SPUtils.get(BaseApplication.getInstance(), "token", "");

    // 调用接口中的网络请求方法的对象
    private RetrofitService retrofitService;
    private Retrofit retrofit;
    private OkHttpClient client = null;

    private static class RetrofitHelperHolder {
        private static final RetrofitHelper retrofitHelper = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return RetrofitHelperHolder.retrofitHelper;
    }

    public static RetrofitHelper getInstance(String default_base_url) {
        RetrofitHelper.default_base_url = default_base_url;
        return new RetrofitHelper();
    }

    private RetrofitHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(default_base_url)
                .addConverterFactory(GsonConverterFactory.create())         // gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // rxjava支持
                .client(getOkHttpClient())                                  // okhttpClient
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    private OkHttpClient getOkHttpClient() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder
                    .addInterceptor(addHeaderInterceptor())
                    .addInterceptor(addQueryParameterInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            client = builder.build();
        }
        return client;
    }

    /**
     * 获取retrofitService接口对象，进行网络请求方法的调用
     */
    public RetrofitService getRetrofitService() {
        return retrofitService;
    }

    /**
     * 调用此方法返回的将不再是单例的class
     */
    public T getRetrofitService(Class<T> tClass) {
        return retrofit.create(tClass);
    }


    /**
     * 设置公共参数
     */
    private Interceptor addQueryParameterInterceptor() {
        return new Interceptor() {     // 添加token公共请求头
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        .addQueryParameter("phoneSystem", "")
                        .addQueryParameter("phoneModel", "")
                        .build();
                Request request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 设置头
     */
    private Interceptor addHeaderInterceptor() {
        return new Interceptor() {     // 添加token公共请求头
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request authorised = originalRequest.newBuilder()
                        .header("token", token)
                        .method(originalRequest.method(), originalRequest.body())
                        .build();
                return chain.proceed(authorised);
            }
        };
    }
}
