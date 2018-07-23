package com.duan1.nhom4.wallpaper.model;

public class HomeItem {
    private String img;
    private String tv;

    public HomeItem(String img, String tv) {
        this.img = img;
        this.tv = tv;
    }

    public HomeItem() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }
}
