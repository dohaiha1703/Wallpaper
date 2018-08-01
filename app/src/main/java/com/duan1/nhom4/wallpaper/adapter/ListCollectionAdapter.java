package com.duan1.nhom4.wallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.CollectionsItem;
import com.duan1.nhom4.wallpaper.model.ItemClickCollection;
import com.duan1.nhom4.wallpaper.model.ListCollectionItem;
import com.duan1.nhom4.wallpaper.uis.activities.CollectionDetailActivity;
import com.duan1.nhom4.wallpaper.uis.activities.DownloadDetailActivity;

import java.util.List;

public class ListCollectionAdapter extends RecyclerView.Adapter<ListCollectionAdapter.ViewHolder>{
    private List<ListCollectionItem> itemList;
    private Context context;
    public ListCollectionAdapter(Context context, List<ListCollectionItem> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_download_favorite,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ListCollectionAdapter.ViewHolder holder, final int position) {
        ListCollectionItem listCollectionItem = itemList.get(position);

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CollectionDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(context, "anh" + (position+1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgBackground);
        }
    }
}


//class ItemCollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//    ImageView imgItemCollection;
//    private ItemClickCollection clickCollection;
//
//    public ItemCollectionViewHolder(View itemView) {
//        super(itemView);
//        imgItemCollection = itemView.findViewById(R.id.imgItemCollection);
//        itemView.setOnClickListener(this);
//
//    }
//
//    public void setImgItemCollection(ItemClickCollection clickCollection){
//        this.clickCollection = clickCollection;
//    }
//
//    @Override
//    public void onClick(View v) {
//        clickCollection.onClick(v,getAdapterPosition(),false);
//    }
//}
