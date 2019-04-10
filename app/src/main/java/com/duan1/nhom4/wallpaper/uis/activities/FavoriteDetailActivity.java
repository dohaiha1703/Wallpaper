package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class FavoriteDetailActivity extends BaseActivity {
    private static final String TAG = "FavoriteDetailActivity";
    private Toolbar toolbar;
    private ImageView imgDownload, imgFavorite, imgSetFavorite;
    private Bitmap bitmapUse;
    private String imgUrl;
    private ProgressDialog progressDialog;
    private DataBaseManager dbManager;
    private boolean check = true;
    private List<FavoriteModel> favoriteModels;
    private int pos;
    private int count;


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
        count = 0;
    }


    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        imgSetFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteFavoriteItem(favoriteModels.get(pos));
                Log.d("ha123", favoriteModels.size() + "");
                imgSetFavorite.setVisibility(View.INVISIBLE);
                finish();
            }
        });
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("img_url")) {
            imgUrl = getIntent().getStringExtra("img_url");
            pos = getIntent().getIntExtra("img_pos", pos);
            Log.d("ha123", "pos: " + pos);
            Glide
                    .with(FavoriteDetailActivity.this)
                    .load(imgUrl)
                    .into(imgFavorite);
        }
    }

    public void applyFavoriteWallpaper(View view) {
        showAlertDialogSingleChoice();
    }

    public void showAlertDialogSingleChoice() {

        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(FavoriteDetailActivity.this);

        builder.setTitle("What would you like?");

        final String[] list = {"Set as Home Screen", "Set as Lock Screen", "Set Both"};

        builder.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                count = which;
            }
        });

        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("count", count + "");
                new SetWallpaer().execute(imgUrl);
            }
        });
        builder.show();
    }


    public class SetWallpaer extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                bitmapUse = Picasso.with(FavoriteDetailActivity.this)
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

            progressDialog = new ProgressDialog(FavoriteDetailActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmapUse);

//            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());

            try {
                if (count == 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        WallpaperManager.getInstance(getApplicationContext()).
                                setBitmap(bitmapUse, null, true, WallpaperManager.FLAG_SYSTEM);
                    }
                } else if (count == 1) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        WallpaperManager.getInstance(getApplicationContext()).
                                setBitmap(bitmapUse, null, true, WallpaperManager.FLAG_LOCK);
                    } else {
                        Toast.makeText(mContext, "SDK >= 24", Toast.LENGTH_SHORT).show();
                    }
                } else if (count == 2) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        WallpaperManager.getInstance(getApplicationContext()).
                                setBitmap(bitmapUse, null, true, WallpaperManager.FLAG_LOCK);

                        WallpaperManager.getInstance(getApplicationContext()).setBitmap(bitmapUse);
                    } else {
                        Toast.makeText(mContext, "SDK >= 24", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
                Toast.makeText(FavoriteDetailActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
