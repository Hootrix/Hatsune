package com.pang.hatsune.dehtml;

import android.provider.DocumentsContract;

import com.pang.hatsune.acache.ACache;
import com.pang.hatsune.info.EchoHotInfo;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
//        matcher.reset();
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


    /**
     * 使用正则表达式解析获取Echo页面的本周，今日的热门数据//// TODO: 2016/8/4
     *
     * @return
     */
    public EchoHotInfo getEchoHotData(String htmlString) {
        EchoHotInfo info = new EchoHotInfo();
        String regx = "";
        Pattern hotDataPattern = null;
        Matcher hotDataMatcher = null;

//        String regx = "<a href=\"[^\"]+?(\\d+)\">[\\s\\S]+?\\(([^\\)]+?)(?:-\\d+)?\\)[\\s\\S]+?<h4>([^</h4>]+?)</h4>[\\s\\S]+?</a>";


//		regx = "data-sid=\"([\\d,]+)\">月榜</i>";
        regx = "data-sid=\"([\\d,]+)\">周榜</i>";
        hotDataPattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        hotDataMatcher = hotDataPattern.matcher(htmlString);

        String weekIdArr[] = null;//周榜数组
        while (hotDataMatcher.find()) {
            weekIdArr = hotDataMatcher.group(1).split(",");
        }

        String hotIdArr[] = null;//热门榜单数组
        regx = "热门榜单<i class=\"play-all js-mp-play-one\" data-sid=\"([\\d,]+)\">";
        hotDataPattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        hotDataMatcher = hotDataPattern.matcher(htmlString);
        while (hotDataMatcher.find()) {
            hotIdArr = hotDataMatcher.group(1).split(",");
        }


//        regx = "<a href=\"/sound/(\\d+)\">[\\s\\S]+?<img src=\"([^\"\\?]+)(?:\\?[^\">]+)?\">[\\s\\S]+?<h4>([^>]+?)</h4>\\s+<h5>([^>]+?)</h5>";//去掉图片裁剪尾巴
        regx = "<a href=\"/sound/(\\d+)\">[\\s\\S]+?<img src=\"([^\"]+)\">[\\s\\S]+?<h4>([^>]+?)</h4>\\s+<h5>([^>]+?)</h5>";//去掉图片裁剪尾巴
        hotDataPattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        hotDataMatcher = hotDataPattern.matcher(htmlString);
        List<EchoHotInfo.DayHotListBean> dayList = new ArrayList<EchoHotInfo.DayHotListBean>();
        List<EchoHotInfo.WeekHotListBean> weekList = new ArrayList<EchoHotInfo.WeekHotListBean>();
        w1:while (hotDataMatcher.find()) {
            EchoHotInfo.DayHotListBean day = new EchoHotInfo.DayHotListBean();
            EchoHotInfo.WeekHotListBean week = new EchoHotInfo.WeekHotListBean();
            w2:
            for (String id : hotIdArr) {
                if (id.equals(hotDataMatcher.group(1))) {
                    day.setId(Integer.valueOf(hotDataMatcher.group(1)));
                    day.setChannel(hotDataMatcher.group(4));
                    day.setPic(replaceWH(hotDataMatcher.group(2)));
                    day.setTitle(hotDataMatcher.group(3));
                    dayList.add(day);
                    continue w1;
                }
            }


            w3:
            for (String id : weekIdArr) {
                if (id.equals(hotDataMatcher.group(1))) {
                    week.setId(Integer.valueOf(hotDataMatcher.group(1)));
                    week.setUsername(hotDataMatcher.group(4));
                    week.setPic(replaceWH(hotDataMatcher.group(2)));
                    week.setTitle(hotDataMatcher.group(3));
                    weekList.add(week);
                    continue w1;
                }
            }

//            info.set
//            System.out.println(m.group(1)); //id
//            System.out.println(m.group(2));//pic
//            System.out.println(m.group(3));//sound name
//            System.out.println(m.group(4));//channel or username
        }

        info.setDayHotList(dayList);
        info.setWeekHotList(weekList);
        return info;
    }

    /**
     * 替换图片裁剪的宽高
     *
     * @return
     */
    private String replaceWH(String url) {
        int w = 300;
        return  url.replace("!100","!"+w).replace("-100","-"+w).replace("w/100","w/"+w);
    }
}
