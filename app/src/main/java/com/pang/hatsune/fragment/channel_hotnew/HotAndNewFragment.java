package com.pang.hatsune.fragment.channel_hotnew;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pang.hatsune.R;

/**
 * 频道页面的  页面和最新数据的fragment
 * Created by Administrator on 2016/7/29.
 */
public class HotAndNewFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //todo
    public void setHot(){

    }
     public void setNew(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_channel_hotandnewfragment_layout, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.fragment2_channel_hotandnewfragment_layout_recyclerview);

        return v;
    }
}
