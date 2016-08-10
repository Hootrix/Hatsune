package com.pang.hatsune.info.gsonfactory;

import com.pang.hatsune.interface_abstract.Info;

/**
 * 名人页的明星信息封装类或者是 视频的信息封装类
 *
 * Created by Administrator on 2016/8/10.
 */
public class Fragment4CelebrityStartinfo implements Info{


    private String mvIdOrStartId;
    private String pic;
    private String name;
    private String descOrChannel;

    public String getMvIdOrStartId() {
        return mvIdOrStartId;
    }

    public void setMvIdOrStartId(String mvIdOrStartId) {
        this.mvIdOrStartId = mvIdOrStartId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescOrChannel() {
        return descOrChannel;
    }

    public void setDescOrChannel(String descOrChannel) {
        this.descOrChannel = descOrChannel;
    }
}
