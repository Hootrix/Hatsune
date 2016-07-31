package com.pang.hatsune.fragment.channel_hotnew;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment2ChannelHotAndNewAdapter;
import com.pang.hatsune.custom_view.FullyGridLayoutManager;
import com.pang.hatsune.custom_view.RecycleViewDivider;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;

import java.util.ArrayList;

/**
 * 频道页面的  页面和最新数据的fragment
 * Created by Administrator on 2016/7/29.
 */
public class HotAndNewFragment extends Fragment {
    public static final int NORMAL = 1;
    public static final int LOADING_MORE = 2;
    public static final int HOT = 10;
    public static final int NEW = 20;
    private int type;
    private int lastNum = 0;//上次请求的页数
    private View rootView;
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
//            switch (type) {
//                case HOT:
//
//                    break;
//                case NEW:
//
//                    break;
//            }


            if (msg.what == NORMAL) {
                adapter = new Fragment2ChannelHotAndNewAdapter(list, HotAndNewFragment.this.getActivity());
                recyclerView.setLayoutManager(new FullyGridLayoutManager(HotAndNewFragment.this.getContext(), 2));
                recyclerView.setAdapter(adapter);
//                recyclerView.addItemDecoration(new RecycleViewDivider(HotAndNewFragment.this.getContext(), RecycleViewDivider.HORIZONTAL_VERTICAL, 10, 0xffffffff));//设置分割线间距
            }

            if (msg.what == LOADING_MORE) {
                list.addAll(loadingList);
                adapter.notifyDataSetChanged();
                loadingProgress.setVisibility(View.GONE);


//                adapter.notifyItemRemoved(list.size());
                isLoading = false;
            }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置请求，显示的数据类型
     *
     * @param type
     */
    public HotAndNewFragment setType(int type) {
        if (type != HOT && type != NEW) {
            throw new RuntimeException("指定类型错误。");
        }
        this.type = type;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment2_channel_hotandnewfragment_layout, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment2_channel_hotandnewfragment_layout_recyclerview);
        loadingProgress = (CircularProgressView) rootView.findViewById(R.id.loading_progress);
        loadingProgress.setVisibility(View.GONE);
        setData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("hhtjim:887");
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastItemIndex = manager.findLastVisibleItemPosition();
                int firstItemIndex = manager.findFirstVisibleItemPosition();
//                Log.i("aa","进了onScrolled："+lastItemIndex+":"+list.size());
                //滑动到最后一个并且状态不是加载中,执行加载更多，isLoading默认值false
                if (lastItemIndex >= list.size() - 1 && !isLoading) {//若最后一项的布局高度比较高，列表的下边界在最后一项高度以内 都会触发if
                    if (!isEnd) {
                        loadingProgress.setVisibility(View.VISIBLE);

//                        list.add(null);
//                        adapter.notifyItemInserted(list.size() - 1);
                    }
                    if (dy > 0) {//触摸点向上托
                        thread();
                    }
                }
                    System.out.println("hhtjim:lastItemIndex:"+lastItemIndex);
                    System.out.println("hhtjim:list.size():"+list.size());

            }
        });

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
        new Thread() {
            @Override
            public void run() {
//                super.run();

                for (int i = 0; i < 2; i++) {
                    String url = HotAndNewFragment.this.type == HOT ? DATA.DOMAIN_API_HOT_DATA : DATA.DOMAIN_API_NEW_DATA;
                    String htmlString = HttpResquestPang.getInstance().get(url + ++lastNum, HttpResquestPang.getInstance().getPCHeaders());//url + ++lastNum

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


//
//    /**
//     * 抓取网络图片和标题的线程
//     */
//    public class FetchImageTitleData implements Runnable {
//
//        @Override
//        public void run() {
//
//
//        }
//    }
}
