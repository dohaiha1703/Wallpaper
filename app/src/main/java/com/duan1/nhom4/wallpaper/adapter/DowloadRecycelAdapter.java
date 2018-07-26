package com.duan1.nhom4.wallpaper.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duan1.nhom4.wallpaper.R;
import com.duan1.nhom4.wallpaper.model.RecycelViewDowload;

import java.util.List;

public class DowloadRecycelAdapter extends RecyclerView.Adapter<DowloadRecycelAdapter.ViewHoder> {
    private List<RecycelViewDowload> dowloadList;
    public DowloadRecycelAdapter(List<RecycelViewDowload> dowloadList){
        this.dowloadList = dowloadList;
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_download_favorite, parent, false);
        return new ViewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        RecycelViewDowload recycelViewDowload = dowloadList.get(position);
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
