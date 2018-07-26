package com.duan1.nhom4.wallpaper.model;

public class CollectionsItem {

    private String name;
    private String img;
    private String note;

    public CollectionsItem(String name, String img, String note) {
        this.name = name;
        this.img = img;
        this.note = note;
    }

    public CollectionsItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
