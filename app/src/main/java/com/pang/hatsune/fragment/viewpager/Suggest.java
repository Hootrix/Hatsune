package com.pang.hatsune.fragment.viewpager;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.pang.hatsune.fragment.BaseFragment;
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
public class Suggest extends BaseFragment {
    ViewPager imageViewpager;
    SwipeRefreshLayout mSwipeRefreshLayout;
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
    GridLayoutManager gridLayoutManager;
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
                gridLayoutManager = new GridLayoutManager(Suggest.this.getContext(), 2);

                /**
                 * 判断recycleview的position 来动态返回item项占用的单位
                 */
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (soundGridList.get(position) == null || position == 0) ? 2 : 1;
                    }
                });
                soundGridrecyclerView.setLayoutManager(gridLayoutManager);
                soundGridList = soundPageInfo.getDesc().getData();
                soundGridAdapter = new Fragment3EchoSuggestSoundAdapter(Suggest.this.getActivity(), soundGridList);
                soundGridrecyclerView.setAdapter(soundGridAdapter);
                soundGridAdapter.setHeaderView(headerView);


                mSwipeRefreshLayout.setRefreshing(false);
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
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.fragment3_echo_viewpager_fragment1_suggest_refreshlayout);
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);

        headerView = LayoutInflater.from(Suggest.this.getContext()).inflate(R.layout.image_viewpager, null, false);
        imageViewpager = (ViewPager) headerView.findViewById(R.id.image_viewpager);
        mIndicatorView = (IndicatorView) headerView.findViewById(R.id.id_indicator);
        soundGridrecyclerView = (RecyclerView) v.findViewById(R.id.fragment3_echo_viewpager_fragment1_suggest_recycleview);
        soundGridrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //   gridLayoutManager = (GridLayoutManager) soundGridrecyclerView.getLayoutManager();

                int lastItemIndex = gridLayoutManager.findLastVisibleItemPosition() - 1;
                int firstItemIndex = gridLayoutManager.findFirstVisibleItemPosition();
                //滑动到最后一个并且状态不是加载中,执行加载更多，isLoading默认值false

                if (Math.ceil(lastItemIndex / 2) >= (soundGridList.size() / 2 - 1) && !isLoading) {//若最后一项的布局高度比较高，列表的下边界在最后一项高度以内 都会触发if
//                    System.out.println("hhtjim:执行loading");
                    if (dy > 0 && !isEnd) {//触摸点向上托
                        if (!isLoading) {//这里标记为loading最好，避免进方法里new线程
                            isLoading = true;
//                            System.out.println("hhtjim:00:add(null)");
                            soundGridList.add(null);
                            soundGridAdapter.notifyItemInserted(soundGridList.size() - 1);
                            thread(GOGOING_SOUNDINFO_LOADING);
                        }

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
                    if (echoSuggestBannerInfo != null) {
                        handler.sendEmptyMessage(GOGOING_BANNERIMAGE);
                    }
                }
            }.start();
            return;
        }


        if (state == GOGOING_SOUNDINFO) {
            lastNum = 1;
            new Thread() {
                @Override
                public void run() {
                    String jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_ECHO_SUGGEST_SOUND_INFO + lastNum);
                    soundPageInfo = Dejson.getInstance().getEchoSuggestSoundPageInfoo(jsonString);
                    if (soundPageInfo != null) {
                        handler.sendEmptyMessage(state);
                    }
                }
            }.start();

            return;
        }


        if (state == GOGOING_SOUNDINFO_LOADING) {
            if (!isEnd) {
                new Thread() {
                    @Override
                    public void run() {

                        lastNum++;
                        String jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_ECHO_SUGGEST_SOUND_INFO + lastNum);
                        soundPageInfo = Dejson.getInstance().getEchoSuggestSoundPageInfoo(jsonString);
                        if (soundPageInfo != null) {
                            handler.sendEmptyMessage(state);
                        }

                    }
                }.start();
            }
            return;
        }


    }
}
