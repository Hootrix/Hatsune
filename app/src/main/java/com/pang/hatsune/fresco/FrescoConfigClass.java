package com.pang.hatsune.fresco;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Fresco的配置类 统一管理  显示图片
 * <p/>
 * Created by Pang on 2016/7/27.
 */
public class FrescoConfigClass {
    private Context context;

    private FrescoConfigClass(Context context ) {
        this.context = context;
    }

    private FrescoConfigClass() {
    }


    /**
     * 获取有图片加载进度条的GenericDraweeHierarchy配置
     */
    public static GenericDraweeHierarchy getLoadingProgressHierarchy() {

        ProgressBarDrawable progress = new ProgressBarDrawable();
        progress.setBackgroundColor(0xff858585);
        progress.setColor(0xffffffff);
        progress.setBarWidth(5);
        progress.setHideWhenZero(true);

        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(null);//测试发现这里获取资源可以为空
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setProgressBarImage(progress)
                .build();
        return hierarchy;
    }


}
