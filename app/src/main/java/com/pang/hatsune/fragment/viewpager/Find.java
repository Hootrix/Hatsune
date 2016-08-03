package com.pang.hatsune.fragment.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pang.hatsune.R;
import com.pang.hatsune.custom_view.IndicatorView;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dejson.Dejson;
import com.pang.hatsune.fragment.image.ImageFragment;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.gsonfactory.EchoSuggestBannerInfo;

import java.util.ArrayList;

/**
 * Created by Pang on 2016/7/23.
 */
public class Find extends Fragment {
    ViewPager imageViewpager;
    ArrayList<ImageFragment> imageFragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3_echo_viewpager_fragment2_find, null);
//        imageViewpager = (ViewPager) v.findViewById(R.id.image_viewpager);
//        IndicatorView mIndicatorView = (IndicatorView) v.findViewById(R.id.id_indicator);
//        mIndicatorView.setViewPager(imageViewpager);//设置滑动指示器

//        imageViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return imageFragmentList.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return imageFragmentList.size();
//            }
//        });


        return v;
    }


    /**
     * 设置banner的图片数据
     */
    public void setImageData() {
        imageFragmentList = new ArrayList<ImageFragment>();

        imageFragmentList.add(new ImageFragment().setView(null));
    }
}
