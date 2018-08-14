package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class SearchActivity extends BaseActivity {

    private Toolbar toolbar;


    @Override
    public int injectLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarSearch);
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
