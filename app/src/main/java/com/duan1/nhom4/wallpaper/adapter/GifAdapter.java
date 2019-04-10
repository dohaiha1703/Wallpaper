package com.duan1.nhom4.wallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.database.DataBaseManager;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.model.gif.GifImage;
import com.duan1.nhom4.wallpaper.uis.activities.HomeDetailActivity;

import java.util.List;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {

    private Context mContext;
    private List<GifImage> mGIFS;

    public GifAdapter(Context context, List<GifImage> GIFS) {
        mContext = context;
        mGIFS = GIFS;
    }

    public void addList(List<GifImage> gifImages){
        mGIFS = gifImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_gif, parent, false);
        return new GifAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GifImage gif = mGIFS.get(position);

        Glide
                .with(mContext).asBitmap()
                .load(gif.getGifImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.image_loader))
                .into(holder.imgItemHome);

        holder.imgItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeDetailActivity.class);
                intent.putExtra("img_url", gif.getGifImage());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        DataBaseManager dbManager = new DataBaseManager(mContext);
        List<FavoriteModel> favoriteModels = dbManager.getAllFavorite();
        for (int i = 0; i < favoriteModels.size(); i++) {
            if (gif.getGifImage().equals(favoriteModels.get(i).getFavoriteImage())) {
                gif.setFavorite(true);
            }
        }

        if (gif.isFavorite() == true) {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        } else {
            holder.imgFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return mGIFS.size() > 0 ? mGIFS.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgItemHome;
        public ImageView imgFavorite;

        public ViewHolder(View itemView) {
            super(itemView);

            imgItemHome = itemView.findViewById(R.id.iv_thumb_gif);
            imgFavorite = itemView.findViewById(R.id.iv_favorite);
        }
    }
}
