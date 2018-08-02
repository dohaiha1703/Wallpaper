package com.duan1.nhom4.wallpaper.uis.activities;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.io.IOException;

public class DownloadDetailActivity extends BaseActivity {
    private Toolbar toolbar;
    private AppCompatButton compatButton;
    private Bitmap bitmap;
    int lastImageref;
    private ImageView imageDownload;
    private static final String TAG = "DownloadDetailActivity";

    @Override
    public int injectLayout() {
        return R.layout.activity_download_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarDownloadDetail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DownloadActivity.class));
            }
        });
        getIncomingIntent();
        compatButton = findViewById(R.id.btnDownloadAD);
        imageDownload = findViewById(R.id.imgDownloadDetail);
        imageDownload.setImageResource(R.drawable.background);
        compatButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                showDialogDownload();
            }
        });
    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        Log.d(TAG, "onCreate: started.");


    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_url")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String imageUrl = getIntent().getStringExtra("image_url");
            setImage(imageUrl);
        }
    }
    private void setImage(String imageUrl){
        Log.d(TAG, "setImage: setting te image and name to widgets.");
        ImageView image = findViewById(R.id.imgDownloadDetail);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }
    public void showDialogDownload(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn dùng ảnh này làm ảnh nền không");
        builder.setCancelable(false);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
//                Toast.makeText(DownloadDetailActivity.this, "Không thoát được", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setResource(R.drawable.background);
                }catch (IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(DownloadDetailActivity.this, "Đã dùng hình nền", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
