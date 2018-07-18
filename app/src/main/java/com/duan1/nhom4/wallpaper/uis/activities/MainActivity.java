package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(injectLayout());
    }

    @Override
    public int injectLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void intialView() {

    }

    @Override
    public void intialVariables() {

    }
}
