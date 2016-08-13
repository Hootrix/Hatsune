package com.pang.hatsune.utils;

/**
 * 字符串过滤操作
 * Created by Pang on 2016/8/13.
 */
public class StringFilter {
    private volatile static StringFilter instance;//volatile  轻量级同步锁

    public static StringFilter getInstance() {//仿imageLoader的单例模式

        if (instance == null) {
            synchronized (StringFilter.class) {
                if (instance == null) {
                    instance = new StringFilter();
                }
            }
        }
        return instance;
    }


    /**
     * 替换图片裁剪的宽高
     *
     * @return
     */
    public String replaceWH(String url) {
        return replaceWH(url, 300);
    }

    public String replaceWH(String url, int w) {
        // int w = 300;
        return url.replace("!100", "!" + w).replace("-100", "-" + w).replace("w/100", "w/" + w).replace("50x50", w + "x" + w);
    }


    /**
     * 过滤搜索关键字
     *
     * @return
     */
    public String fitlerSearchKeyword(String str) {
        return str.replace("_", "+").replace("-", "+").replace("&", "+");
    }

    /**
     * 过滤回声关键字
     *
     * @return
     */
    public String fitlerEchoKeyword(String str) {
        return str.replace("回声", "初音");
    }
}
