package com.pang.hatsune.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.info.gsonfactory.Fragment4CelebrityStartinfo;

import java.util.ArrayList;

/**
 * 名人页面  适配器
 * Created by Administrator on 2016/8/10.
 */
public class Fragment4CelebrityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Fragment4CelebrityStartinfo> list;

    private final int TYPE_HEADER_IMAGE = 1;
    private final int TYPE_STARTS_HORIZONTAL_LIST = 2;
    private final int TYPE_MVS_GRID_LIST = 3;
    private final int TYPE_RECOMMEND_TITLE = 4;
    private final int TYPE_NORMAL_RECOMMEND_LIST = 5;

    private View mHeaderView;
    private View mHorizontalListview;
    private View mMvsGridListView;
    private View mNormalRecommendTitleView;

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setmHorizontalListview(View mHorizontalListview) {
        this.mHorizontalListview = mHorizontalListview;
    }

    public void setmMvsGridListView(View mMvsGridListView) {
        this.mMvsGridListView = mMvsGridListView;
    }

    public void setmNormalRecommendTitleView(View mNormalRecommendTitleView) {
        this.mNormalRecommendTitleView = mNormalRecommendTitleView;
    }


    public Fragment4CelebrityListAdapter(android.content.Context context, ArrayList<Fragment4CelebrityStartinfo> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER_IMAGE:
                return new VH(mHeaderView);

            case TYPE_STARTS_HORIZONTAL_LIST:
                return   new VH(mHorizontalListview);

            case TYPE_MVS_GRID_LIST:
                return  new VH(mMvsGridListView);

            case TYPE_RECOMMEND_TITLE:
                return   new VH(mNormalRecommendTitleView);

            default://position>=4   TYPE_NORMAL_RECOMMEND_LIST
        }
        View normalRecommendRootView = LayoutInflater.from(context).inflate(R.layout.fragment4_celebrity_recommend_list_item, null, false);

        return new VH(normalRecommendRootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < 4) {
            return;
        }

        position -= 4;
        ((VH) holder).image.setImageURI(Uri.parse(list.get(position).getPic()));
        ((VH) holder).name.setText(list.get(position).getName());
        ((VH) holder).desc.setText(list.get(position).getDescOrChannel());
    }

    @Override
    public int getItemCount() {
        return list.size() + 4;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER_IMAGE;
            case 1:
                return TYPE_STARTS_HORIZONTAL_LIST;
            case 2:
                return TYPE_MVS_GRID_LIST;
            case 3:
                return TYPE_RECOMMEND_TITLE;
            default://position>=4
                return TYPE_NORMAL_RECOMMEND_LIST;
        }
    }


    public class VH extends RecyclerView.ViewHolder {
        final SimpleDraweeView image;
        final TextView name;
        final TextView desc;

        public VH(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.fragment4_celebrity_recommend_list_item_image);
            name = (TextView) itemView.findViewById(R.id.fragment4_celebrity_recommend_list_item_name);
            desc = (TextView) itemView.findViewById(R.id.fragment4_celebrity_recommend_list_item_desc);
        }
    }

}
