package com.duan1.nhom4.wallpaper.adapter;

import android.content.Context;
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
import com.duan1.nhom4.wallpaper.model.cate.CATEGORY;
import com.duan1.nhom4.wallpaper.uis.activities.CollectionList;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<CATEGORY> items;
    private Context mContext;

    public CollectionAdapter(Context context, List<CATEGORY> items) {
        this.items = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CATEGORY collectionsItem = items.get(position);
        holder.name01.setText(collectionsItem.getCategoryName());
        Glide
                .with(mContext)
                .load(collectionsItem.getCategoryImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.image_loader))
                .into(holder.imageView01);

        holder.imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(CollectionList.createIntent(mContext,collectionsItem.getCategoryName(), collectionsItem.getCid()));
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
