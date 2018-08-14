package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.CollectionAdapter;
import com.duan1.nhom4.wallpaper.model.CollectionsItem;
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
        adapter = new CollectionAdapter(items, CollectionActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void intialVariables() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitle("Collection");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fakeData();
    }

    private void fakeData() {
        CollectionsItem item1 = new CollectionsItem("Anime",
                R.drawable.cate_anime, "");

        CollectionsItem item2 = new CollectionsItem("Girl",
                R.drawable.cate_girl, "");

        CollectionsItem item3 = new CollectionsItem("3D",
                R.drawable.cate_3d, "");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        adapter.notifyDataSetChanged();
    }
}
