
package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.DowloadRecycelAdapter;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.DownloadModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends BaseActivity {
    private RecyclerView recyclerPlace;
    private DowloadRecycelAdapter adapter;
    private Toolbar toolbar;
    private List<DownloadModel> downloadModels;
    private DataBaseManager dbManager;

    @Override
    public int injectLayout() {
        return R.layout.activity_download;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarDownloadActivity);
        dbManager = new DataBaseManager(DownloadActivity.this);

        recyclerPlace = findViewById(R.id.recyclerView);
        downloadModels = dbManager.getAllDownload();
        adapter = new DowloadRecycelAdapter(getApplicationContext(), downloadModels);

    }

    @Override
    public void intialVariables() {

        toolbar.setTitle("Download");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(DownloadActivity.this, HomeActivity.class));
            }
        });

        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, 3);
        recyclerPlace.setLayoutManager(layoutManager1);
        recyclerPlace.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

