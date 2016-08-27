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
import com.pang.hatsune.info.EchoHotInfo;

/**
 * Created by Administrator on 2016/8/4.
 */
public class Fragment3EchoHotGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER_IMAGE_TYPE = 0;
    private final int DAY_HOT_TYPE = 1;
    private final int WEEK_HOT_TYPE = 2;
    private final int WEEK_HOT_TITLE = 3;
    private final int DAY_HOT_TITLE = 4;

    private Context context;
    private View mHeaderView;
    private View mWeekHotTitleView;
    private View mDayHotTitleView;
    private EchoHotInfo info;

    public Fragment3EchoHotGridAdapter(Context context, EchoHotInfo info) {
        this.info = info;
        this.context = context;
    }

    public void setHeaderView(View v) {
        this.mHeaderView = v;
    }

    public void setWeekHotTitle(View v) {
        this.mWeekHotTitleView = v;
    }

    public void setDayHotTitle(View v) {
        this.mDayHotTitleView = v;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return HEADER_IMAGE_TYPE;
        }

        if (position == 1) {
            return DAY_HOT_TITLE;
        }

        if (position == info.getDayHotList().size() + 2) {
            return WEEK_HOT_TITLE;
        }

        if (position >= info.getDayHotList().size() + 3) {
            return WEEK_HOT_TYPE;
        }

        return DAY_HOT_TYPE;


//        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mDayHotTitleView != null && viewType == DAY_HOT_TITLE) {
            return new VH(mDayHotTitleView);
        }

        if (mWeekHotTitleView != null && viewType == WEEK_HOT_TITLE) {
            return new VH(mWeekHotTitleView);
        }

        if (mHeaderView != null && viewType == HEADER_IMAGE_TYPE) {
            return new VH(mHeaderView);
        }

        if (mHeaderView != null && viewType == WEEK_HOT_TYPE) {
            View v = LayoutInflater.from(context).inflate(R.layout.fragment3_echo_hot_grid_week_hot_item, null);
            return new VH(v);
        }


        View v = LayoutInflater.from(context).inflate(R.layout.fragment3_echo_hot_grid_day_hot_item, null);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 || position == 1 || position == info.getDayHotList().size() + 2) {
            return;
        }

        VH vh = (VH) holder;

        /**
         * 周榜数据设置
         */
        if (position >= info.getDayHotList().size() + 3) {
            position = position - (info.getDayHotList().size() + 3);
            vh.username.setText(info.getWeekHotList().get(position).getUsername());
            vh.weekDataImage.setImageURI(Uri.parse(info.getWeekHotList().get(position).getPic()));
            vh.weekDataTitle.setText(info.getWeekHotList().get(position).getTitle());
            return;
        }

        /**
         * 今日最热数据设置
         */
        if (position <= info.getDayHotList().size() + 2) {
            position = position - 2;
//            vh.channel.setText(info.getDayHotList().get(position).getChannel());

            if (info.getDayHotList().get(position).getPic() != null) {
                vh.dayDataImage.setImageURI(Uri.parse(info.getDayHotList().get(position).getPic()));
                vh.dayDataImage.setVisibility(View.VISIBLE);
                vh.orderText.setVisibility(View.GONE);
            } else {
                vh.dayDataImage.setVisibility(View.GONE);
                vh.orderText.setVisibility(View.VISIBLE);
            }
            vh.orderText.setText((position + 1) + "");
            vh.dayDataTitle.setText(info.getDayHotList().get(position).getTitle());
            return;
        }


    }

    @Override
    public int getItemCount() {
        return info.getDayHotList().size() + info.getWeekHotList().size() + 3;
//        return 10;
    }

    public class VH extends RecyclerView.ViewHolder {
        public final TextView dayDataTitle, weekDataTitle;
        public final TextView orderText;//今日热门排序用来显示的文本
        public final SimpleDraweeView dayDataImage, weekDataImage;
        public final TextView username;
//        public final TextView  channel;

        public VH(View itemView) {
            super(itemView);
            dayDataTitle = (TextView) itemView.findViewById(R.id.fragment3_echo_hot_grid_day_hot_item_title);
            weekDataTitle = (TextView) itemView.findViewById(R.id.fragment3_echo_hot_grid_week_hot_item_title);
            dayDataImage = (SimpleDraweeView) itemView.findViewById(R.id.fragment3_echo_hot_grid_day_hot_item_image);
            weekDataImage = (SimpleDraweeView) itemView.findViewById(R.id.fragment3_echo_hot_grid_week_hot_item_image);
            username = (TextView) itemView.findViewById(R.id.fragment3_echo_hot_grid_week_hot_item_username);
//            channel = (TextView) itemView.findViewById(R.id.fragment3_echo_hot_grid_day_hot_item_channel);
            orderText = (TextView) itemView.findViewById(R.id.fragment3_echo_hot_grid_day_hot_item_order);
        }
    }
}
