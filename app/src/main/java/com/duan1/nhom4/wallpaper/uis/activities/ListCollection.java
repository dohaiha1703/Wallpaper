package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.ListCollectionAdapter;
import com.duan1.nhom4.wallpaper.model.CollectionsItem;
import com.duan1.nhom4.wallpaper.model.ListCollectionItem;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ListCollection extends BaseActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<ListCollectionItem> collectionItems;
    private ListCollectionAdapter adapter;


    @Override
    public int injectLayout() {
        return R.layout.activity_list_collection;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarListCollection);
        recyclerView = findViewById(R.id.recyclerViewListCollection);
        collectionItems = new ArrayList<>();
        adapter = new ListCollectionAdapter(getApplicationContext(),collectionItems);
    }

    @Override
    public void intialVariables() {
        toolbar.setTitle("Chi Tiết");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //quay lại trang vừa ấn
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fakeData();
    }
    public void fakeData(){
        for (int i=0; i< 40; i++){
            ListCollectionItem listCollectionItem = new ListCollectionItem("");
            collectionItems.add(listCollectionItem);
        }
        adapter.notifyDataSetChanged();
    }

}
