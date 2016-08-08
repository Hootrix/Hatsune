package com.pang.hatsune.utils;

/**
 * Created by Pang on 2016/8/8.
 */
import java.util.Date;

public class TokenTime {
    public static String time() {
        new Date().getTime();
        return "" + System.currentTimeMillis() / 1000 /60 /60;
    }
}
