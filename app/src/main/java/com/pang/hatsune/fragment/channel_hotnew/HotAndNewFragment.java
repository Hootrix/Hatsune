package com.pang.hatsune.fragment.channel_hotnew;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment2ChannelHotAndNewAdapter;
import com.pang.hatsune.custom_view.FullyGridLayoutManager;
import com.pang.hatsune.custom_view.MyScrollView;
import com.pang.hatsune.custom_view.RecycleViewDivider;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.event_bus_message.EventHot;
import com.pang.hatsune.event_bus_message.EventNew;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * 频道页面的  热门和最新数据的fragment
 * Created by Administrator on 2016/7/29.
 */
public class HotAndNewFragment extends Fragment {
    public static final int NORMAL = 1;
    public static final int LOADING_MORE = 2;
    public static final int HOT = 10;
    public static final int NEW = 20;
    private int type;
    private int lastNum = 0;//上次请求的页数
    private LinearLayout rootView;
    CircularProgressView loadingProgress;
    private boolean isLoading;//加载更多的状态
    private boolean isEnd;//判断是否到结尾了

    RecyclerView recyclerView;
    Fragment2ChannelHotAndNewAdapter adapter;
    ArrayList<Fragment2ChannelHorizontalInfo> list, loadingList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == NORMAL) {
                adapter = new Fragment2ChannelHotAndNewAdapter(list, HotAndNewFragment.this.getActivity());
                recyclerView.setLayoutManager(new FullyGridLayoutManager(HotAndNewFragment.this.getContext(), 2));
                recyclerView.setAdapter(adapter);
//                recyclerView.addItemDecoration(new RecycleViewDivider(HotAndNewFragment.this.getContext(), RecycleViewDivider.HORIZONTAL_VERTICAL, 10, 0xffffffff));//设置分割线间距
            }

            if (msg.what == LOADING_MORE) {
                loadingProgress.setVisibility(View.GONE);

                if (loadingList != null && loadingList.size() < 1) {
                    isEnd = true;
                    return;
                }

                list.addAll(loadingList);
                adapter.notifyDataSetChanged();


//                adapter.notifyItemRemoved(list.size());
                isLoading = false;
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//接收消息的页面 注册eventbus
    }

    /**
     * 设置请求，显示的数据类型
     *
     * @param type
     */
    public void setType(int type) {
        if (type != HOT && type != NEW) {
            throw new RuntimeException("指定类型错误。");
        }
        this.type = type;
        return;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (LinearLayout) inflater.inflate(R.layout.fragment2_channel_hotandnewfragment_layout, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment2_channel_hotandnewfragment_layout_recyclerview);
        loadingProgress = (CircularProgressView) rootView.findViewById(R.id.loading_progress);

        loadingProgress.setVisibility(View.GONE);
        setData();

//        MyScrollView myscrollview = (MyScrollView) this.getActivity().findViewById(R.id.myscrollview);
//        myscrollview.setOnScrollChangeListener(new MyScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(MyScrollView view, int x, int y, int oldx, int oldy) {
//            }
//
//            @Override
//            public void onScrollBottomListener() {
////                System.out.println("hhtjim::"+type+"进了onScrolled底部");
//
//                if (!isLoading) {//若没有loading
//                    if (!isEnd) {
//                        loadingProgress.setVisibility(View.VISIBLE);
//                        thread(LOADING_MORE);
////                        System.out.println("hhtjim:99"+type+":执行loading thread");
//                    }
//                }
//            }
//
//            @Override
//            public void onScrollTopListener() {
//            }
//        });

        return rootView;
    }

    /**
     * 获取图片 名称数据
     */
    public void setData() {
        list = new ArrayList<Fragment2ChannelHorizontalInfo>();
//        new Thread(new FetchImageTitleData()).start();
        thread();
    }

    /**
     * 抓取网络图片和标题的线程
     */

    public void thread() {
        thread(NORMAL);
    }

    public void thread(final int state) {
        if (!isLoading) {
            new Thread() {
                @Override
                public void run() {
                    isLoading = true;
//                super.run();
                    for (int i = 0; i < 2; i++) {
//                        System.out.println("hhtjim:88899:1：" + type);
                        String url = HotAndNewFragment.this.type == HOT ? DATA.DOMAIN_API_HOT_DATA : DATA.DOMAIN_API_NEW_DATA;
                        String htmlString = "";
                        htmlString = HttpResquestPang.getInstance().get(url + ++lastNum, HttpResquestPang.getInstance().getPCHeaders());//url + ++lastNum
                        if (state == NORMAL && i < 1) {
                            list = DeHtml.getInstance().getHotAndNewData(htmlString);
                            handler.sendEmptyMessage(NORMAL);
                        } else {
                            loadingList = DeHtml.getInstance().getHotAndNewData(htmlString);
                            handler.sendEmptyMessage(LOADING_MORE);

                        }
                    }


                }
            }.start();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消eventBus注册
    }


    /**
     * 接受eventbus消息  更新最热
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(EventHot event) {
        if (type == HOT) {//判断当前的fragment  是否为最热
            doLoadin();
        }
    }


    /**
     * 接受eventbus消息  更新最新
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(EventNew event) {
        if (type == NEW) {//判断当前的fragment  是否为最新
            doLoadin();
        }
    }

    /**
     * 执行loading 操作
     */
    private void doLoadin() {
        if (!isLoading) {//若没有loading
            if (!isEnd) {
                loadingProgress.setVisibility(View.VISIBLE);
                thread(LOADING_MORE);
//                        System.out.println("hhtjim:99"+type+":执行loading thread");
            }
        }
    }
}
