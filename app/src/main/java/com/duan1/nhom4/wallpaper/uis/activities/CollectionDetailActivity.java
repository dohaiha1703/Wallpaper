package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class CollectionDetailActivity extends BaseActivity {
    private Toolbar toolbar;
    private String imgUrl;
    private ImageView imgSubjectDetail, imgCollectionFavorite, imgCollectionDownload;
    private DataBaseManager dbManager;
    private ProgressDialog progressDialog;
    private Bitmap bitmapUse;

    @Override
    public int injectLayout() {
        return R.layout.activity_subject_detail;
    }

    @Override
    public void intialView() {

        toolbar = findViewById(R.id.toolbarSubjectDetail);
        imgUrl = null;
        imgSubjectDetail = findViewById(R.id.imgSubjectDetail);
        imgCollectionFavorite = findViewById(R.id.imgCollectionFavorite);
        imgCollectionDownload = findViewById(R.id.imgCollectionDownload);
        dbManager = new DataBaseManager(this);
        dialog = new ProgressDialog(CollectionDetailActivity.this);
        bitmapUse = null;
    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //quay lại trang vừa ấn
            }
        });

        getIncomingData();
    }


    private void getIncomingData() {
        if (getIntent().hasExtra("img_url")) {
            imgUrl = getIntent().getStringExtra("img_url");

            Picasso.with(CollectionDetailActivity.this).load(imgUrl).into(imgSubjectDetail);
        }
    }

    public void eventFavorite(View view) {
        dbManager.insertFavorite(imgUrl);
        imgCollectionFavorite.setVisibility(View.INVISIBLE);
    }


    private void startDownload(String url) {
        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse(url));
        mRqRequest.setDescription("This was downloaded from Wallpaer");
//        mRqRequest.setDestinationUri(Uri.parse("give your local path"));
        mRqRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        long idDownLoad = mManager.enqueue(mRqRequest);

    }

    public void DownloadCollection(View view) {
        if (imgUrl.length() > 0) {
            startDownload(imgUrl);
            dbManager.insertDownload(imgUrl);
            imgCollectionDownload.setVisibility(View.INVISIBLE);
        }
    }

    public void applyWallpaper(View view) {
       new setWallpaer().execute(imgUrl);
    }


    public class setWallpaer extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                bitmapUse = Picasso.with(CollectionDetailActivity.this)
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

            progressDialog = new ProgressDialog(CollectionDetailActivity.this);
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
                Toast.makeText(CollectionDetailActivity.this, "Succeed", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
