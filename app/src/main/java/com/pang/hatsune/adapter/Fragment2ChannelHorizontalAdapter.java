package com.pang.hatsune.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 频道页 水平滚动的视图recycleView 适配器
 * Created by Administrator on 2016/7/28.
 */
public class Fragment2ChannelHorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Fragment2ChannelHorizontalInfo> list;
    Context context;

    public Fragment2ChannelHorizontalAdapter(ArrayList<Fragment2ChannelHorizontalInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment2_channel_horizontal_recycleview_item, null);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH viewholder = (VH) holder;
        Fragment2ChannelHorizontalInfo info = list.get(position);
        String url = "";
        try {
            url = info.getUrl();
            viewholder.imageView.setImageURI(Uri.parse(info.getUrl()));
        } catch (NullPointerException e) {
        }

        viewholder.textView.setText(info.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        public SimpleDraweeView imageView;
        public TextView textView;

        public VH(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.fragment2_channel_horizontal_recycleview_item_image);
            textView = (TextView) itemView.findViewById(R.id.fragment2_channel_horizontal_recycleview_item_name);
        }
    }
}
