package com.pang.hatsune.dejson;

import com.google.gson.Gson;
import com.pang.hatsune.acache.ACache;
import com.pang.hatsune.http.HttpResquestPang;
//import com.pang.hatsune.info.NewsRecyclerViewInfo;
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
     *
     * @param jsonString
     * @return
     */
    public NewsRecyclerViewInfoGson getNewsRecyclerViewInfo(String jsonString) {
        Gson gson = new Gson();// new一个Gson对象
        NewsRecyclerViewInfoGson a = gson.fromJson(jsonString, NewsRecyclerViewInfoGson.class);
        return a;
    }


    public ArrayList<NewsRecyclerViewInfo> getNewsInfoJsonObject(String jsonString) {
        ArrayList<NewsRecyclerViewInfo> list = new ArrayList<NewsRecyclerViewInfo>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("desc");
            if (jsonObject.getInt("status") != 100 || jsonObject.getString("desc").equals("没有更多动态")) {
                return null;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                NewsRecyclerViewInfo newsRecyclerViewInfo = new NewsRecyclerViewInfo();
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getInt("type") != 0 || !obj.has("sound")) {
                    continue;
                }
                newsRecyclerViewInfo.setCreate_time(obj.getInt("create_time") + "");
                newsRecyclerViewInfo.setLabel_text(obj.getString("label_text"));
                newsRecyclerViewInfo.setContent(obj.getString("content"));
                newsRecyclerViewInfo.setLike_num(obj.getInt("like_num") + "");
                newsRecyclerViewInfo.setComment_num(obj.getInt("comment_num") + "");
                newsRecyclerViewInfo.setRelay_num(obj.getInt("relay_num") + "");
                JSONObject soundObj = obj.getJSONObject("sound");
                newsRecyclerViewInfo.setSoundInfo(new SoundInfo().setId(soundObj.getString("id")).setName(soundObj.getString("name")).setPic(soundObj.getString("pic")).setSource(soundObj.getString("source")));
                if (obj.has("publisher")) {
                    JSONObject publisherObj = obj.getJSONObject("publisher");
                    newsRecyclerViewInfo.setPublisherInfo(new PublisherInfo().setName(publisherObj.getString("name")).setAvatar(publisherObj.getString("avatar")).setAvatar_100(publisherObj.getString("avatar_100")));
                }
                list.add(newsRecyclerViewInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("解析json异常");
        }
        return list;
    }

}
