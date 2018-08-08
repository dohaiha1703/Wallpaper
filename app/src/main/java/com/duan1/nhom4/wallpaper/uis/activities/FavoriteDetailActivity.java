package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteDetailActivity extends BaseActivity {
    private static final String TAG = "FavoriteDetailActivity";
    private Toolbar toolbar;
    private ImageView imgDownload, imgFavorite, imgSetFavorite;
    private Bitmap bitmapUse;
    private String imgUrl;
    public ProgressDialog progressDialog;
    private DataBaseManager dbManager;
    private boolean check = true;
    private List<FavoriteModel> favoriteModels;
    private int pos;


    @Override
    public int injectLayout() {
        return R.layout.activity_favorite_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarFavoriteDetail);
        imgFavorite = findViewById(R.id.imgFavoriteDetail);
        imgSetFavorite = findViewById(R.id.imgFavoriteDetailSetFavorite);

        dbManager = new DataBaseManager(FavoriteDetailActivity.this);
        favoriteModels = dbManager.getAllFavorite();

        getIncomingIntent();
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

        imgSetFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true) {
                    dbManager.deleteFavoriteItem(favoriteModels.get(pos));
                    imgSetFavorite.setVisibility(View.INVISIBLE);
                    check = false;
                    finish();
                    startActivity(new Intent(FavoriteDetailActivity.this, FavoriteActivity.class));
                }
//                else if (check == false) {
//                    dbManager.insertFavorite(imgUrl);
//                    imgSetFavorite.setImageResource(R.drawable.ic_action_star_10);
//                    check = true;
//                }
            }
        });
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("img_url")) {
            imgUrl = getIntent().getStringExtra("img_url");
            pos = getIntent().getIntExtra("img_pos", pos);
            Picasso
                    .with(FavoriteDetailActivity.this)
                    .load(imgUrl)
                    .into(imgFavorite);
        }
    }
}
