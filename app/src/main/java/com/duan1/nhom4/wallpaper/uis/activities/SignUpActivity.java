package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.duan1.nhom4.wallpaper.R;

public class SignUpActivity extends BaseActivity {

    ImageView imgBack;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void intialView() {
        imgBack = findViewById(R.id.imgBack);
    }

    @Override
    public void intialVariables() {
        eventClick();
    }

    public void eventClick() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
