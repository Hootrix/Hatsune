package com.pang.hatsune.info;

import com.pang.hatsune.interface_abstract.Info;

/**
 * 音乐信息封装类
 * <p/>
 * Created by Administrator on 2016/7/25.
 */
public class SoundInfo implements Info {
    private String id;
    private  String name;
    private  String pic;//图片
    private  String source;//音乐播放地址
    private  String length;
    private  String relay_count;
    private  String user_id;
    private  String view_count;
    private  String status;
    private  String is_pay;
    private  String is_bought;

    public SoundInfo setPic(String pic) {
        this.pic = pic;return this;
    }

    public SoundInfo setId(String id) {
        this.id = id;return this;
    }

    public SoundInfo setName(String name) {
        this.name = name;return this;
    }

    public SoundInfo setSource(String source) {
        this.source = source;return this;
    }

    public SoundInfo setLength(String length) {
        this.length = length;return this;
    }

    public SoundInfo setRelay_count(String relay_count) {
        this.relay_count = relay_count;return this;
    }

    public SoundInfo setUser_id(String user_id) {
        this.user_id = user_id;return this;
    }

    public SoundInfo setView_count(String view_count) {
        this.view_count = view_count;return this;
    }

    public SoundInfo setStatus(String status) {
        this.status = status;return this;
    }

    public SoundInfo setIs_pay(String is_pay) {
        this.is_pay = is_pay;return this;
    }

    public SoundInfo setIs_bought(String is_bought) {
        this.is_bought = is_bought;return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public String getSource() {
        return source;
    }

    public String getLength() {
        return length;
    }

    public String getRelay_count() {
        return relay_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getView_count() {
        return view_count;
    }

    public String getStatus() {
        return status;
    }

    public String getIs_pay() {
        return is_pay;
    }

    public String getIs_bought() {
        return is_bought;
    }
}
