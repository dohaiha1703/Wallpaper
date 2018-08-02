package com.duan1.nhom4.wallpaper.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClientSignUp {
    public static final String BASE_API = "http://nhom4.dotplays.com/";
    private static Retrofit retrofit;
    private static final String SIGN_API = "http://nhom4.dotplays.com";
    private static Retrofit retrofitSignUp;

    public static Retrofit getRestClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APISignUp getApiSignUp() {
        return getRestClient().create(APISignUp.class);
    }
    //Sign Up

    public static Retrofit getSignApi() {
        if (retrofitSignUp == null){
            retrofitSignUp = new Retrofit.Builder()
                    .baseUrl(SIGN_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitSignUp;
    }
    public static APISignUp getApiSign(){
        return getSignApi().create(APISignUp.class);
    }
}
