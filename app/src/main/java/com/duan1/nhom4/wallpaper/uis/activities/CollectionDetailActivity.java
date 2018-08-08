package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.squareup.picasso.Picasso;

public class CollectionDetailActivity extends BaseActivity {
    Toolbar toolbar;
    private String imgUrl;
    private ImageView imgSubjectDetail;


    @Override
    public int injectLayout() {
        return R.layout.activity_subject_detail;
    }

    @Override
    public void intialView() {

        toolbar = findViewById(R.id.toolbarSubjectDetail);
        imgUrl = null;
        imgSubjectDetail = findViewById(R.id.imgSubjectDetail);
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
}
