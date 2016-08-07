package com.pang.hatsune.info;

import com.pang.hatsune.interface_abstract.Info;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Fragment2ChannelHorizontalInfo implements Info {
    private String name;
    private String url;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
