package com.pang.hatsune.fragment.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 用于viewpager 的图片轮滑
 * Created by Administrator on 2016/7/27.
 */
public class ImageFragment extends Fragment{

    private View rootView;

   public ImageFragment setView(View v){
       rootView = v;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return rootView;
    }
}
