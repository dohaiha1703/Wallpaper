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
import com.duan1.nhom4.wallpaper.model.DownloadModel;
import com.duan1.nhom4.wallpaper.uis.activities.DownloadDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DowloadRecycelAdapter extends RecyclerView.Adapter<DowloadRecycelAdapter.ViewHoder> {
    private List<DownloadModel> dowloadList;
    private Context mContext;
    public DowloadRecycelAdapter(Context context, List<DownloadModel> dowloadList){
        this.dowloadList = dowloadList;
        this.mContext = context;
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
        final DownloadModel model = dowloadList.get(position);

        Picasso.with(mContext).load(model.getPlaceImage()).into(holder.imgDownload);


        holder.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DownloadDetailActivity.class);
                intent.putExtra("img_url", model.getPlaceImage());
                intent.putExtra("img_pos", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dowloadList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView imgDownload;
        public ViewHoder(View itemView) {
            super(itemView);
            imgDownload = itemView.findViewById(R.id.imgBackground);
        }
    }
}
