package com.duan1.nhom4.wallpaper.uis.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.HomeRecyclerviewAdapter;
import com.duan1.nhom4.wallpaper.adapter.ListCollectionAdapter;
import com.duan1.nhom4.wallpaper.model.HDWALLPAPER;
import com.duan1.nhom4.wallpaper.model.ListCollectionItem;
import com.duan1.nhom4.wallpaper.model.ListImage;
import com.duan1.nhom4.wallpaper.rest.GetCollectionRestClient;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Callback;
import retrofit2.Response;

public class CollectionList extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<ListCollectionItem> collectionItems;
    private HomeRecyclerviewAdapter adapter;
    private List<String> links;
    private String idPost, namePost;
    private ProgressDialog dialog;
    private final static String INTENT_KEY_COLLECTION_LIST = "INTENT_KEY_COLLECTION_LIST";
    private List<HDWALLPAPER> mWallpapers;
    private String url = "http://www.tapetee.com/api.php?latest";


    public static Intent createIntent(Context context, String str) {
        Intent intent = new Intent(context, CollectionList.class);
        intent.putExtra(INTENT_KEY_COLLECTION_LIST, str);
        return intent;
    }

    private void getIncomingData() {
        if (getIntent().hasExtra(INTENT_KEY_COLLECTION_LIST)) {
            namePost = getIntent().getStringExtra(INTENT_KEY_COLLECTION_LIST);
        }
    }


    @Override
    public int injectLayout() {
        return R.layout.activity_list_collection;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.toolbarListCollection);
        recyclerView = findViewById(R.id.recyclerViewListCollection);
        links = new ArrayList<>();
        namePost = null;
        dialog = new ProgressDialog(this);
    }

    @Override
    public void intialVariables() {
        getIncomingData();
        toolbar.setTitle(namePost);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //quay lại trang vừa ấn
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
//        fakeData();
//        getLinkMedia();
        recyclerView.setAdapter(adapter);
        showSpinerProgress();

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(url);

    }

    private void fakeData() {
        for (int i = 0; i < links.size(); i++) {
            ListCollectionItem item = new ListCollectionItem(links.get(i));
            collectionItems.add(item);
        }
        Log.e("sizeItem", collectionItems.size() + "");

        Log.e("sizeLink", links.size() + "");
        adapter.notifyDataSetChanged();

    }

    private void getLinkMedia() {
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
                Toast.makeText(CollectionList.this, "Error Loading", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showSpinerProgress() {

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

    class MyAsyncTask extends AsyncTask<String, Long, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        // ko thao tac vs cac thanh phan trong UI (Giao dien)
        @Override
        protected String doInBackground(String... arrays) {

            try {

                // doi tuong URL, dung de khoi tao dia chi request
                URL url = new URL(arrays[0]);

                // mo ket noi toi dia chi url
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                // doc du lieu thong qua InputStream
                InputStream inputStream = httpURLConnection.getInputStream();

                // doc du lieu
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder total = new StringBuilder();

                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line).append('\n');

                }

                return total.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        // thao tac vs cac than phan trong UI
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null) {
                ListImage example = new Gson().fromJson(s, ListImage.class);
                mWallpapers = example.getHDWALLPAPER();
                List<HDWALLPAPER> hdwallpapers = new ArrayList<>();

                for (HDWALLPAPER x : mWallpapers) {
                    Log.d("haiha123", x.getCategoryName() + " vs " + namePost);
                    if (x.getCategoryName().equalsIgnoreCase(namePost)){
                        hdwallpapers.add(x);
                    }
                }

                Log.d("haiha123", hdwallpapers.size()+"");


                adapter = new HomeRecyclerviewAdapter(hdwallpapers, CollectionList.this);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CollectionList.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                dialog.dismiss();

            }

        }

    }

}
