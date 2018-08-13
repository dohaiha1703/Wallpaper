package com.duan1.nhom4.wallpaper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.model.DownloadModel;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper {

    Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "link_db";

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DatabaseClassManager", "DatabaseClassManager");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FavoriteModel.CREATE_TABLE);
        db.execSQL(DownloadModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteModel.TABLE_NAME);
        onCreate(db);
    }


    public void insertFavorite(String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FavoriteModel.COLUMN_LINK_FAVORITE, link);
        db.insert(FavoriteModel.TABLE_NAME, null, values);
        db.close();
        Log.e("ok", "add favorite");

    }

    public void insertDownload(String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DownloadModel.COLUMN_LINK_DOWNLOAD, link);
        db.insert(DownloadModel.TABLE_NAME, null, values);
        db.close();
        Log.e("ok", "add download");
    }

    public List<FavoriteModel> getAllFavorite() {
        List<FavoriteModel> list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + FavoriteModel.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FavoriteModel model = new FavoriteModel();

                model.setFavoriteImage(cursor.getString(cursor.getColumnIndex(FavoriteModel.COLUMN_LINK_FAVORITE)));
                model.setId(cursor.getInt(cursor.getColumnIndex(FavoriteModel.COLUMN_ID_FAVORITE)));

                list.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public List<DownloadModel> getAllDownload() {
        List<DownloadModel> list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DownloadModel.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DownloadModel model = new DownloadModel();
                model.setPlaceImage(cursor.getString(cursor.getColumnIndex(DownloadModel.COLUMN_LINK_DOWNLOAD)));
                model.setId(cursor.getInt(cursor.getColumnIndex(DownloadModel.COLUMN_ID_DOWNLOAD)));

                list.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return list;

    }

    public void deleteFavoriteItem(FavoriteModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(FavoriteModel.TABLE_NAME,
                FavoriteModel.COLUMN_ID_FAVORITE + "=?", new String[]{String.valueOf(model.getId())});

        db.close();
    }

    public void deleteDownloadItem(DownloadModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DownloadModel.TABLE_NAME,
                DownloadModel.COLUMN_ID_DOWNLOAD + "=?", new String[]{String.valueOf(model.getId())});

        db.close();
    }
}
