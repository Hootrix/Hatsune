package com.pang.hatsune.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pang.hatsune.R;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.info.gsonfactory.NewsRecyclerViewInfoGson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * fragment1 news 的RecyclerView适配器
 * Created by Administrator on 2016/7/25.
 */
public class Fragment1RecyclerViewAdapter extends RecyclerView.Adapter<Fragment1RecyclerViewAdapter.ViewHolder> {
    private final NewsRecyclerViewInfoGson infoClass;

    public Fragment1RecyclerViewAdapter(NewsRecyclerViewInfoGson infoClass) {
        this.infoClass = infoClass;
    }

    /**
     * 找到根布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment1_news_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }


    /**
     * 给viewholder控件设置值
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = infoClass.getDesc();
//        System.out.println("=============" + holder.mItem.get(position).getSound().getName());
        holder.publisher.setText(holder.mItem.get(position).getPublisher().getName());
        holder.publicTitle.setText(holder.mItem.get(position).getLabel_text());
        String date = new SimpleDateFormat("MM-dd HH:mm").format(new Date((long)(holder.mItem.get(position).getCreate_time())));
        holder.publicTime.setText(date);
//        holder.content.setText(holder.mItem.get(position).getContent() + "");
//        holder.musicIcon.setIma

//        holder.musicDes.setText(holder.mItem.get(position).getSound().getName());
        // holder.musicPlayNum.setText(holder.mItem.get(position).getSound().getView_count());
//        holder.comment.setText(holder.mItem.get(position).getComment_num());
//        holder.like.setText(holder.mItem.get(position).getLike_num());
//        holder.share.setText(holder.mItem.get(position).getRelay_num());
    }

    @Override
    public int getItemCount() {
        int num = 0;
        try {
            num = infoClass.getDesc().size();
        } catch (NullPointerException e) {
            return num;
        }
        return num;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView publisher;
        public final TextView publicTitle;
        public final TextView publicTime;
        public final TextView content;
        public final ImageView musicIcon;
        public final TextView musicDes;
        public final TextView musicPlayNum;
        public final Button comment;
        public final Button like;
        public final Button share;

        public List<NewsRecyclerViewInfoGson.DescBean> mItem;

        public ViewHolder(View rootView) {
            super(rootView);
            mView = rootView;
            publisher = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_echo_publisher);
            publicTitle = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_echo_title);
            publicTime = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_echo_date);
            content = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_content);
            musicIcon = (ImageView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_music_icon);
            musicDes = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_music_title);
            musicPlayNum = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_music_play_num);
            comment = (Button) rootView.findViewById(R.id.fragment1_news_recyclerview_item_play_comment);
            like = (Button) rootView.findViewById(R.id.fragment1_news_recyclerview_item_play_like);
            share = (Button) rootView.findViewById(R.id.fragment1_news_recyclerview_item_play_share);


        }
    }
}
