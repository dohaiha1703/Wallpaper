package com.duan1.nhom4.wallpaper.uis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    public ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(injectLayout());

        mContext = BaseActivity.this;

        intialView(); //truoc

        intialVariables(); // sau

    }

    public abstract int injectLayout(); // setup layout for activity

    public abstract void intialView(); //khoi tao (intial) cac view

    public abstract void intialVariables(); //khoi tao cac tham so

    public void showProgressDialog(){
        if (dialog == null){
            try {
                dialog = new ProgressDialog(mContext);
                dialog.show();
                dialog.setCancelable(false);

            }catch (Exception e){
                dialog = new ProgressDialog(this.getParent());
                dialog.show();
                dialog.setCancelable(false);
            }
        }
    }

    public void closeProgressDialog(){
        if(dialog != null){
            dialog.cancel();
            dialog = null;
        }
    }
}
