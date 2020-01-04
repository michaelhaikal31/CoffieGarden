package com.royalhouse.coffiegarden.Services;

import com.royalhouse.coffiegarden.Constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {
    private Retrofit mRetrofit = null;
    public Retrofit getInstance(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .build();
        if(mRetrofit == null){
            mRetrofit =  new Retrofit.Builder()
                    .baseUrl(Constant.URL_SERVICE)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public Retrofit getPublicService(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .build();
        if(mRetrofit == null){
            mRetrofit =  new Retrofit.Builder()
                    .baseUrl(Constant.URL_TEST)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
