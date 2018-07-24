package com.duan1.nhom4.wallpaper.model;

public class RecycelViewDowload {
    private String placeImage;
    private String placeImage1;
    private String placeImage2;

    public RecycelViewDowload(String placeImage, String placeImage1, String placeImage2) {
        this.placeImage = placeImage;
        this.placeImage1 = placeImage1;
        this.placeImage2 = placeImage2;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

    public String getPlaceImage1() {
        return placeImage1;
    }

    public void setPlaceImage1(String placeImage1) {
        this.placeImage1 = placeImage1;
    }

    public String getPlaceImage2() {
        return placeImage2;
    }

    public void setPlaceImage2(String placeImage2) {
        this.placeImage2 = placeImage2;
    }
}
