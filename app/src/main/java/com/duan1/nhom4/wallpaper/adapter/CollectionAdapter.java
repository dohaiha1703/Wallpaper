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

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.CollectionsItem;
import com.duan1.nhom4.wallpaper.uis.activities.CollectionList;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<CollectionsItem> items;
    private Context mContext;

    public CollectionAdapter(List<CollectionsItem> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CollectionsItem collectionsItem = items.get(position);
        holder.name01.setText(collectionsItem.getName());
        holder.note01.setText(collectionsItem.getNote());
        holder.imageView01.setImageResource(collectionsItem.getImg());

        holder.imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (collectionsItem.getName().equalsIgnoreCase("girl")) {
//                    mContext.startActivity(CollectionList.createIntent(mContext, "gril"));
//                    return;
//                }
                mContext.startActivity(CollectionList.createIntent(mContext, collectionsItem.getName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView01;
        TextView name01, note01;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView01 = itemView.findViewById(R.id.imgV01);
            name01 = itemView.findViewById(R.id.tvName01);
            note01 = itemView.findViewById(R.id.tvNote01);


        }
    }
}
