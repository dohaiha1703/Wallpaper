package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.DownloadModel;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class HomeDetailActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imgHomeDetail;
    private Bitmap bitmapUse;
    private String imgUrl;
    private ProgressDialog progressDialog;
    private DataBaseManager dbManager;
    private boolean check = true;
    private List<FavoriteModel> favoriteModels;
    private List<DownloadModel> downloadModels;
    private int count;
    private FloatingActionButton mButtonDownload, mButtonFavorite, mButtonShare, mButtonSetWallpaper;


    @Override
    public int injectLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarHomeDetail);
        imgHomeDetail = findViewById(R.id.imgHomeDetail);

        dbManager = new DataBaseManager(HomeDetailActivity.this);

        count = 0;

        mButtonDownload = findViewById(R.id.menu_download);
        mButtonFavorite = findViewById(R.id.menu_favorite);
        mButtonSetWallpaper = findViewById(R.id.menu_set_wallpaper);
        mButtonShare = findViewById(R.id.menu_share);
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
                mButtonFavorite.setClickable(false);
                mButtonFavorite.setColorNormal(R.color.cardview_dark_background);
            } else {
                check = true;
            }
        }

        downloadModels = dbManager.getAllDownload();
        for (int j = 0; j < downloadModels.size(); j++) {
            if (imgUrl.equals(downloadModels.get(j).getPlaceImage())) {
                mButtonDownload.setClickable(false);
                mButtonDownload.setColorNormal(R.color.cardview_dark_background);
            }
        }
    }

    public void favoriteEvent(View view) {

        if (check) {
            dbManager.insertFavorite(imgUrl);
            check = false;
            mButtonFavorite.setClickable(false);
            mButtonFavorite.setColorNormal(R.color.cardview_dark_background);
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
            mButtonDownload.setClickable(false);
            mButtonDownload.setColorNormal(R.color.cardview_dark_background);
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


    public void showAlertDialogSingleChoice() {

        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(HomeDetailActivity.this);

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
                new setWallpaer().execute(imgUrl);
            }
        });
        builder.show();
    }

    public void ApplyWallpaper(View view) {
        showAlertDialogSingleChoice();
    }

    public void shareAction(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, imgUrl);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
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
                Toast.makeText(HomeDetailActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
