package com.pang.hatsune.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    Fragment1RecyclerViewAdapter  recyclerViewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;//下拉刷新的布局，里面有一个recycleView
    int lastPage = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                list = Dejson.getInstance().getNewsInfoJsonObject(jsonString);
//                info = Dejson.getInstance().getNewsRecyclerViewInfo(jsonString);
                recyclerViewAdapter =   new Fragment1RecyclerViewAdapter(Fragment1News.this.getContext(),list);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Fragment1News.this.getActivity()));

                //分割线
//                recyclerView.addItemDecoration(new RecycleViewDivider(Fragment1News.this.getActivity(),LinearLayoutManager.HORIZONTAL));
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
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
            public void onRefresh() {
                //自己写更新适配器的方法
                // refreshData(adapter);
//TODO
                handler.postDelayed(new Runnable() {
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        thread(-1);
//                    listDatas.addAll(listDatas);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });

        return rootView;
    }

    /**
     *
     * 执行我网络请求
     */
    public void thread(){//
        thread(0);
    }

    public void thread(final int state){
        switch (state){
            case -1://下拉刷新
                break;
            case -2://上拉加载
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
