package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.CollectionAdapter;
import com.duan1.nhom4.wallpaper.model.cate.CATEGORY;
import com.duan1.nhom4.wallpaper.model.cate.CateItem;
import com.duan1.nhom4.wallpaper.rest.GetAllImageRestClient;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionActivity extends BaseActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private CollectionAdapter adapter;
    private List<CATEGORY> items;


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
    }

    private void getAllCate() {
        Call<JsonElement> call = GetAllImageRestClient.getApiInterface().getCategories();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement jsonElement = response.body();
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                CateItem cateItem = new Gson().fromJson(jsonObject, CateItem.class);
                items = cateItem.getCATEGORY();

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CollectionActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}
