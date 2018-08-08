package com.duan1.nhom4.wallpaper.rest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetCollectionApiInterface {


    @GET("wp-json/wp/v2/media/")
    Call<JsonElement> getMediaForCollection(
            @Query("parent") String parent,
            @Query("per_page") String perPage);
}
