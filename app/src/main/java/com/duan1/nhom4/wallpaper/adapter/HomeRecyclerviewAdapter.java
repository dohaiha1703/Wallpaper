package com.duan1.nhom4.wallpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.HomeItem;

import java.util.List;

public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder> {

    private List<HomeItem> homeItems;

    public HomeRecyclerviewAdapter(List<HomeItem> homeItems) {
        this.homeItems = homeItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_home_2, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeItem homeItem = homeItems.get(position);
        holder.tvItemHome.setText(homeItem.getTv());

    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgItemHome;
        public TextView tvItemHome;

        public ViewHolder(View itemView) {
            super(itemView);


            imgItemHome = itemView.findViewById(R.id.imgItemHome);
            tvItemHome = itemView.findViewById(R.id.tvItemHome);
        }
    }
}
