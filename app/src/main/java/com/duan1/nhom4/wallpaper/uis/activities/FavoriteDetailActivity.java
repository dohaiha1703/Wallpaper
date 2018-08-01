package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class FavoriteDetailActivity extends BaseActivity {
    private static final String TAG = "FavoriteDetailActivity";
    private Toolbar toolbar;


    @Override
    public int injectLayout() {
        return R.layout.activity_favorite_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarFavoriteDetail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
            }
        });
       getIncomingIntent();
    }


    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
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
}
