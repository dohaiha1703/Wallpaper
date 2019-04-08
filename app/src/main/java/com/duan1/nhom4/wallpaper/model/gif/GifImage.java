
package com.duan1.nhom4.wallpaper.model.gif;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GifImage {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("gif_image")
    @Expose
    private String gifImage;
    @SerializedName("total_views")
    @Expose
    private String totalViews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGifImage() {
        return gifImage;
    }

    public void setGifImage(String gifImage) {
        this.gifImage = gifImage;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(String totalViews) {
        this.totalViews = totalViews;
    }

}
