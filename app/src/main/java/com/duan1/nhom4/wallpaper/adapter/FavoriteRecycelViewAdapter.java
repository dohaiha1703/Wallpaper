package com.duan1.nhom4.wallpaper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.RecycelViewFavorite;

import java.util.List;

public class FavoriteRecycelViewAdapter extends RecyclerView.Adapter<FavoriteRecycelViewAdapter.ViewHoder> {
    private List<RecycelViewFavorite> favoriteList;
    public FavoriteRecycelViewAdapter(List<RecycelViewFavorite> favorites){
        this.favoriteList = favorites;

    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recycel, parent, false);
        return new ViewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        RecycelViewFavorite viewFavorite = favoriteList.get(position);
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHoder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgBackground);
        }
    }
}
