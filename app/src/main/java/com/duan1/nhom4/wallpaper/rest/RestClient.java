package com.duan1.nhom4.wallpaper.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final String BASE_API = "http://nhom4.dotplays.com/";
    private static Retrofit retrofit;
    public static final String LOGIN_API = "http://nhom4.dotplays.com/";
    private static Retrofit retrofitLogin;


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

    //Login
    public static Retrofit getLogin() {
        if (retrofitLogin == null) {
            retrofitLogin = new Retrofit.Builder()
                    .baseUrl(LOGIN_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitLogin;
    }

    public static ApiInterface getApiLogin() {
        return getLogin().create(ApiInterface.class);
    }
}
