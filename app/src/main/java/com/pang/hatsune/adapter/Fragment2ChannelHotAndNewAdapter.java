package com.pang.hatsune.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;

import java.util.ArrayList;

/**
 * 频道页面的热门,最新   recycleView 适配器
 * Created by Pang on 2016/7/29.
 */
public class Fragment2ChannelHotAndNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Fragment2ChannelHorizontalInfo> list;
    Context context;

    public Fragment2ChannelHotAndNewAdapter(ArrayList<Fragment2ChannelHorizontalInfo> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.fragment2_channel_hotandnewfragment_layout_recycleview_item, null, false);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"00000000",Toast.LENGTH_SHORT).show();
//            }
//        });
        return new VH(v);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        VH vhs = (VH) holder;
        Fragment2ChannelHorizontalInfo info = list.get(position);
//        info.getId();
        vhs.imageView.setImageURI(Uri.parse(info.getUrl()));
        vhs.title.setText(info.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        public SimpleDraweeView imageView;
        public TextView title;


        public VH(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.fragment2_channel_hotandnewfragment_layout_recycleview_item_image);
            title = (TextView) itemView.findViewById(R.id.fragment2_channel_hotandnewfragment_layout_recycleview_item_title);
        }
    }
}
