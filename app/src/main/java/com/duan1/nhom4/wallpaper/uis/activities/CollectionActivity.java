package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.CollectionAdapter;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

public class CollectionActivity extends BaseActivity {
    CollectionAdapter collectionAdapter;

    @Override
    public int injectLayout() {
        return R.layout.activity_collection;
    }

    @Override
    public void intialView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView01);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        collectionAdapter = new CollectionAdapter(this,null);
        recyclerView.setAdapter(collectionAdapter);
    }

    @Override
    public void intialVariables() {

    }
}
