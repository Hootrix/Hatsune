package com.pang.hatsune.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pang.hatsune.R;

/**
 * 频道
 * A simple {@link Fragment} subclass.
 */
public class Fragment2Channel extends Fragment {


    public Fragment2Channel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2_channel, container, false);
    }

}
