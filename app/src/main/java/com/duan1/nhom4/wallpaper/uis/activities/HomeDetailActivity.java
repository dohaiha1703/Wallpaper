package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.DownloadModel;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.List;

public class HomeDetailActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imgHomeDetail, imgHomeDetailFavorite, imgHomeDetailDownload;
    private Bitmap bitmapUse;
    private String imgUrl;
    private ProgressDialog progressDialog;
    private DataBaseManager dbManager;
    private boolean check = true;
    private List<FavoriteModel> favoriteModels;
    private List<DownloadModel> downloadModels;
    private AppCompatButton btnApply;


    @Override
    public int injectLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarHomeDetail);
        imgHomeDetail = findViewById(R.id.imgHomeDetail);
        imgHomeDetailFavorite = findViewById(R.id.imgHomeDetailFavorite);
        imgHomeDetailDownload = findViewById(R.id.imgHomeDetailDownload);
        btnApply = findViewById(R.id.btnApply);

        dbManager = new DataBaseManager(HomeDetailActivity.this);

    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        incomingIntent();


        favoriteModels = dbManager.getAllFavorite();
        for (int i = 0; i < favoriteModels.size(); i++) {
            if (imgUrl.equals(favoriteModels.get(i).getFavoriteImage())) {
                check = false;
                imgHomeDetailFavorite.setVisibility(View.INVISIBLE);
            } else {
                check = true;
            }
        }

        downloadModels = dbManager.getAllDownload();
        for (int j = 0; j < downloadModels.size(); j++) {
            if (imgUrl.equals(downloadModels.get(j).getPlaceImage())) {
                imgHomeDetailDownload.setVisibility(View.INVISIBLE);
                btnApply.setVisibility(View.VISIBLE);
            }
        }


    }

    public void favoriteEvent(View view) {

        if (check) {
            dbManager.insertFavorite(imgUrl);
            check = false;
            imgHomeDetailFavorite.setVisibility(View.INVISIBLE);
        }
    }

    private void incomingIntent() {
        if (getIntent().hasExtra("img_url")) {
            imgUrl = getIntent().getStringExtra("img_url");
            Glide
                    .with(HomeDetailActivity.this)
                    .load(imgUrl)
                    .into(imgHomeDetail);
        }
    }

    public void downLoadImg(View view) {
        if (imgUrl.length() > 0) {

            startDownload(imgUrl);
            dbManager.insertDownload(imgUrl);
            imgHomeDetailDownload.setVisibility(View.INVISIBLE);
        }
    }

    public void startDownload(String url) {
        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse(url));
        mRqRequest.setDescription("This was downloaded from Wallpaer");
//        mRqRequest.setDestinationUri(Uri.parse("give your local path"));
        mRqRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        long idDownLoad = mManager.enqueue(mRqRequest);

    }

//    public void viewDownload() {
//        Intent mView = new Intent();
//        mView.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
//        startActivity(mView);
//    }

    public void ApplyWallpaper(View view) {
        new setWallpaer().execute(imgUrl);
    }

    public class setWallpaer extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                bitmapUse = Picasso.with(HomeDetailActivity.this)
                        .load(imgUrl)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapUse;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(HomeDetailActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmapUse);


            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());

            try {
                wallpaperManager.setBitmap(bitmapUse);
                progressDialog.dismiss();
                Toast.makeText(HomeDetailActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
