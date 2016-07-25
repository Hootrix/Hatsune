package com.pang.hatsune.dejson;

import com.google.gson.Gson;
import com.pang.hatsune.acache.ACache;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.NewsRecyclerViewInfo;
import com.pang.hatsune.info.PublisherInfo;
import com.pang.hatsune.info.SoundInfo;
import com.pang.hatsune.info.gsonfactory.NewsRecyclerViewInfoGson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25.
 */
public class Dejson {
    private volatile static Dejson instance;//volatile  轻量级同步锁
    private ACache aCache;//缓存网络请求的字符串

    protected Dejson() {
    }

    public static Dejson getInstance() {//仿imageLoader的单例模式

        if (instance == null) {
            synchronized (Dejson.class) {
                if (instance == null) {
                    instance = new Dejson();
                }
            }
        }
        return instance;
    }

    /**
     * 获取动态页面列表的信息
     * @param jsonString
     * @return
     */
    public NewsRecyclerViewInfoGson getNewsRecyclerViewInfo(String jsonString) {
        Gson gson = new Gson();// new一个Gson对象
        NewsRecyclerViewInfoGson a = gson.fromJson(jsonString, NewsRecyclerViewInfoGson.class);
        return a;
    }


//    public ArrayList<NewsRecyclerViewInfo> getNewsInfoJsonObject(String jsonString) {
//        ArrayList<NewsRecyclerViewInfo> list = new ArrayList<NewsRecyclerViewInfo>();
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray("desc");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                NewsRecyclerViewInfo newsRecyclerViewInfo = new NewsRecyclerViewInfo();
//                JSONObject obj = jsonArray.getJSONObject(i);
//                newsRecyclerViewInfo.setCreate_time(obj.getInt("create_time") + "");
//                newsRecyclerViewInfo.setLabel_text(obj.getString("label_text"));
//                newsRecyclerViewInfo.setContent(obj.getString("content"));
//                newsRecyclerViewInfo.setLike_num(obj.getString("like_num"));
//                newsRecyclerViewInfo.setComment_num(obj.getString("comment_num"));
//                newsRecyclerViewInfo.setRelay_num(obj.getString("relay_num"));
//
//                obj = obj.getJSONObject("sound");
//                newsRecyclerViewInfo.setSoundInfo(new SoundInfo().setId(obj.getString("id")).setName(obj.getString("name")).setPic(obj.getString("pic")).setSource(obj.getString("source")));
//                obj = obj.getJSONObject("publisher");
//                newsRecyclerViewInfo.setPublisherInfo(new PublisherInfo().setName(obj.getString("name")).setAvatar(obj.getString("avatar")).setAvatar_100(obj.getString("avatar_100")));
//                list.add(newsRecyclerViewInfo);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            System.out.println("解析json异常");
//        }
//        return list;
//    }

}
