package com.pang.hatsune.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/8/4.
 */
public class Fragment3EchoHotGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER_IMAGE_TYPE = 0;
    private final int DAY_HOT_TYPE = 1;
    private final int WEEK_HOT_TYPE = 2;
    private final int WEEK_HOT_TITLE = 3;
    private final int DAY_HOT_TITLE = 4;

    private View mHeaderView;
    //todo 信息封装类

    public Fragment3EchoHotGridAdapter(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public void setHeaderView(View v) {
        this.mHeaderView = v;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VH extends RecyclerView.ViewHolder {

        public VH(View itemView) {
            super(itemView);
        }
    }
}
