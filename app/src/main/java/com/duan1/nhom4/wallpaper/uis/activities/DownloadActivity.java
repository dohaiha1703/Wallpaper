
package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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

public class DownloadActivity extends BaseActivity {
    private RecyclerView recyclerPlace;
    private List<RecycelViewDowload> recycelViews;
    private DowloadRecycelAdapter adapter;
    private Toolbar toolbar;

    @Override
    public int injectLayout() {
        return R.layout.activity_download;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarDownloadActivity);


        recyclerPlace = findViewById(R.id.recyclerView);
        recycelViews = new ArrayList<>();
        adapter = new DowloadRecycelAdapter(recycelViews, getApplicationContext());
    }

    @Override
    public void intialVariables() {

        toolbar.setTitle("Download");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this,3);
        recyclerPlace.setLayoutManager(layoutManager1);
        recyclerPlace.setAdapter(adapter);
        fakeData();
    }

    public void fakeData() {
        for (int i = 0; i < 40; i++) {
            RecycelViewDowload recycelView = new RecycelViewDowload("", "", "");
            recycelViews.add(recycelView);
        }
        adapter.notifyDataSetChanged();
    }
}

