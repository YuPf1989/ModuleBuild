package com.rain.baselib.net;



import com.rain.baselib.base.BaseApplication;
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

public class RetrofitHelper {

    private static final int DEFAULT_TIMEOUT = 5;

    private static RetrofitHelper retrofitHelper = new RetrofitHelper();

    private static final String TAG = "RetrofitHelper";

    private String token = (String) SPUtils.get(BaseApplication.getInstance(), "token", "");

    // 调用接口中的网络请求方法的对象
    private RetrofitService retrofitService;
    private  Retrofit retrofit;

    public static RetrofitHelper getInstance() {
        return retrofitHelper;
    }

    private RetrofitHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())         // gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // rxjava支持
                .client(getOkHttpClient())                                  // okhttpClient
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    private OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder
                .addInterceptor(addHeaderInterceptor())
                .addInterceptor(addQueryParameterInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        return builder.build();
    }

    /**
     * 获取retrofitService接口对象，进行网络请求方法的调用
     */
    public RetrofitService getRetrofitService() {
        return retrofitService;
    }


    /**
     * 设置公共参数
     */
    private Interceptor addQueryParameterInterceptor(){
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
                        .method(originalRequest.method(),originalRequest.body())
                        .build();
                return chain.proceed(authorised);
            }
        };
    }
}
