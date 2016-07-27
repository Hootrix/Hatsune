package com.pang.hatsune.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.pang.hatsune.R;
import com.pang.hatsune.fresco.FrescoConfigClass;
import com.pang.hatsune.fresco.ImageLoadingDrawable;
import com.pang.hatsune.info.NewsRecyclerViewInfo;
import com.pang.hatsune.info.gsonfactory.NewsRecyclerViewInfoGson;

import org.w3c.dom.Text;

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
    private int loading = -1;

    public Fragment1RecyclerViewAdapter(Context context, ArrayList<NewsRecyclerViewInfo> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position) == null) {//loading
            return loading;
        }
        return super.getItemViewType(position);
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
        if (viewType == loading) {//上拉加载更多的布局
            //使用loading 文本
//            TextView loadingView = new TextView(context);
//            return  new ViewHolder(loadingView);

            //使用loading 动画
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loading, parent, false);

            return  new ViewHolder(v);
        }

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
        if (list.get(position)==null) {//加载更多

            //设置loading的文本内容，样式
//            TextView tv = (TextView)holder.mView;
//            tv.setText("Loading...");
//            tv.setTextSize(25);
//            tv.setGravity(Gravity.CENTER);
//            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            return;
        }

        holder.mItem = list;
        holder.publisher.setText(holder.mItem.get(position).getPublisherInfo().getName());
        holder.publicTitle.setText(holder.mItem.get(position).getLabel_text());
        String date = new SimpleDateFormat("MM-dd HH:mm").format(new Date(Long.valueOf(holder.mItem.get(position).getCreate_time())));
        holder.publicTime.setText(date);
        holder.content.setText(holder.mItem.get(position).getContent() + "");

        holder.musicIcon.setImageURI(Uri.parse(holder.mItem.get(position).getSoundInfo().getPic()));
        holder.musicIcon.setHierarchy(FrescoConfigClass.getLoadingProgressHierarchy());

//
//        //图片的加载进度条
//        ProgressBarDrawable progress = new ProgressBarDrawable();
//        progress.setBackgroundColor(0xff858585);
//        progress.setColor(0xffffffff);
//        progress.setBarWidth(5);
//        progress.setHideWhenZero(true);
//        GenericDraweeHierarchyBuilder builder =
//                new GenericDraweeHierarchyBuilder(context.getResources());
//        GenericDraweeHierarchy hierarchy = builder
//                .setFadeDuration(300)
//                .setProgressBarImage(progress)
//                .build();
//        holder.musicIcon.setHierarchy(hierarchy);

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



    /**
     * 普通布局的viewholder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView publisher;
        public final TextView publicTitle;
        public final TextView publicTime;
        public final TextView content;
        public final SimpleDraweeView musicIcon;
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
