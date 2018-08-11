package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.adapter.HomeRecyclerviewAdapter;
import com.duan1.nhom4.wallpaper.model.HomeItem;
import com.duan1.nhom4.wallpaper.rest.GetAllImageRestClient;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.duan1.nhom4.wallpaper.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private List<HomeItem> items;
    private HomeRecyclerviewAdapter adapter;
    private List<String> listLink;
    private List<String> listNameImage;
    private TextView getUserName;
    private ProgressDialog dialog;


    @Override
    public int injectLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.tbHome);
        navigationView = findViewById(R.id.navHome);
        drawerLayout = findViewById(R.id.drawerLayout);
        getUserName = findViewById(R.id.tvGetUserName);

        recyclerView = findViewById(R.id.rvHomeItem);
        items = new ArrayList<>();
        adapter = new HomeRecyclerviewAdapter(items, getApplicationContext());

        listLink = new ArrayList<>();
        listNameImage = new ArrayList<>();

        dialog = new ProgressDialog(HomeActivity.this);
    }

    @Override
    public void intialVariables() {
        createToolBarAndNavgation();
        createRecyclerView();
        getLinkAllMedia();
        showSpinerProgress();


    }

    public void createToolBarAndNavgation() {
        toolbar.setTitle("New Wallpaper");
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);

//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        if (getIntent().hasExtra("user_name")) {
//                            getUserName.setText("Welcome " + getIntent().getStringExtra("user_name"));
//                        }
//                    }
//                }, 1000);


            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.collection:
                        startActivity(new Intent(HomeActivity.this, CollectionActivity.class));
                        break;

                    case R.id.download:
                        startActivity(new Intent(HomeActivity.this, DownloadActivity.class));
                        break;


                    case R.id.favorite:
                        startActivity(new Intent(HomeActivity.this, FavoriteActivity.class));
                        break;


                    case R.id.about:
                        showAlertDialog();
                        break;

                    case R.id.sign_out:
                        Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                        startActivity(intent);
                        break;
                }

                drawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });


    }

    public void showAlertDialog() {
        //dinh nghia dialog
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);

        //thiet lap header, body, button
//        builder.setTitle("AlerDialog");
        builder.setMessage("We are Group 4. \nSubject Project 1.\nClass PT13251-MOB");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(HomeActivity.this, "close", Toast.LENGTH_SHORT).show();
            }
        });

        //show
        builder.show();
    }

    public void createRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void fakeData() {
        for (int i = 0; i < 30; i++) {
            HomeItem item = new HomeItem(listLink.get(i), listNameImage.get(i));
            items.add(item);
        }
        adapter.notifyDataSetChanged();
    }

    public void openSearchActivity(View view) {
        startActivity(new Intent(HomeActivity.this, SearchActivity.class));
    }

    public void getLinkAllMedia() {
        Call<JsonElement> call = GetAllImageRestClient.getApiInterface().getAllMedia();

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement jsonElement = response.body();
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                if (jsonArray.size() > 0) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject objectImg = jsonArray.get(i).getAsJsonObject();
                        String link = objectImg.get("source_url").getAsString();

                        JsonObject titleObject = objectImg.getAsJsonObject("title");
                        String name = titleObject.get("rendered").getAsString();
                        listLink.add(link);
                        listNameImage.add(name);
                    }
                }
                Log.e("sizeLink", listLink.size() + "");
                Log.e("sizeName", listNameImage.size() + "");
                fakeData();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("sizeLink", "failed");
            }
        });
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
        dialog.setCancelable(false);

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
