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
import com.duan1.nhom4.wallpaper.model.CollectionsItem;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<CollectionsItem> items;

    public CollectionAdapter( List<CollectionsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       CollectionsItem collectionsItem = items.get(position);
       holder.name01.setText(collectionsItem.getName());
       holder.note01.setText(collectionsItem.getNote());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView01,imgIcon01;
        TextView name01,note01;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView01 = itemView.findViewById(R.id.imgV01);
            imgIcon01 = itemView.findViewById(R.id.imgIcon01);
            name01 = itemView.findViewById(R.id.tvName01);
            note01 = itemView.findViewById(R.id.tvNote01);

        }
    }
}
