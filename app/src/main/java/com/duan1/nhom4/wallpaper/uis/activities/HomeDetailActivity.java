package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

public class HomeDetailActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imgHomeDetail;
    private String imgUrl;
    private Bitmap bitmap;


    @Override
    public int injectLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarHomeDetail);
        imgHomeDetail = findViewById(R.id.imgHomeDetail);
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
    }

    private void incomingIntent() {
        if (getIntent().hasExtra("img_url")) {
            imgUrl = getIntent().getStringExtra("img_url");
            Glide.with(HomeDetailActivity.this).load(imgUrl).into(imgHomeDetail);
        }
    }

    public void downLoadImg(View view) {
        if (imgUrl.length() > 0) {
//            startDownload(imgUrl);
//            viewDownload();

            setWallpaper();
        }
    }

    public void startDownload(String url) {
        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse(url));
        mRqRequest.setDescription("This is Test File");
//  mRqRequest.setDestinationUri(Uri.parse("give your local path"));
        long idDownLoad = mManager.enqueue(mRqRequest);
    }

    public void viewDownload() {
        Intent mView = new Intent();
        mView.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(mView);
    }

    public void setWallpaper(){

        try {
            bitmap =Picasso.with(HomeDetailActivity.this)
                    .load(imgUrl)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(HomeDetailActivity.this);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
