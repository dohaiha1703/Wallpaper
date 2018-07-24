package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.DowloadRecycelAdapter;
import com.duan1.nhom4.wallpaper.model.RecycelViewDowload;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends BaseActivity {
    private RecyclerView recyclerPlace;
    private List<RecycelViewDowload> recycelViews;
    private DowloadRecycelAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int injectLayout() {
        return R.layout.activity_favorite;

    }

    @Override
    public void intialView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Favorite");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        recyclerPlace = findViewById(R.id.recyclerView);
        recycelViews = new ArrayList<>();
        adapter = new DowloadRecycelAdapter(recycelViews);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerPlace.setLayoutManager(layoutManager1);
        recyclerPlace.setAdapter(adapter);
        fakeData();

    }

    @Override
    public void intialVariables() {

    }

    public void fakeData() {
        for (int i = 0; i < 40; i++) {
            RecycelViewDowload recycelView = new RecycelViewDowload("", "", "");
            recycelViews.add(recycelView);
        }
        adapter.notifyDataSetChanged();
    }
}