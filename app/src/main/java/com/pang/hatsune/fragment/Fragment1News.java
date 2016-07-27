package com.pang.hatsune.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment1RecyclerViewAdapter;
import com.pang.hatsune.custom_view.RecycleViewDivider;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dejson.Dejson;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.NewsRecyclerViewInfo;
import com.pang.hatsune.info.gsonfactory.NewsRecyclerViewInfoGson;

import java.util.ArrayList;
import java.util.List;


/**
 * 动态 fragment
 * Created by Administrator on 2016/7/22.
 */
public class Fragment1News extends Fragment {
    RecyclerView recyclerView;
    //NewsRecyclerViewInfoGson info;
    ArrayList<NewsRecyclerViewInfo> list;
    String jsonString;
    Fragment1RecyclerViewAdapter recyclerViewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;//下拉刷新的布局，里面有一个recycleView
    int lastPage = 0;
    int pageNum = 0;//获取页面总数

    boolean isLoading;//加载更多的状态
    boolean isEnd;//判断是否到结尾了

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {//普通请求，应该是第一次进来
                list = Dejson.getInstance().getNewsInfoJsonObject(jsonString);
//                info = Dejson.getInstance().getNewsRecyclerViewInfo(jsonString);
                recyclerViewAdapter = new Fragment1RecyclerViewAdapter(Fragment1News.this.getContext(), list);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Fragment1News.this.getActivity()));
                return;
                //分割线
//                recyclerView.addItemDecoration(new RecycleViewDivider(Fragment1News.this.getActivity(),LinearLayoutManager.HORIZONTAL));
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }


            if (msg.what == -1) {//下拉刷新
                list.clear();
                list.addAll(Dejson.getInstance().getNewsInfoJsonObject(jsonString));
                recyclerViewAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);//取消下拉刷新的提示
                return;
            }

            if (msg.what == -2) {//上拉加载
                list.remove(list.size() - 1);
                ArrayList<NewsRecyclerViewInfo> templist = Dejson.getInstance().getNewsInfoJsonObject(jsonString);
                if (templist.size() < 1) {
                    pageNum = lastPage;
                    isEnd = true;
                }
                list.addAll(templist);
                recyclerViewAdapter.notifyItemRemoved(list.size());
//                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment1_news, null, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment1_news_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment1_news_refreshlayout);
        thread();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//监听下拉刷新
                thread(-1);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastItemIndex = manager.findLastVisibleItemPosition();
                int firstItemIndex = manager.findFirstVisibleItemPosition();
//                Log.i("aa","进了onScrolled："+lastItemIndex+":"+list.size());
                //滑动到最后一个并且状态不是加载中,执行加载更多，isLoading默认值false
                if (lastItemIndex >= list.size() - 1 && !isLoading) {//若最后一项的布局高度比较高，列表的下边界在最后一项高度以内 都会触发if
                    if (!isEnd) {
                        list.add(null);
                        recyclerViewAdapter.notifyItemInserted(list.size() - 1);
                    }
                    if (dy > 0) {//触摸点向上托
                        thread(-2);
                    }
                }

            }
        });
        return rootView;
    }

    /**
     * 执行我网络请求
     */
    public void thread() {//默认普通的请求
        thread(0);
    }

    public void thread(final int state) {
        switch (state) {
            case -1://下拉刷新
                lastPage = 1;//默认第一页
                if(pageNum != 0 && isEnd){//如果已经拉到末尾
                    isEnd = false;
                }
                break;
            case -2://上拉加载
                if (isLoading) {
                    return;
                }

                if (pageNum != 0 && isEnd) {
                    Snackbar.make(recyclerView, "没有了哦", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                isLoading = true;
                lastPage++;
                break;
            default://默认第一页
                lastPage = 1;
                break;
        }

        new Thread() {
            @Override
            public void run() {
                jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_NEWS_DATA + lastPage);
                handler.sendEmptyMessage(state);
            }
        }.start();

    }
}
