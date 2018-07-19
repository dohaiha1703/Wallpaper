package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class SignInActivity extends BaseActivity {

    AppCompatButton btnSignUp;

    @Override
    public int injectLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void intialView() {
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    @Override
    public void intialVariables() {
        eventClick();
    }

    public void eventClick(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
