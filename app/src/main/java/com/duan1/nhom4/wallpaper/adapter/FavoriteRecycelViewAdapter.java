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
import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.FavoriteModel;
import com.duan1.nhom4.wallpaper.uis.activities.CollectionActivity;
import com.duan1.nhom4.wallpaper.uis.activities.DownloadDetailActivity;
import com.duan1.nhom4.wallpaper.uis.activities.FavoriteActivity;
import com.duan1.nhom4.wallpaper.uis.activities.FavoriteDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteRecycelViewAdapter extends RecyclerView.Adapter<FavoriteRecycelViewAdapter.ViewHoder> {

    private List<FavoriteModel> listFavoriteLink;
    private Context mContext;


    public FavoriteRecycelViewAdapter(List<FavoriteModel> listFavoriteLink, Context mContext) {
        this.listFavoriteLink = listFavoriteLink;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_download_favorite, parent, false);
        return new ViewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, final int position) {

        final FavoriteModel model = listFavoriteLink.get(position);

        Picasso
                .with(mContext)
                .load(model.getFavoriteImage())
                .into(holder.imgItemFavorite);



        holder.imgItemFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FavoriteDetailActivity.class);
                intent.putExtra("img_url", model.getFavoriteImage());
                intent.putExtra("img_pos", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFavoriteLink.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView imgItemFavorite;

        public ViewHoder(View itemView) {
            super(itemView);
            imgItemFavorite = itemView.findViewById(R.id.imgBackground);
        }
    }

}
