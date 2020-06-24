package com.sum.corp.imagessubredditviewer.view.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageloader.ImageLoader;
import com.sum.corp.imagessubredditviewer.R;
import com.sum.corp.imagessubredditviewer.data.model.Children;
import com.sum.corp.imagessubredditviewer.databinding.ItemImageLayoutBinding;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.Holder> {

    private OnItemClick itemClick;
    private List<Children> imageList;
    Context context;


    public ImageListAdapter(OnItemClick itemClick, Context context) {
        this.itemClick = itemClick;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemImageLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_image_layout, parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        if (imageList != null) {

            ImageLoader.get(context)
                    .loadUrl(imageList.get(position).getChildrenData().getThumbnail())
                    .target(holder.binding.ivThumbnail)
                    .execute();

            holder.binding.ivThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClick(position, imageList.get(position).getChildrenData().getMainUrl());
                }
            });
        }
    }

    public void setImagesList(List<Children> imagesList) {
        this.imageList = imagesList;
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ItemImageLayoutBinding binding;

        public Holder(@NonNull ItemImageLayoutBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }


    interface OnItemClick {
        void onItemClick(int pos, String url);
    }
}
