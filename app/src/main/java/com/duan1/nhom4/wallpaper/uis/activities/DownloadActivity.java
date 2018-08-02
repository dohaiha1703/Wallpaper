
package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.DowloadRecycelAdapter;
import com.duan1.nhom4.wallpaper.model.RecycelViewDowload;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends BaseActivity {
    private RecyclerView recyclerPlace;
    private static final String TAG = "DownloadActivity";
    private List<RecycelViewDowload> recycelViews;
    private Context context;
    private DowloadRecycelAdapter adapter;
    private Toolbar toolbar;
//    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    public int injectLayout() {
        return R.layout.activity_download;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarDownloadActivity);

        recyclerPlace = findViewById(R.id.recyclerView);
        recycelViews = new ArrayList<>();
        adapter = new DowloadRecycelAdapter(getApplicationContext(), recycelViews);
    }

    @Override
    public void intialVariables() {

        toolbar.setTitle("Download");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, 3);
        recyclerPlace.setLayoutManager(layoutManager1);
        recyclerPlace.setAdapter(adapter);
        fakeData();
    }

    public void fakeData() {
        for (int i = 0; i < 40; i++) {
            RecycelViewDowload recycelView = new RecycelViewDowload("");
            recycelViews.add(recycelView);
        }
        adapter.notifyDataSetChanged();
    }
}

