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
    public static final String DOMAIN_API_SEARCH_DATA = DOMAIN+"/search/input-box-recommend?keyword=";//需要关键字  搜索数据接口
    public static final String DOMAIN_API_HOT_DATA = DOMAIN+"/channel/index?order=hot&_pjax=.main-pjax&page=";//需要页数  热门频道数据接口
    public static final String DOMAIN_API_NEW_DATA = DOMAIN+"/channel/index?order=new&_pjax=.main-pjax&page=";//需要页数  最新频道数据接口
    public static final String DOMAIN_API_ECHO_SUGGEST_BANNER = "http://echosystem.kibey.com/index/banner?android_v=107&app_channel=kibey&position=0&v=9";//echo 推荐页  banner数据接口
    public static final String DOMAIN_API_ECHO_SUGGEST_SOUND_INFO = DOMAIN+"/sound/api-get-more?page=";//需要页数  echo 推荐页  banner数据接口

    public static final String CACHE_DIR = "/storage/emulated/0/Android/data/com.pang.hatsune/cache/AsimpleCache";
    public final static int CACHE_TIME = ACache.TIME_DAY / 2;//缓存时间  12小时

}
