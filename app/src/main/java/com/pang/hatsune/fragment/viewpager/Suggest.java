package com.pang.hatsune.fragment.viewpager;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment3EchoSuggestSoundAdapter;
import com.pang.hatsune.custom_view.IndicatorView;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dejson.Dejson;
import com.pang.hatsune.fragment.image.ImageFragment;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.gsonfactory.EchoSuggestBannerInfo;
import com.pang.hatsune.info.gsonfactory.EchoSuggestSoundPageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager页的推荐页面
 * Created by Pang on 2016/7/23.
 */
public class Suggest extends Fragment {
    ViewPager imageViewpager;
    ArrayList<ImageFragment> imageFragmentList;
    Fragment3EchoSuggestSoundAdapter soundGridAdapter;//
    List<EchoSuggestSoundPageInfo.DescBean.DataBean> soundGridList;//
    int lastNum = 1;//上次访问的数据页数
    View headerView;
    EchoSuggestBannerInfo echoSuggestBannerInfo;
    EchoSuggestSoundPageInfo soundPageInfo;
    private final int GOGOING_BANNERIMAGE = 1;
    private final int GOGOING_SOUNDINFO = 2;
    private final int GOGOING_SOUNDINFO_LOADING = 3;
    private boolean isLoading;
    private boolean isEnd;

    IndicatorView mIndicatorView;

    RecyclerView soundGridrecyclerView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == GOGOING_BANNERIMAGE) {
                for (int i = 0; i < echoSuggestBannerInfo.getResult().size(); i++) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(Suggest.this.getContext());
                    simpleDraweeView.setImageURI(Uri.parse(echoSuggestBannerInfo.getResult().get(i).getPic()));
                    imageFragmentList.add(new ImageFragment().setView(simpleDraweeView));
//                   echoSuggestBannerInfo.getResult().get(i).getSound().getSource();//音乐地址
//                   echoSuggestBannerInfo.getResult().get(i).getSound().getId();//音乐id
//                   echoSuggestBannerInfo.getResult().get(i).getSound().getName();//音乐名
                }

                imageViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return imageFragmentList.get(position);
                    }

                    @Override
                    public int getCount() {
                        return imageFragmentList.size();
                    }
                });

                mIndicatorView.setViewPager(imageViewpager);//设置滑动指示器
            }


            if (msg.what == GOGOING_SOUNDINFO) {
                soundGridrecyclerView.setLayoutManager(new GridLayoutManager(Suggest.this.getContext(), 2));
                soundGridList = soundPageInfo.getDesc().getData();
                soundGridAdapter = new Fragment3EchoSuggestSoundAdapter(Suggest.this.getActivity(), soundGridList);
                soundGridAdapter.setHeaderView(headerView);
                soundGridrecyclerView.setAdapter(soundGridAdapter);

//                soundGridrecyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//                    @Override
//                    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                        View v = LayoutInflater.from(Suggest.this.getActivity()).inflate(R.layout.fragment3_echo_suggest_sound_info_grid_item,null);
//                        return new VH(v);
//                    }
//
//                    @Override
//                    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                        VH vh = (VH) holder;
////                        soundPageInfo.getDesc().getData().get(position).getSound().getPic();
////                        soundPageInfo.getDesc().getData().get(position).getSound().getId();//id
////                        soundPageInfo.getDesc().getData().get(position).getSound().getSource();//音乐资源地址
////                        soundPageInfo.getDesc().getData().get(position).getSound().getPic_200();//200x200的图片
//                        vh.image.setImageURI(Uri.parse(soundPageInfo.getDesc().getData().get(position).getSound().getPic_200()));
//                        vh.title.setText(soundPageInfo.getDesc().getData().get(position).getSound().getName());
//                    }
//
//                    @Override
//                    public int getItemCount() {
//                        return soundPageInfo.getDesc().getData().size();
//                    }
//
//                    class VH extends RecyclerView.ViewHolder {
//                        public final SimpleDraweeView image;
//                        public final TextView title;
//
//                        public VH(View itemView) {
//                            super(itemView);
//                            image = (SimpleDraweeView) itemView.findViewById(R.id.fragment3_echo_suggest_sound_info_grid_item_image);
//                            title = (TextView) itemView.findViewById(R.id.fragment3_echo_suggest_sound_info_grid_item_title);
//                        }
//                    }
//                });

            }


            if (msg.what == GOGOING_SOUNDINFO_LOADING) {
                soundGridList.remove(soundGridList.size() - 1);
                if (soundPageInfo.getDesc().getData().size() < 1) {
                    isEnd = true;
                }
                soundGridList.addAll(soundPageInfo.getDesc().getData());
                soundGridAdapter.notifyItemRemoved(soundGridList.size());
                soundGridAdapter.notifyDataSetChanged();
                isLoading = false;
            }

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3_echo_viewpager_fragment1_suggest, null, false);
         headerView = LayoutInflater.from(Suggest.this.getContext()).inflate(R.layout.image_viewpager, null);
        imageViewpager = (ViewPager) headerView.findViewById(R.id.image_viewpager);
        mIndicatorView = (IndicatorView) headerView.findViewById(R.id.id_indicator);
        soundGridrecyclerView = (RecyclerView) v.findViewById(R.id.fragment3_echo_viewpager_fragment1_suggest_recycleview);
        soundGridrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager manager = (GridLayoutManager) soundGridrecyclerView.getLayoutManager();
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (soundGridList.get(position) == null || position == 0) ? 2 : 1;
                    }
                });

                int lastItemIndex = manager.findLastVisibleItemPosition()-1;
                int firstItemIndex = manager.findFirstVisibleItemPosition();
                //滑动到最后一个并且状态不是加载中,执行加载更多，isLoading默认值false
//                System.out.println("hhtjim:lastItemIndex"+lastItemIndex);
//                System.out.println(Math.ceil((double)(lastItemIndex/2))+" -- "+soundGridList.size()/2);
//                System.out.println("hhtjim:end");
//                System.out.println(Math.ceil(lastItemIndex/2) >= soundGridList.size()/2);

                if (Math.ceil(lastItemIndex / 2) >= (soundGridList.size() / 2 - 1) && !isLoading) {//若最后一项的布局高度比较高，列表的下边界在最后一项高度以内 都会触发if
//                    System.out.println("hhtjim:执行loading");
                    if (dy > 0) {//触摸点向上托
                        soundGridList.add(null);// TODO: 2016/8/3
                        soundGridAdapter.notifyItemInserted(soundGridList.size() - 1);
                        thread(GOGOING_SOUNDINFO_LOADING);

                    }
                }
            }
        });

        setImageData();
        setSoundGridData();

        return v;
    }


    /**
     * 设置banner的图片数据
     */
    public void setImageData() {
        imageFragmentList = new ArrayList<ImageFragment>();
        thread(GOGOING_BANNERIMAGE);
    }


    /**
     * 设置下面Sound Info grid 数据
     */
    public void setSoundGridData() {
        thread();
    }


    /**
     * 获取音乐推荐的信息
     */
    private void thread() {
        thread(GOGOING_SOUNDINFO);
    }

    private void thread(final int state) {

        if (state == GOGOING_BANNERIMAGE) {
            new Thread() {
                @Override
                public void run() {
//        super.run();
                    String jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_ECHO_SUGGEST_BANNER);
                    echoSuggestBannerInfo = Dejson.getInstance().getEchoSuggestBannerInfo(jsonString);
                    handler.sendEmptyMessage(GOGOING_BANNERIMAGE);
                }
            }.start();
            return;
        }


        if (state == GOGOING_SOUNDINFO) {
            lastNum = 1;
        }


        if (state == GOGOING_SOUNDINFO_LOADING) {
//            lastNum++;
        }

        if (!isLoading && !isEnd) {
            lastNum++;
            new Thread() {
                @Override
                public void run() {
                    if (state == GOGOING_SOUNDINFO_LOADING) {

                        isLoading = true;
                    }
                    String jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_ECHO_SUGGEST_SOUND_INFO + lastNum);
                    soundPageInfo = Dejson.getInstance().getEchoSuggestSoundPageInfoo(jsonString);
                    handler.sendEmptyMessage(state);
                }
            }.start();
        }


    }
}
