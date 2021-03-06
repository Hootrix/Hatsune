package com.pang.hatsune.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.info.gsonfactory.SearchResultInfo;
import com.pang.hatsune.utils.StringFilter;

import java.util.List;

/**
 * Created by Pang on 2016/8/7.
 */
public class SearchResultRecycleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SearchResultInfo.ResultBean.DataBean> list;
    //    SearchResultInfo  info;
    Context context;
    private int TYPE_LOADING = 1;

    private boolean isEmpty;//友情提示的空布局
    private View emptyView;

    private String colorFitlerKeyword;//搜索关键字 用于搜索关键字颜色高亮


    public SearchResultRecycleviewAdapter(List<SearchResultInfo.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setEmptyView(View v) {
        this.emptyView = v;
    }

    public void setColorFitlerKeyword(String colorFitlerKeyword) {
        this.colorFitlerKeyword = colorFitlerKeyword;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING) {
            LinearLayout loading = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.loading, null, false);

            /**
             * 必须设置LayoutParams 布局参数 否者不会居中显示
             */
            loading.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            loading.setGravity(Gravity.CENTER);
            return new VH(loading);
        }

        if (isEmpty && emptyView != null) {
            TextView tv = (TextView) emptyView;
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);
            return new VH(tv);
        }

        View v = LayoutInflater.from(context).inflate(R.layout.activity_search_result_list_item, null, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isEmpty || list.get(position) == null) {//空布局 必须在前面判断
            return;
        }

        VH vh = (VH) holder;
        vh.image.setImageURI(Uri.parse(list.get(position).getPic()));

        if (colorFitlerKeyword != null) {//有搜索关键字高亮的需求
            vh.desc.setText(StringFilter.getInstance().fitlerColor(colorFitlerKeyword, list.get(position).getInfo()));
            vh.title.setText(StringFilter.getInstance().fitlerColor(colorFitlerKeyword, list.get(position).getName()));
        } else {
            vh.desc.setText(list.get(position).getInfo());
            vh.title.setText(list.get(position).getName());
        }


// list.get(position).getSource();
    }

    @Override
    public int getItemCount() {
        if (list.size() == 0) {//判断是否显示空布局 提示
            isEmpty = true;
            return 1;
        }
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (isEmpty) {//空布局
            return super.getItemViewType(position);
        }

        if (list.get(position) == null) {
            return TYPE_LOADING;
        }
        return super.getItemViewType(position);
    }

    public class VH extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView title;
        TextView desc;


        public VH(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.search_resutlt_item_image);
            title = (TextView) itemView.findViewById(R.id.search_resutlt_item_music_title);
            desc = (TextView) itemView.findViewById(R.id.search_resutlt_item_music_desc);
        }
    }
}
