package com.duan1.nhom4.wallpaper.rest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {
    @GET("api.php?latest")
    Call<JsonElement> getAllMedia();

    @GET("api.php?cat_list")
    Call<JsonElement> getCategories();

    @GET("api.php?gif_list")
    Call<JsonElement> getGifList();

    @GET("api.php?")
    Call<JsonElement> getSingleImage(@Query("wallpaper_id") String id);
}

