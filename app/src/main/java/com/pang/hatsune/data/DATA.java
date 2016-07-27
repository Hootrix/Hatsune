package com.pang.hatsune.data;

import com.pang.hatsune.acache.ACache;
import com.pang.hatsune.activity.BaseActivity;

import java.util.Locale;

/**
 * Created by Administrator on 2016/7/25.
 */
public class DATA {

    private static boolean LOCALE = false;
    public static final String DOMAIN = LOCALE ?"http://192.168.9.12":"http://www.app-echo.com";
    public static final String DOMAIN_API_NEWS_DATA = DOMAIN+"/feed/api-friend?page_uid=177480060&page=";//需要页数  动态页数据
    public static final String DOMAIN_API_CHANNEL_DATA = DOMAIN+"/channel/";//频道页数据


    public static final String CACHE_DIR = "/storage/emulated/0/Android/data/com.pang.hatsune/cache/AsimpleCache";
    public final static int CACHE_TIME = ACache.TIME_DAY / 2;//缓存时间  12小时

}
