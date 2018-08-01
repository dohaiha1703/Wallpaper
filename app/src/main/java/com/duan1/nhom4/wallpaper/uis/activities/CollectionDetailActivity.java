package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class CollectionDetailActivity extends BaseActivity {
    Toolbar toolbar;


    @Override
    public int injectLayout() {
        return R.layout.activity_collection_detail;
    }

    @Override
    public void intialView() {

        toolbar = findViewById(R.id.toolbarCollecDetail);

    }

    @Override
    public void intialVariables() {
        toolbar.setTitle("chi tiet");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //quay lại trang vừa ấn
            }
        });
    }
}
