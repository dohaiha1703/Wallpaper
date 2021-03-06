package com.duan1.nhom4.wallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.HomeItem;
import com.duan1.nhom4.wallpaper.uis.activities.HomeDetailActivity;

import java.util.List;

public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder> {

    private List<HomeItem> homeItems;
    private Context mContext;
    boolean check = true;


    public HomeRecyclerviewAdapter(List<HomeItem> homeItems, Context mContext) {
        this.homeItems = homeItems;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_home_2, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final HomeItem homeItem = homeItems.get(position);
        holder.tvItemHome.setText(homeItem.getTv());

        Glide
                .with(mContext)
                .load(homeItem.getImg())
                .into(holder.imgItemHome);

        holder.imgItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeDetailActivity.class);
                intent.putExtra("img_url", homeItem.getImg());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

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
