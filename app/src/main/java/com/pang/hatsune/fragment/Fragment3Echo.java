package com.pang.hatsune.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.pang.hatsune.R;
import com.pang.hatsune.custom_view.IndicatorView;
import com.pang.hatsune.fragment.viewpager.Find;
import com.pang.hatsune.fragment.viewpager.Suggest;

import java.util.ArrayList;

/**
 * 回声
 * A simple {@link Fragment} subclass.
 */
public class Fragment3Echo extends Fragment {
    ViewPager viewPager;
    ArrayList<Fragment> list;
    FragmentManager manager;
    RadioGroup radioGroup;


    public Fragment3Echo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        radioGroup = (RadioGroup) Fragment3Echo.this.getActivity().findViewById(R.id.main_top_radiogroup);
        setData();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment3_echo, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.fragment3_viewpager);
        manager = this.getChildFragmentManager();
        viewPager.setAdapter(new FragmentPagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int id = 0;
                switch (position) {
                    case 0:
                        id = R.id.main_top_radiogroup_r1;
                        break;
                    case 1:
                        id = R.id.main_top_radiogroup_r2;
                        break;
                    default:
                        id = R.id.main_top_radiogroup_r1;
                        break;
                }
                radioGroup.check(id);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = 0;
                switch (checkedId) {
                    case R.id.main_top_radiogroup_r1:
                        id = 0;
                        break;
                    case R.id.main_top_radiogroup_r2:
                        id = 1;
                        break;
                    default:
                        break;
                }
                viewPager.setCurrentItem(id, true);
            }
        });
        return v;
    }


    public void setData() {
        list = new ArrayList<Fragment>();
        list.add(new Suggest());
        list.add(new Find());
    }
}
