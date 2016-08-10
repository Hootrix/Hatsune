package com.pang.hatsune.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;
import com.pang.hatsune.info.gsonfactory.Fragment4CelebrityStartinfo;

import java.util.ArrayList;

/**
 * 名人页面  适配器
 * Created by Administrator on 2016/8/10.
 */
public class Fragment4CelebrityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context Context;
    ArrayList<Fragment4CelebrityStartinfo> list;

    private final int TYPE_HEADER_IMAGE = 1;
    private final int TYPE_STARTS_HORIZONTAL_LIST = 2;
    private final int TYPE_MVS_GRID_LIST = 3;
    private final int TYPE_RECOMMEND_TITLE = 4;
    private final int TYPE_NORMAL_RECOMMEND_LIST = 5;

    private View mHeaderView;
    private View mHorizontalListview;
    private View mMvsGridListView;
    private View mNormalRecommendListView;

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setmHorizontalListview(View mHorizontalListview) {
        this.mHorizontalListview = mHorizontalListview;
    }

    public void setmMvsGridListView(View mMvsGridListView) {
        this.mMvsGridListView = mMvsGridListView;
    }

    public void setmNormalRecommendListView(View mNormalRecommendListView) {
        this.mNormalRecommendListView = mNormalRecommendListView;
    }


    public Fragment4CelebrityListAdapter(android.content.Context context, ArrayList<Fragment4CelebrityStartinfo> list) {
        Context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER_IMAGE:
            case TYPE_STARTS_HORIZONTAL_LIST:
            case TYPE_MVS_GRID_LIST:
            case TYPE_RECOMMEND_TITLE:
            default://position>=4
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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


        public VH(View itemView) {
            super(itemView);


        }
    }

}
