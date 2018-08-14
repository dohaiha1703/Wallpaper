package com.duan1.nhom4.wallpaper.rest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAllImageApiInterface {
    @GET("api.php?latest")
    Call<JsonElement> getAllMedia();
}
