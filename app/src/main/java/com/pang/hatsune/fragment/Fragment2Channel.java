package com.pang.hatsune.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.custom_view.IndicatorView;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.fragment.image.ImageFragment;
import com.pang.hatsune.fragment.viewpager.Find;
import com.pang.hatsune.fragment.viewpager.Suggest;
import com.pang.hatsune.http.HttpResquestPang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 频道
 * A simple {@link Fragment} subclass.
 */
public class Fragment2Channel extends Fragment {
    ViewPager galleryViewpager;
    ArrayList<Fragment> list;
    HashMap httpResutl;
    View rootView;

    RecyclerView horizontalRecyclerView;//横向的recycleView
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                setData();


                galleryViewpager.setAdapter(new FragmentPagerAdapter(Fragment2Channel.this.getActivity().getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return list.get(position);
                    }

                    @Override
                    public int getCount() {
                        return list.size();
                    }
                });

                IndicatorView mIndicatorView = (IndicatorView) rootView.findViewById(R.id.id_indicator);
                mIndicatorView.setViewPager(galleryViewpager);//设置滑动指示器
            }
        }
    };


    public Fragment2Channel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fragment2_channel, null);
        galleryViewpager = (ViewPager) rootView.findViewById(R.id.fragment2_channel_gallery_viewpager);
        horizontalRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment2_channel_horizontal_recyclerView);


        new Thread() {
            @Override
            public void run() {
//        super.run();
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2729.4 Safari/537.36");
                String httpString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_CHANNEL_DATA,hashMap);
                httpResutl = DeHtml.getInstance().getChannelViewPagerImage(httpString);
                handler.sendEmptyMessage(1);

                //todo 请求网络数据获取频道分类的图片
            }
        }.start();

        return rootView;
    }


    /**
     * 设置图片数据
     */
    public void setData() {
        list = new ArrayList<Fragment>();
        Iterator<Map.Entry<String, String>> itor = httpResutl.entrySet().iterator();
        while(itor.hasNext()){
            Map.Entry<String, String> t = itor.next();
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(Fragment2Channel.this.getContext());
            simpleDraweeView.setImageURI(Uri.parse(t.getValue()));
//            simpleDraweeView.setMaxWidth(600);

            list.add(new ImageFragment().setView(simpleDraweeView));
//            System.out.println(itor.next().getKey());
        }
    }

}
