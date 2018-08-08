package com.duan1.nhom4.wallpaper.model;

public class CollectionsItem {

    private String name;
    private int img;
    private String note;

    public CollectionsItem(String name, int img, String note) {
        this.name = name;
        this.img = img;
        this.note = note;
    }

    public CollectionsItem() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
