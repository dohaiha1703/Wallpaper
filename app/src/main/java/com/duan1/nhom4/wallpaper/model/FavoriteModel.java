package com.duan1.nhom4.wallpaper.model;

public class FavoriteModel {

    public static final String TABLE_NAME = "FAVORITE_LIST";
    public static final String COLUMN_ID_FAVORITE = "COLUMN_ID_FAVORITE";
    public static final String COLUMN_LINK_FAVORITE = "COLUMN_LINK_FAVORITE";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID_FAVORITE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_LINK_FAVORITE + " TEXT )";

    private String favoriteImage;
    private int id;

    public FavoriteModel() {
    }

    public FavoriteModel(String favoriteImage, int id) {
        this.favoriteImage = favoriteImage;
        this.id = id;
    }

    public String getFavoriteImage() {
        return favoriteImage;
    }

    public void setFavoriteImage(String favoriteImage) {
        this.favoriteImage = favoriteImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
