package com.duan1.nhom4.wallpaper.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecycelView  {
    private String placeImage;
    private String placetvName;

    public RecycelView(String placeImage, String placetvName) {
        this.placeImage = placeImage;
        this.placetvName = placetvName;
    }


    public String getPlacetvName() {
        return placetvName;
    }

    public void setPlacetvName(String placetvName) {
        this.placetvName = placetvName;
    }


    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }
}
