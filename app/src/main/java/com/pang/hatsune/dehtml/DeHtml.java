package com.pang.hatsune.dehtml;

import android.provider.DocumentsContract;

import com.pang.hatsune.acache.ACache;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pang on 2016/7/27.
 */
public class DeHtml {
    private volatile static DeHtml instance;//volatile  轻量级同步锁
    private ACache aCache;//缓存网络请求的字符串

    protected DeHtml() {
    }

    public static DeHtml getInstance() {//仿imageLoader的单例模式

        if (instance == null) {
            synchronized (DeHtml.class) {
                if (instance == null) {
                    instance = new DeHtml();
                }
            }
        }
        return instance;
    }


    /**
     * 获取频道页面的viewpager图片<br/>使用解析html的框架
     *
     * @param jsonString
     * @return
     */
    public HashMap<String, String> getChannelViewPagerImage(String jsonString) {
        HashMap<String, String> list = new HashMap<String, String>();
        Document doc = Jsoup.parse(jsonString);//解析html 文本代码
        Elements div = doc.select(".chn-left_content");
        Iterator<Element> it = div.iterator();
//        System.out.println("hhtjim0:"+jsonString);
//        System.out.println("hhtjim0:"+div.select("h4").text());

        int i = 0;
        while (it.hasNext()) {
            Element divChild = it.next();
            String title = divChild.select("h4").text();
            String url = divChild.attr("style");

            Pattern p = Pattern.compile("\\(([^)]+?)\\-\\d+\\)", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(url);
            while (m.find()) {//while循环操作
                url = m.group(1);
            }
//            System.out.println("hhtjim:"+title);
//            System.out.println("hhtjim:"+url);
            list.put(title, url);
            i++;
        }

        return list;
    }

    /**
     * 获取频道页面的 频道分类 数据 无图片
     *
     * @param jsonString
     * @return
     */
    public ArrayList<String> getChannelClassname(String jsonString) {
        ArrayList<String> list = new ArrayList<String>();

        Pattern p = Pattern.compile("style='color:#999'\\s*?href=\"[^\"]+?\">(.*?)</a>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(jsonString);
//        System.out.println("hhtjim:--======start");
        while (m.find()) {//while循环操作
            String t = m.group(1).trim();
//            System.out.println(t);
            list.add(t);
        }
//            System.out.println("hhtjim:"+title);
//            System.out.println("hhtjim:"+url);
        return list;
    }


    /**
     * 使用正则表达式解析获取频道分类的最新，热门 的频道数据
     *
     * @return
     */
    public ArrayList<Fragment2ChannelHorizontalInfo> getHotAndNewData(String htmlString) {
        ArrayList<Fragment2ChannelHorizontalInfo> list = new ArrayList<Fragment2ChannelHorizontalInfo>();
        String regx = "<a href=\"[^\"]+?(\\d+)\">[\\s\\S]+?\\(([^\\)]+?)(?:-\\d+)?\\)[\\s\\S]+?<h4>([^</h4>]+?)</h4>[\\s\\S]+?</a>";
//        System.out.println("hhtjim7878："+htmlString);

        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlString);
//        System.out.println("====---===:"+matcher.find()+":"+htmlString);
        matcher.reset();
        while (matcher.find()) {
            Fragment2ChannelHorizontalInfo info = new Fragment2ChannelHorizontalInfo();
            info.setId(matcher.group(1));
            info.setUrl(matcher.group(2));
            info.setName(matcher.group(3));
            list.add(info);
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//            System.out.println(matcher.group(3));
//            System.out.println("-------");
        }
        return list;
    }
}
