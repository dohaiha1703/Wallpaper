package com.duan1.nhom4.wallpaper.uis.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.adapter.HomeRecyclerviewAdapter;
import com.duan1.nhom4.wallpaper.model.HomeItem;
import com.duan1.nhom4.wallpaper.uis.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private List<HomeItem> items;
    private HomeRecyclerviewAdapter adapter;


    @Override
    public int injectLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void intialView() {
        toolbar = findViewById(R.id.tbHome);
        navigationView = findViewById(R.id.navHome);
        drawerLayout = findViewById(R.id.drawerLayout);

        recyclerView = findViewById(R.id.rvHomeItem);
        items = new ArrayList<>();
        adapter = new HomeRecyclerviewAdapter(items);

    }

    @Override
    public void intialVariables() {
        createToolBarAndNavgation();
        createRecyclerView();
    }

    public void createToolBarAndNavgation() {
        toolbar.setTitle("New Wallpaper");
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.collection:
                        Toast.makeText(mContext, "collection", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.download:
                        Toast.makeText(mContext, "download", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.favorite:
                        Toast.makeText(mContext, "favorite", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.about:
                        Toast.makeText(mContext, "about", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.sign_out:
                        Toast.makeText(mContext, "exit", Toast.LENGTH_SHORT).show();
                        break;
                }

                drawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });
    }

    public void createRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fakeData();
    }

    private void fakeData() {
        for (int i = 0; i < 40; i++) {
            HomeItem item = new HomeItem("", "item "+i);
            items.add(item);
        }
        adapter.notifyDataSetChanged();
    }
}
