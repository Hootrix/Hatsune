package com.pang.hatsune.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pang.hatsune.R;
import com.pang.hatsune.custom_view.IndicatorView;
import com.pang.hatsune.fragment.image.ImageFragment;
import com.pang.hatsune.fragment.viewpager.Find;
import com.pang.hatsune.fragment.viewpager.Suggest;

import java.util.ArrayList;
import java.util.List;


/**
 * 频道
 * A simple {@link Fragment} subclass.
 */
public class Fragment2Channel extends Fragment {
ViewPager galleryViewpager;
    ArrayList<Fragment> list;
    public Fragment2Channel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment2_channel,null);
        galleryViewpager = (ViewPager) rootView.findViewById(R.id.fragment2_channel_gallery_viewpager);

        ImageView imageView = new ImageView(this.getActivity());
        imageView.setImageResource(R.drawable.echo_round_logo);

        ImageView imageView2 = new ImageView(this.getActivity());
        imageView2.setImageResource(R.drawable.disk);
        list = new ArrayList<Fragment>();

        list.add(new ImageFragment().setView(imageView));
        list.add(new ImageFragment().setView(imageView2));

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

        IndicatorView   mIndicatorView = (IndicatorView) rootView.findViewById(R.id.id_indicator) ;
        mIndicatorView.setViewPager(galleryViewpager);//设置滑动指示器
        return  rootView;
    }

}
