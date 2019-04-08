package com.duan1.nhom4.wallpaper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void changeTheme(View view) {

        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences currentTheme = preferenceManager.getString()

    }
}
