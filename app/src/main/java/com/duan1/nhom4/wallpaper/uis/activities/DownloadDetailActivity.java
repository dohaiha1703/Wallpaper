package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.DownloadModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class DownloadDetailActivity extends BaseActivity {
    private Toolbar toolbar;
    private ImageView imgDownload, imgDelete;
    private Bitmap bitmapUse;
    private String imgUrl;
    private ProgressDialog progressDialog;
    private DataBaseManager dbManager;
    private boolean check = true;
    private List<DownloadModel> downloadModels;
    private int pos;
    @Override
    public int injectLayout() {
        return R.layout.activity_download_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarDownloadDetail);
        imgDelete = findViewById(R.id.imgDownloadDetailDelete);
        imgDownload = findViewById(R.id.imgDownloadDetail);

        dbManager = new DataBaseManager(DownloadDetailActivity.this);
        downloadModels = dbManager.getAllDownload();

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

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check){
                    dbManager.deleteDownloadItem(downloadModels.get(pos));
                    imgDelete.setVisibility(View.INVISIBLE);
                    check = false;
                    startActivity(new Intent(DownloadDetailActivity.this, DownloadActivity.class));
                    Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void getIncomingIntent() {
        if (getIntent().hasExtra("img_url")) {
            imgUrl = getIntent().getStringExtra("img_url");
            pos = getIntent().getIntExtra("img_pos", pos);
            Picasso
                    .with(DownloadDetailActivity.this)
                    .load(imgUrl)
                    .into(imgDownload);
        }
    }

    public void applyDownoadWallpaper(View view) {
        new setWallpaer().execute(imgUrl);
    }


    public class setWallpaer extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                bitmapUse = Picasso.with(DownloadDetailActivity.this)
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

            progressDialog = new ProgressDialog(DownloadDetailActivity.this);
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
                Toast.makeText(DownloadDetailActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
