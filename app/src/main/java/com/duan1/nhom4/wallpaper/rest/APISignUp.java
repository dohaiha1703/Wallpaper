package com.duan1.nhom4.wallpaper.rest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APISignUp {
    @GET("api/get_nonce/")
    Call<JsonElement> callGetNonceSignUp(@Query("controller")String controller,
                                         @Query("method")String method);
    @GET("api/user/register/")
    Call<JsonElement> signup(
            @Query("username") String username,
            @Query("email") String email,
            @Query("nonce") String nonce,
            @Query("display_name") String display,
            @Query("user_pass") String password,
            @Query("insecure") String cool);
}
