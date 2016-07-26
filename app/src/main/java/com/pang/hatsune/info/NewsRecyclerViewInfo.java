package com.pang.hatsune.info;

/**
 *
 * 动态页面的info封装类
 * Created by Administrator on 2016/7/25.
 */
public class NewsRecyclerViewInfo implements  Info{
    private String label_text;
    private String create_time;//时间戳
    private String content;
    private SoundInfo soundInfo;//音乐信息
    private PublisherInfo publisherInfo;//信息发布者
    private String like_num;
    private String comment_num;
    private String relay_num;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel_text() {
        return label_text;
    }

    public void setLabel_text(String label_text) {
        this.label_text = label_text;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public SoundInfo getSoundInfo() {
        return soundInfo;
    }

    public void setSoundInfo(SoundInfo soundInfo) {
        this.soundInfo = soundInfo;
    }

    public PublisherInfo getPublisherInfo() {
        return publisherInfo;
    }

    public void setPublisherInfo(PublisherInfo publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getRelay_num() {
        return relay_num;
    }

    public void setRelay_num(String relay_num) {
        this.relay_num = relay_num;
    }
}
