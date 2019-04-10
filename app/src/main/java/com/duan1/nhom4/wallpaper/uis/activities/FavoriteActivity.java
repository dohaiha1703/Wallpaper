package com.duan1.nhom4.wallpaper.uis.activities;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.FavoriteRecycelViewAdapter;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.List;

public class FavoriteActivity extends BaseActivity {
    private RecyclerView recyclerPlace;
    private List<FavoriteModel> favoriteModels;
    private DataBaseManager dbManager;
    private FavoriteRecycelViewAdapter adapter;
    private Toolbar toolbar;


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
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void intialVariables() {

        toolbar.setTitle("Favorite");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(FavoriteActivity.this, HomeActivity.class));
            }
        });


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerPlace.setLayoutManager(layoutManager);
        recyclerPlace.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}