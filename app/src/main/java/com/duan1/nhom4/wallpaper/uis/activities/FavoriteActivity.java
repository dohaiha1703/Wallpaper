package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.FavoriteRecycelViewAdapter;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends BaseActivity {
    private RecyclerView recyclerPlace;
    private List<FavoriteModel> favoriteModels;
    private FavoriteRecycelViewAdapter adapter;
    private Toolbar toolbar;
    private DataBaseManager dbManager;


    @Override
    public int injectLayout() {
        return R.layout.activity_favorite;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarFavoriteActivity);
        dbManager = new DataBaseManager(FavoriteActivity.this);


        recyclerPlace = findViewById(R.id.recyclerView);
        favoriteModels = dbManager.getAllFavorite();
        adapter = new FavoriteRecycelViewAdapter(favoriteModels, FavoriteActivity.this);
    }

    @Override
    public void intialVariables() {

        toolbar.setTitle("Favorite");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                startActivity(new Intent(FavoriteActivity.this, HomeActivity.class));
            }
        });


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerPlace.setLayoutManager(layoutManager);
        recyclerPlace.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}