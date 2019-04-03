package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.AboutUsActivity;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.SettingActivity;
import com.duan1.nhom4.wallpaper.adapter.CollectionAdapter;
import com.duan1.nhom4.wallpaper.adapter.FavoriteRecycelViewAdapter;
import com.duan1.nhom4.wallpaper.adapter.HomeRecyclerviewAdapter;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.CollectionsItem;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.model.HDWALLPAPER;
import com.duan1.nhom4.wallpaper.model.ListImage;
import com.duan1.nhom4.wallpaper.rest.GetAllImageRestClient;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
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
    private HomeRecyclerviewAdapter adapter;
    private List<String> listLink;
    private List<String> listNameImage;
    private ProgressDialog dialog;
    private List<HDWALLPAPER> mWallpapers;
    private CollectionAdapter adapterCollection;
    private List<CollectionsItem> items;
    private TextView toolbarTitle;
    private List<FavoriteModel> favoriteModels;
    private DataBaseManager dbManager;
    private FavoriteRecycelViewAdapter adapterFavorite;


    @Override
    public int injectLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.tbHome);
        navigationView = findViewById(R.id.navHome);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbarTitle = findViewById(R.id.toolbar_title);

        recyclerView = findViewById(R.id.rvHomeItem);
        recyclerView.setHasFixedSize(true);

        listLink = new ArrayList<>();
        listNameImage = new ArrayList<>();

        dialog = new ProgressDialog(HomeActivity.this);

        items = new ArrayList<>();
        adapterCollection = new CollectionAdapter(items, HomeActivity.this);

        dbManager = new DataBaseManager(this);
    }

    @Override
    public void intialVariables() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        createToolBarAndNavgation();
        if (networkInfo.isConnected() || networkInfo2.isConnected()) {
            getLinkAllMediaRetrofit();
        } else Toast.makeText(this, "No Internet!", Toast.LENGTH_LONG).show();
        showSpinerProgress();
    }

    private void createToolBarAndNavgation() {
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
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
                    case R.id.categories:
                        collectionClicked();
                        break;
//
//                    case R.id.download:
//                        startActivity(new Intent(HomeActivity.this, DownloadActivity.class));
//                        break;

                    case R.id.favorite:
                        favoriteClicked();
                        break;

                    case R.id.more_app:
                        String url = "https://play.google.com/store/apps/developer?id=dotPLAYS";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;

                    case R.id.rate_app:
                        String urlApp = "https://play.google.com/store/apps/details?id=com.duan1.nhom4.wallpaper";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(urlApp));
                        startActivity(intent);
                        break;

                    case R.id.about:
                        Intent intent2 = new Intent(HomeActivity.this, AboutUsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.latest:
                        Intent intent1 = new Intent(HomeActivity.this, HomeActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                        break;

                    case R.id.privacy_policy:
                        showDialogPrivacyPolicy();
                        break;

                    case R.id.setting:
                        startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                        break;

                    default:
                        Toast.makeText(mContext, "clicked" + item.getItemId(), Toast.LENGTH_SHORT).show();
                        break;
                }

                drawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        showDialogExit();
    }

    private void showAlertDialog() {
        //dinh nghia dialog
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);

        //thiet lap header, body, button
//        builder.setTitle("AlerDialog");
        builder.setMessage("Find out more on our website: \nhttp://nhom4.dotplays.com/");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(HomeActivity.this, "close", Toast.LENGTH_SHORT).show();
            }
        });
        //show
        builder.show();
    }

    private void showDialogPrivacyPolicy() {
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(HomeActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.dialog_privacy_policy, null);
        mbuilder.setView(mview);
        Button buttonOK = mview.findViewById(R.id.buttonOK);
        final AlertDialog dialog = mbuilder.create();
        dialog.show();

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showDialogExit() {
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(HomeActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.dialog_exit, null);
        Button buttonYes = mview.findViewById(R.id.button_yes);
        Button buttonRateApp = mview.findViewById(R.id.button_rate_app);
        mbuilder.setView(mview);
        final AlertDialog dialog = mbuilder.create();
        dialog.show();

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                dialog.dismiss();
            }
        });

        buttonRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlApp = "https://play.google.com/store/apps/details?id=com.duan1.nhom4.wallpaper";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlApp));
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    private void showAlertDialogSignOut() {
        //dinh nghia dialog
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);

        //thiet lap header, body, button
//        builder.setTitle("AlerDialog");
        builder.setMessage("Do you want to Sign out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //show
        builder.show();
    }


//    private void fakeData() {
//        for (int i = 0; i < 30; i++) {
//            HomeItem item = new HomeItem(listLink.get(i), listNameImage.get(i));
//            items.add(item);
//        }
//        adapter.notifyDataSetChanged();
//    }

    public void openSearchActivity(View view) {
        startActivity(new Intent(HomeActivity.this, SearchActivity.class));
    }

    private void getLinkAllMedia() {
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
//                fakeData();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("sizeLink", "failed");
            }
        });
    }

    private void getLinkAllMediaRetrofit() {
        Call<JsonElement> call = GetAllImageRestClient.getApiInterface().getAllMedia();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement jsonElement = response.body();
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                ListImage example = new Gson().fromJson(jsonObject, ListImage.class);
                mWallpapers = example.getHDWALLPAPER();

                adapter = new HomeRecyclerviewAdapter(mWallpapers, getApplicationContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomeActivity.this, 2);
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

    private void showSpinerProgress() {

        //lap thong tin
//        dialog.setTitle("open");
        dialog.setMessage("Loading");
        dialog.setTitle("Openning");
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
        }, 5000);
    }

    private void collectionClicked() {
        toolbarTitle.setText("Categories");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCollection);
        fakeData();

    }

    private void fakeData() {
        items.clear();
        CollectionsItem item1 = new CollectionsItem("Anime",
                R.drawable.cate_anime, "");

        CollectionsItem item2 = new CollectionsItem("Girl",
                R.drawable.cate_girl, "");

        CollectionsItem item3 = new CollectionsItem("3D",
                R.drawable.cate_3d, "");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        adapterCollection.notifyDataSetChanged();
    }

    private void favoriteClicked(){
        toolbarTitle.setText("Favorite");
        favoriteModels = dbManager.getAllFavorite();
        adapterFavorite = new FavoriteRecycelViewAdapter(favoriteModels, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterFavorite);
        adapter.notifyDataSetChanged();
    }
}
