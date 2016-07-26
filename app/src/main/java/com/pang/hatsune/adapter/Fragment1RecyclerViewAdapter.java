package com.pang.hatsune.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.pang.hatsune.R;
import com.pang.hatsune.fresco.ImageLoadingDrawable;
import com.pang.hatsune.info.NewsRecyclerViewInfo;
import com.pang.hatsune.info.gsonfactory.NewsRecyclerViewInfoGson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * fragment1 news 的RecyclerView适配器
 * Created by Administrator on 2016/7/25.
 */
public class Fragment1RecyclerViewAdapter extends RecyclerView.Adapter<Fragment1RecyclerViewAdapter.ViewHolder> {
    private final ArrayList<NewsRecyclerViewInfo> list;
    private Context context;

    public Fragment1RecyclerViewAdapter(Context context,ArrayList<NewsRecyclerViewInfo> list) {
        this.list = list;
        this.context = context;
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
        holder.mItem = list;
        holder.publisher.setText(holder.mItem.get(position).getPublisherInfo().getName());
        holder.publicTitle.setText(holder.mItem.get(position).getLabel_text());
        String date = new SimpleDateFormat("MM-dd HH:mm").format(new Date(Long.valueOf(holder.mItem.get(position).getCreate_time())));
        holder.publicTime.setText(date);
        holder.content.setText(holder.mItem.get(position).getContent() + "");

        holder.musicIcon.setImageURI(Uri.parse(holder.mItem.get(position).getSoundInfo().getPic()));

        ProgressBarDrawable progress =   new ProgressBarDrawable();
        progress.setBackgroundColor(0xff858585);
        progress.setColor(0xffffffff);
        progress.setBarWidth(5);
        progress.setHideWhenZero(true);
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setProgressBarImage(progress)
                .build();
        holder.musicIcon.setHierarchy(hierarchy);

        holder.musicDes.setText(holder.mItem.get(position).getSoundInfo().getName());
         holder.musicPlayNum.setText(holder.mItem.get(position).getSoundInfo().getView_count());
        holder.comment.setText(holder.mItem.get(position).getComment_num());
        holder.like.setText(holder.mItem.get(position).getLike_num());
        holder.share.setText(holder.mItem.get(position).getRelay_num());
    }

    @Override
    public int getItemCount() {
        int num = 0;
        try {
            num = list.size();
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
        public final  SimpleDraweeView musicIcon;
        public final TextView musicDes;
        public final TextView musicPlayNum;
        public final Button comment;
        public final Button like;
        public final Button share;

        public List<NewsRecyclerViewInfo> mItem;

        public ViewHolder(View rootView) {
            super(rootView);
            mView = rootView;
            publisher = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_echo_publisher);
            publicTitle = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_echo_title);
            publicTime = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_echo_date);
            content = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_content);
            musicIcon = (SimpleDraweeView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_music_icon);
            musicDes = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_music_title);
            musicPlayNum = (TextView) rootView.findViewById(R.id.fragment1_news_recyclerview_item_music_play_num);
            comment = (Button) rootView.findViewById(R.id.fragment1_news_recyclerview_item_play_comment);
            like = (Button) rootView.findViewById(R.id.fragment1_news_recyclerview_item_play_like);
            share = (Button) rootView.findViewById(R.id.fragment1_news_recyclerview_item_play_share);


        }
    }
}
