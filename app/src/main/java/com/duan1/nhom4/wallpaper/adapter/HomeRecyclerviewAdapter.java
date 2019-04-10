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
import com.bumptech.glide.request.RequestOptions;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.model.image.HDWALLPAPER;
import com.duan1.nhom4.wallpaper.uis.activities.HomeDetailActivity;

import java.util.List;

public class HomeRecyclerviewAdapter extends RecyclerView.Adapter<HomeRecyclerviewAdapter.ViewHolder> {

    private List<HDWALLPAPER> homeItems;
    private Context mContext;


    public HomeRecyclerviewAdapter(List<HDWALLPAPER> homeItems, Context mContext) {
        this.homeItems = homeItems;
        this.mContext = mContext;

    }

    public void getImageList(List<HDWALLPAPER> list){
        homeItems = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_home, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final HDWALLPAPER hdwallpaper = homeItems.get(position);

        holder.tvViewNumber.setText(hdwallpaper.getTotalViews());

        Glide
                .with(mContext)
                .load(hdwallpaper.getWallpaperImageThumb())
                .apply(new RequestOptions().placeholder(R.drawable.image_loader))
                .into(holder.imgItemHome);

        holder.imgItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeDetailActivity.class);
                intent.putExtra("img_url", hdwallpaper.getWallpaperImage());
                intent.putExtra("wallpaper_id", hdwallpaper.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        DataBaseManager dbManager = new DataBaseManager(mContext);
        List<FavoriteModel> favoriteModels = dbManager.getAllFavorite();
        for (int i = 0; i < favoriteModels.size(); i++) {
            if (hdwallpaper.getWallpaperImage().equals(favoriteModels.get(i).getFavoriteImage())) {
                hdwallpaper.setFavorite(true);
            }
        }

        if (hdwallpaper.isFavorite() == true){
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        }else {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        }
    }


    @Override
    public int getItemCount() {
        return homeItems.size() > 0 ? homeItems.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgItemHome;
        public ImageView imgFavorite;
        public TextView tvViewNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            imgItemHome = itemView.findViewById(R.id.iv_thumb_home);
            imgFavorite = itemView.findViewById(R.id.iv_favorite);
            tvViewNumber = itemView.findViewById(R.id.tv_view_number);
        }
    }
}
