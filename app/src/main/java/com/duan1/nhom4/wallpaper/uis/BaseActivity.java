package com.duan1.nhom4.wallpaper.uis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.duan1.nhom4.wallpaper.R;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String KEY_CURRENT_THEME = "current_theme";
    public static final String LILAC_THEME = "lilac";
    public static final String MINT_THEME = "mint";

    protected Context mContext;
    public ProgressDialog dialog;

    private SharedPreferences sharedPreferences;
    private String currentTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getString(KEY_CURRENT_THEME, LILAC_THEME);
        setAppTheme(currentTheme);
        setContentView(injectLayout());

        mContext = BaseActivity.this;

        intialView(); //truoc

        intialVariables(); // sau

    }

    public abstract int injectLayout(); // setup layout for activity

    public abstract void intialView(); //khoi tao (intial) cac view

    public abstract void intialVariables(); //khoi tao cac tham so

    @Override
    protected void onResume() {
        super.onResume();

        String selectedTheme = sharedPreferences.getString(KEY_CURRENT_THEME, LILAC_THEME);
        if (currentTheme != selectedTheme) {
            recreate();
        }
    }

    private void setAppTheme(String currentTheme) {
        if (currentTheme.equals(MINT_THEME)){
            setTheme(R.style.Theme_App_Mint);
        }else {
            setTheme(R.style.Theme_App_Lilac);
        }
    }
}
