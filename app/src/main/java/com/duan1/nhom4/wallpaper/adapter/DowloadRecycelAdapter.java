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
import com.duan1.nhom4.wallpaper.model.RecycelViewDowload;
import com.duan1.nhom4.wallpaper.uis.activities.DownloadDetailActivity;

import java.util.List;

public class DowloadRecycelAdapter extends RecyclerView.Adapter<DowloadRecycelAdapter.ViewHoder> {
    private List<RecycelViewDowload> dowloadList;
    private Context mContext;
    public DowloadRecycelAdapter(Context context, List<RecycelViewDowload> dowloadList){
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
        RecycelViewDowload recycelViewDowload = dowloadList.get(position);

        holder.imgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DownloadDetailActivity.class);
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
        private ImageView imgImage;
        public ViewHoder(View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgBackground);
        }
    }
}
