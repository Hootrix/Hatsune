package com.pang.hatsune.token;

import com.pang.hatsune.utils.MD5;
import com.pang.hatsune.utils.TokenTime;

/**
 * token
 * Created by Administrator on 2016/8/8.
 */
public class Token {
    public static String getLinkSearchToken(String key, String num) {
        String token = MD5.stringMD5(TokenTime.time() + num + key + "pangtoke");//md5(time().$page.$keyword."pangtoke")
        return token;
    }
}
