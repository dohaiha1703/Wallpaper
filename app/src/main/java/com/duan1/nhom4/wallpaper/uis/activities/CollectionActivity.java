package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.CollectionAdapter;
import com.duan1.nhom4.wallpaper.model.CollectionsItem;
import com.duan1.nhom4.wallpaper.model.HomeItem;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private CollectionAdapter adapter;
    private List<CollectionsItem> items;


    @Override
    public int injectLayout() {
        return R.layout.activity_collection;
    }

    @Override
    public void intialView() {

        toolbar = findViewById(R.id.tollbarCollectionActivity);
        recyclerView = findViewById(R.id.recyclerView01);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        adapter = new CollectionAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setTitle("Collection");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fakeData();
    }

    private void fakeData() {
        for (int i = 0; i < 40; i++) {
            CollectionsItem item = new CollectionsItem("name", "","note");
            items.add(item);
        }
        adapter.notifyDataSetChanged();
    }
}
