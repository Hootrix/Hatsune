package com.pang.hatsune.fresco;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/7/26.
 */
public class FrescoAppliacationPang  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);//初始化Fresco  也可以用在application。setContentView之前
    }
}
