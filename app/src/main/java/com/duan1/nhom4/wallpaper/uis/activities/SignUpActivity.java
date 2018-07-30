package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.duan1.nhom4.wallpaper.R;

public class SignUpActivity extends BaseActivity {

    Toolbar toolbar;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarSignUp);
    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
