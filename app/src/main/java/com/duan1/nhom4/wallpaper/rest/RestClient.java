package com.duan1.nhom4.wallpaper.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final String BASE_API = "https://api.androidhive.info/";
    private static Retrofit retrofit;

    public static Retrofit getRestClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface() {
        return getRestClient().create(ApiInterface.class);
    }
}
