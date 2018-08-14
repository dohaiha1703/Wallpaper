package com.duan1.nhom4.wallpaper.rest;


import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

//list-up all apis in app
public interface ApiInterface {
    @POST("api/get_nonce/")
    Call<JsonElement> callGetNonceLogin(@Query("controller")String controller,
                                        @Query("method")String method);
    @POST("api/auth/generate_auth_cookie/")
    Call<JsonElement> login(@Query("nonce")String nonce,
                            @Query("username")String username,
                            @Query("password")String password,
                            @Query("insecure")String insecure);

}
