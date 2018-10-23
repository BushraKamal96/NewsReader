package com.example.acer.newsreader.Interface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acer on 10/14/2018.
 */

public class RetrofitBuilder {
    private static Retrofit ourInstance = null;

    public static Retrofit getInstance() {
        if (ourInstance == null)
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return ourInstance;

    }

    private RetrofitBuilder() {
    }
}
