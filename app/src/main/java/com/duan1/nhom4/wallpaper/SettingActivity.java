package com.duan1.nhom4.wallpaper;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class SettingActivity extends BaseActivity {

    private SharedPreferences sharedPreferences;
    private String currentTheme;
    private ImageView mImageView;

    @Override
    public int injectLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void intialView() {
        mImageView = findViewById(R.id.myRoundView);
    }

    @Override
    public void intialVariables() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getString(KEY_CURRENT_THEME, LILAC_THEME);

        Toast.makeText(mContext, currentTheme, Toast.LENGTH_SHORT).show();
        if (currentTheme == MINT_THEME) {
            mImageView.setImageResource(R.drawable.round_pink);
        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ha123", "\n \n"+currentTheme+"\n \n");
                    if (currentTheme.equals(LILAC_THEME)) {
                        mImageView.setImageResource(R.drawable.round_pink);
                        sharedPreferences.edit().putString(KEY_CURRENT_THEME, MINT_THEME).apply();
                    } else {
                        sharedPreferences.edit().putString(KEY_CURRENT_THEME, LILAC_THEME).apply();
                    }
                    recreate();
            }
        });
    }

    public void backPress(View view) {
      onBackPressed();
    }
}
