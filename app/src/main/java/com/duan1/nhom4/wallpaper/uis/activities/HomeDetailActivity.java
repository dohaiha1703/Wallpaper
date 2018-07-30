package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class HomeDetailActivity extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;


    @Override
    public int injectLayout() {
        return R.layout.activity_home_detail;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarHomeDetail);


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
    }
}
