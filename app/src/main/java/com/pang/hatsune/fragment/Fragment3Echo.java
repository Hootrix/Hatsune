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
                viewPager.setCurrentItem(id,true);
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
