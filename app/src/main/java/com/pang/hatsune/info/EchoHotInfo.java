package com.pang.hatsune.info;

import com.pang.hatsune.interface_abstract.Info;

import java.util.List;

/**
 * echo页面 hot页的信息封装类<br/>
 * 数据来源为官网 正则匹配之结果.这里是用GsonFormat插件一键生成
 * Created by Administrator on 2016/8/4.
 */
public class EchoHotInfo  implements Info {

    /**
     * pic : pic
     * title : title
     * channel : channel
     * id : 112
     */

    private List<DayHotListBean> dayHotList;
    /**
     * pic : pic
     * title : title
     * username : username
     * id : 112
     */

    private List<WeekHotListBean> weekHotList;

    public List<DayHotListBean> getDayHotList() {
        return dayHotList;
    }

    public void setDayHotList(List<DayHotListBean> dayHotList) {
        this.dayHotList = dayHotList;
    }

    public List<WeekHotListBean> getWeekHotList() {
        return weekHotList;
    }

    public void setWeekHotList(List<WeekHotListBean> weekHotList) {
        this.weekHotList = weekHotList;
    }

    public static class DayHotListBean {
        private String pic;
        private String title;
        private String channel;
        private int id;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class WeekHotListBean {
        private String pic;
        private String title;
        private String username;
        private int id;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
