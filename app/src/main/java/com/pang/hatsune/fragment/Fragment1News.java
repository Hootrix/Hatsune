package com.pang.hatsune.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pang.hatsune.R;

/**
 * 动态 fragment
 * Created by Administrator on 2016/7/22.
 */
public class Fragment1News extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = LayoutInflater.from(this.getActivity()).inflate(R.layout.fragment1,null);
        View v = inflater.inflate(R.layout.fragment_fragment1_news, null);

        return v;
    }
}
