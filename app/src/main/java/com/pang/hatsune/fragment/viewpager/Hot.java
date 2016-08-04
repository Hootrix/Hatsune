package com.pang.hatsune.fragment.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pang.hatsune.R;

/**
 * Created by Pang on 2016/7/23.
 */
public class Hot extends Fragment {

    RecyclerView gridRecyclerView;
//    ArrayList<ImageFragment> imageFragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3_echo_viewpager_fragment2_hot, null);
        gridRecyclerView = (RecyclerView) v.findViewById(R.id.fragment3_echo_viewpager_fragment2_hot_grid);

        return v;
    }


}
