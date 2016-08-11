package com.pang.hatsune.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment4CelebrityListAdapter;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.gsonfactory.Fragment4CelebrityStartinfo;

import java.util.ArrayList;

/**
 * 名人
 * A simple {@link Fragment} subclass.
 */
public class Fragment4Celebrity extends BaseFragment {
    RecyclerView recyclerView;
    ArrayList<Fragment4CelebrityStartinfo> StartsList;
    ArrayList<Fragment4CelebrityStartinfo> MvsList;
    ArrayList<Fragment4CelebrityStartinfo> RecommendStartsList;
    private final int GO = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == GO) {
                recyclerView.setLayoutManager(new LinearLayoutManager(Fragment4Celebrity.this.getContext()));
                Fragment4CelebrityListAdapter adapter = new Fragment4CelebrityListAdapter(Fragment4Celebrity.this.getContext(), RecommendStartsList);
                ImageView headerImage = new ImageView(Fragment4Celebrity.this.getContext());
                headerImage.setImageResource(R.drawable.start_banner);
                adapter.setmHeaderView(headerImage);

//                adapter.setmHorizontalListview();
//                recyclerView.setAdapter();
            }
        }
    };

    public Fragment4Celebrity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment4_celebrity, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment3_celebrity_list);
        new Thread() {
            @Override
            public void run() {
//        super.run();
                String htmlString = HttpResquestPang.getInstance().get(DATA.DOMAIN);
                StartsList = DeHtml.getInstance().getCelebrityStarts(htmlString);
                MvsList = DeHtml.getInstance().getCelebrityMvs(htmlString);
                htmlString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_CELEBRITY_RECOMMEND_START_DATA);
                RecommendStartsList = DeHtml.getInstance().getCelebrityRecommendStarts(htmlString);
                handler.sendEmptyMessage(GO);
            }
        }.start();

        return rootView;
    }


    public View getHorizontalListview() {// TODO: 2016/8/12
View v = LayoutInflater.from(Fragment4Celebrity.this.getContext()).inflate(R.layout.)

        return null;
    }

}
