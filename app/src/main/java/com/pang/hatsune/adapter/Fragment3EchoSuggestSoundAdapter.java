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
import com.pang.hatsune.info.gsonfactory.EchoSuggestSoundPageInfo;

import java.util.List;

/**
 * Echo页面  推荐页的sound grid RecycleView适配器
 * Created by Administrator on 2016/8/3.
 */
public class Fragment3EchoSuggestSoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int LOADING_TYPE = 10;
    private final int NORMAL_TYPE = 11;
    private final int HEADER_TYPE = 12;
    private View mHeaderView;


    Context context;
    List<EchoSuggestSoundPageInfo.DescBean.DataBean> list;

    public Fragment3EchoSuggestSoundAdapter(Context context, List<EchoSuggestSoundPageInfo.DescBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOADING_TYPE) {
            return new VH(LayoutInflater.from(context).inflate(R.layout.loading, null));
        }

        if (mHeaderView != null && viewType == HEADER_TYPE) {
            return new VH(mHeaderView);
        }

        View v = LayoutInflater.from(context).inflate(R.layout.fragment3_echo_suggest_sound_info_grid_item, null);
        return new VH(v);
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position) == null) {
            return LOADING_TYPE;
        }

        if (mHeaderView == null) {
            return NORMAL_TYPE;
        }
        if (position == 0) {
            return HEADER_TYPE;
        }

        return NORMAL_TYPE;

        // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (list.get(position) == null || position == 0) {
            return;
        }

        VH vh = (VH) holder;

//                        soundPageInfo.getDesc().getData().get(position).getSound().getPic();
//                        soundPageInfo.getDesc().getData().get(position).getSound().getId();//id
//                        soundPageInfo.getDesc().getData().get(position).getSound().getSource();//音乐资源地址
//                        soundPageInfo.getDesc().getData().get(position).getSound().getPic_200();//200x200的图片
        vh.image.setImageURI(Uri.parse(list.get(position).getSound().getPic_200()));
        vh.title.setText(list.get(position).getSound().getName());
        vh.channeltitle.setText(list.get(position).getSound().getChannel().getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        public final SimpleDraweeView image;
        public final TextView title;
        public final TextView channeltitle;

        public VH(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.fragment3_echo_suggest_sound_info_grid_item_image);
            title = (TextView) itemView.findViewById(R.id.fragment3_echo_suggest_sound_info_grid_item_title);
            channeltitle = (TextView) itemView.findViewById(R.id.fragment3_echo_suggest_sound_info_grid_item_channel);
        }
    }
}
