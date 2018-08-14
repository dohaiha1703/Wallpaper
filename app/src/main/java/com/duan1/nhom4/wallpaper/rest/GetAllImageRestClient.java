package com.duan1.nhom4.wallpaper.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllImageRestClient {

    public static final String BASE_URL = "http://www.tapetee.com/";
    private static Retrofit retrofit;

    public static Retrofit getRestClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static GetAllImageApiInterface getApiInterface() {
        return getRestClient().create(GetAllImageApiInterface.class);
    }
}
