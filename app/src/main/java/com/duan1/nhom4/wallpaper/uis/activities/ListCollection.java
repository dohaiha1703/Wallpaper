package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.ListCollectionAdapter;
import com.duan1.nhom4.wallpaper.model.ListCollectionItem;
import com.duan1.nhom4.wallpaper.rest.GetCollectionRestClient;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;

public class ListCollection extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<ListCollectionItem> collectionItems;
    private ListCollectionAdapter adapter;
    private List<String> links;
    private String idPost, namePost;
    private ProgressDialog dialog;


    @Override
    public int injectLayout() {
        return R.layout.activity_list_collection;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarListCollection);
        recyclerView = findViewById(R.id.recyclerViewListCollection);
        collectionItems = new ArrayList<>();
        adapter = new ListCollectionAdapter(ListCollection.this, collectionItems);
        links = new ArrayList<>();
        idPost = null;
        namePost = null;
        dialog = new ProgressDialog(this);
    }

    @Override
    public void intialVariables() {
        getIncomingData();
        toolbar.setTitle(namePost);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //quay lại trang vừa ấn
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        fakeData();
        getLinkMedia();
        recyclerView.setAdapter(adapter);
        showSpinerProgress();
    }

    public void fakeData() {
        for (int i = 0; i < links.size(); i++) {
            ListCollectionItem item = new ListCollectionItem(links.get(i));
            collectionItems.add(item);
        }
        Log.e("sizeItem", collectionItems.size() + "");

        Log.e("sizeLink", links.size() + "");
        adapter.notifyDataSetChanged();

    }

    public void getLinkMedia() {
        retrofit2.Call<JsonElement> call = GetCollectionRestClient.getCollectionApiInterface().
                getMediaForCollection(idPost, "100");

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(retrofit2.Call<JsonElement> call, Response<JsonElement> response) {

                JsonElement jsonElement = response.body();
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                if (jsonArray.size() > 0) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject objectImg = jsonArray.get(i).getAsJsonObject();
                        String link = objectImg.get("source_url").getAsString();

                        JsonObject titleObject = objectImg.getAsJsonObject("title");
                        String name = titleObject.get("rendered").getAsString();
                        links.add(link);
                    }
                    fakeData();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<JsonElement> call, Throwable t) {
                Toast.makeText(ListCollection.this, "Error Loading", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getIncomingData(){
        if (getIntent().hasExtra("collection_id") && getIntent().hasExtra("collection_name")) {
            idPost = getIntent().getStringExtra("collection_id");
            namePost = getIntent().getStringExtra("collection_name");

        }
    }

    public void showSpinerProgress() {

        //lap thong tin
//        dialog.setTitle("open");
        dialog.setMessage("Loading");
//
//        dialog.setButton(ProgressDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

        //thiet lap k the huy - co the huy
        dialog.setCancelable(true);

        //show dialog
        dialog.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 20000000);
    }

}
