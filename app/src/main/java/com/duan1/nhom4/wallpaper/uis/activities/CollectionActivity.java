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
        adapter = new CollectionAdapter(items, CollectionActivity.this);
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
        CollectionsItem item1 = new CollectionsItem("Nature",
                R.drawable.nature_background, "Forest, Coast, Lake, ...");

        CollectionsItem item2 = new CollectionsItem("Movies",
                R.drawable.movies_background, "Drama, Action, Science, ...");

        CollectionsItem item3 = new CollectionsItem("Creative",
                R.drawable.creative_background, "More than you expect");

        CollectionsItem item4 = new CollectionsItem("Lifestyle",
                R.drawable.lifestyle_background, "Lifestyle of everywhere on the world");

        CollectionsItem item5 = new CollectionsItem("Universe",
                R.drawable.universe_background, "Galaxy, Stars, Space ...");

        CollectionsItem item6 = new CollectionsItem("Art",
                R.drawable.art_background, "Beautiful and abstract");

        CollectionsItem item7 = new CollectionsItem("Scenery",
                R.drawable.scenery_background, "Where spectacular");

        CollectionsItem item8 = new CollectionsItem("Others",
                R.drawable.others_background, "Others topics");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        adapter.notifyDataSetChanged();
    }
}
