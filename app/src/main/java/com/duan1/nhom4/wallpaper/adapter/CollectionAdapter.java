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

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private Context context;
    private List<String> stringList;

    public CollectionAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView01.setImageResource(R.drawable.oto02);
        holder.imgIcon01.setImageResource(R.drawable.ic_action_star_0);
        holder.name01.setText("Cars");
        holder.note01.setText("noteeeeeeeeeeeeeeeeeeeeee thong tin");
    }

    @Override
    public int getItemCount() {
        return 10;
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
