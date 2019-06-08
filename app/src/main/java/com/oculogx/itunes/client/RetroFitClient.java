package com.oculogx.itunes.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static final String BASE_URL = "https://itunes.apple.com/";

    public static Retrofit getRetrofit(){

        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return mRetrofit;


    }
}
