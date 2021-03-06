package com.duan1.nhom4.wallpaper.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllImageRestClient {

    public static final String BASE_API = "http://nhom4.dotplays.com/wp-json/wp/v2/";
    private static Retrofit retrofit;

    public static Retrofit getRestClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_API).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static GetAllImageApiInterface getApiInterface(){
        return getRestClient().create(GetAllImageApiInterface.class);
    }
}
