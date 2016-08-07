package com.pang.hatsune.fragment;

import android.support.v4.app.Fragment;

import com.pang.hatsune.activity.BaseActivity;

/**
 * Created by Pang on 2016/8/6.
 */
public abstract class BaseFragment extends Fragment {



    public BaseActivity getBaseActivity() {
        return (BaseActivity) this.getActivity();
    }


}
