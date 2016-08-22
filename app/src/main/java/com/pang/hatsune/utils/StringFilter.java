package com.pang.hatsune.utils;

/**
 * 字符串过滤操作
 * Created by Pang on 2016/8/13.
 */
public class
StringFilter {
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
        return replaceWH(url, 250);
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
        return str.replaceAll("(?i)echo回声", "Hatsune初音").replace("回声", "初音");
    }


    /**
     * TextView里面过滤关键字为指定颜色
     * @param keyword
     * @param allStr
     * @return
     */
    public  CharSequence fitlerColor(String keyword,String allStr){
        allStr = allStr.replaceAll("(?i)"+keyword,"{$0}");
        CharSequence formatted = ColorPhrase.from(allStr)
                .withSeparator("{}")
                .innerColor(0xFFE6454A)
                .outerColor(0xFF666666)
                .format();
        return formatted;
    }


    /**
     * 计算mp3的音乐播放时间
     * @param duration
     * @return
     */
    public String calcDurtions(int duration){
        //int duration = 2427;
        int seconds = (int) (duration / 1000);// 总时间
        int minute = 0;
        if (seconds >= 60) {
            int index = seconds / 60;
            minute = index;
            seconds = seconds - index * 60;
        }
        String result = String.format( "%02d",minute) + ":" + String.format( "%02d",seconds);
        return  result;
    }
}
