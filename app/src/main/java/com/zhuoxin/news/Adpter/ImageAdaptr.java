package com.zhuoxin.news.Adpter;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhuoxin.news.R;
import com.zhuoxin.news.utils.JsonImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nh on 2017/1/13.
 */

public class ImageAdaptr extends RecyclerView.Adapter<ImageAdaptr.viewHolder> {
    Context context;
    List<JsonImage.ResultsBean> imageURLlist=new ArrayList<>();

    public ImageAdaptr(Context context, List<JsonImage.ResultsBean> imageURLlist) {
        this.context = context;
        this.imageURLlist = imageURLlist;
    }

    @Override
    public int getItemCount() {
        return imageURLlist.size();
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image,parent,false);
        viewHolder holder=new viewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Picasso.with(context)
                .load(imageURLlist.get(position).getUrl())
                .placeholder(R.color.colorPrimary)
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_image);
    }


    static class  viewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        public viewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}
