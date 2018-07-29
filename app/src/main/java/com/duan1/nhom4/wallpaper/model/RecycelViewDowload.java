package com.duan1.nhom4.wallpaper.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecycelViewDowload {
    private String placeImage;

    public RecycelViewDowload(String placeImage) {
        this.placeImage = placeImage;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

}
