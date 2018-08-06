package com.duan1.nhom4.wallpaper.model;

public class DownloadModel {

    public static final String TABLE_NAME = "DOWNLOAD_LIST";
    public static final String COLUMN_ID_DOWNLOAD = "COLUMN_ID_DOWNLOAD";
    public static final String COLUMN_LINK_DOWNLOAD = "COLUMN_LINK_DOWNLOAD";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID_DOWNLOAD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_LINK_DOWNLOAD + " TEXT )";


    private String placeImage;
    private int id;

    public DownloadModel() {
    }

    public DownloadModel(String placeImage, int id) {
        this.placeImage = placeImage;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

}
