package com.pang.hatsune.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.pang.hatsune.info.gsonfactory.NewsRecyclerViewInfoGson;

import java.util.List;


/**
 * 动态 fragment
 * Created by Administrator on 2016/7/22.
 */
public class Fragment1News extends Fragment {
    RecyclerView recyclerView;
    NewsRecyclerViewInfoGson info;
    String jsonString;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                info = Dejson.getInstance().getNewsRecyclerViewInfo(jsonString);
                recyclerView.setAdapter(new Fragment1RecyclerViewAdapter(info));
                recyclerView.setLayoutManager(new LinearLayoutManager(Fragment1News.this.getActivity()));

                //分割线
//                recyclerView.addItemDecoration(new RecycleViewDivider(Fragment1News.this.getActivity(),LinearLayoutManager.HORIZONTAL));

                recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        new Thread() {
            @Override
            public void run() {
                jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_NEWS_DATA + "1");
                handler.sendEmptyMessage(1);
            }
        }.start();

        // recyclerView.setAdapter(new Fragment1RecyclerViewAdapter());
        return rootView;
    }
}
