package com.pang.hatsune.info;

import com.pang.hatsune.interface_abstract.Info;

/**
 * 动态页面的recycleView 发布者信息封装类
 * Created by Administrator on 2016/7/25.
 */
public class PublisherInfo implements Info {
    private  String id;
    private  String name;
    private  String avatar;
    private  String avatar_100;
    private  String pay_class;
    private  String pay_status;
    private  String famous_status;
    private  String famous_type;
    private String famous_icon;

    public String getAvatar() {
        return avatar;
    }

    public PublisherInfo setAvatar(String avatar) {
        this.avatar = avatar;return this;
    }

    public String getId() {
        return id;
    }

    public PublisherInfo setId(String id) {
        this.id = id;return this;
    }

    public String getName() {
        return name;
    }

    public PublisherInfo setName(String name) {
        this.name = name;return this;
    }

    public String getAvatar_100() {
        return avatar_100;
    }

    public PublisherInfo setAvatar_100(String avatar_100) {
        this.avatar_100 = avatar_100;return this;
    }

    public String getPay_class() {
        return pay_class;
    }

    public PublisherInfo setPay_class(String pay_class) {
        this.pay_class = pay_class;return this;
    }

    public String getPay_status() {
        return pay_status;
    }

    public PublisherInfo setPay_status(String pay_status) {
        this.pay_status = pay_status;return this;
    }

    public String getFamous_status() {
        return famous_status;
    }

    public PublisherInfo setFamous_status(String famous_status) {
        this.famous_status = famous_status;return this;
    }

    public String getFamous_type() {
        return famous_type;
    }

    public PublisherInfo setFamous_type(String famous_type) {
        this.famous_type = famous_type;return this;
    }

    public String getFamous_icon() {
        return famous_icon;
    }

    public PublisherInfo setFamous_icon(String famous_icon) {
        this.famous_icon = famous_icon;return this;
    }
}
