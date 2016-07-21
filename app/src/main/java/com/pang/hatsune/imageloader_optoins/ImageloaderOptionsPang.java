package com.pang.hatsune.imageloader_optoins;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.pang.hatsune.R;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ImageloaderOptionsPang {
    // 显示图片的配置

    public static final DisplayImageOptions normalImageOptions = new DisplayImageOptions.Builder()
//            .showImageOnLoading(R.drawable.default_image)
//            .showImageOnFail(R.drawable.default_image)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();


    public static final DisplayImageOptions userImageOptions = new DisplayImageOptions.Builder()
//            .showImageOnLoading(R.drawable.icon_default_store_photo)
//            .showImageOnFail(R.drawable.icon_default_store_photo)
            .cacheInMemory(true).cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .displayer(new FadeInBitmapDisplayer(850))// 是否图片加载好后渐入的动画时间
                    //new RoundedBitmapDisplayer(60)  圆角处理
                    //	.displayer(new CircleBitmapDisplayer(60))//圆形处理
            .displayer( new CircleBitmapDisplayer(0xffffffff, 5) )//圆形图片半径
            .build();

    /**
     * 清除imageLoader内存和本地缓存
     */
    public static void clear() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.clearDiskCache();// 清除本地缓存
        imageLoader.clearMemoryCache(); // 清除内存缓存
    }
}
