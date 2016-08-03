package com.pang.hatsune.http;

import android.text.TextUtils;

import com.pang.hatsune.acache.ACache;
import com.pang.hatsune.data.DATA;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Pang on 2016/7/23.
 */
public class HttpResquestPang {

    private volatile static HttpResquestPang instance;//volatile  轻量级同步锁
    private ACache aCache;//缓存网络请求的字符串

    protected HttpResquestPang() {
    }

    /**
     * 检查缓存的内容是否错误</br>用于重新请求
     */
    public boolean checkError() {
        return checkError("");
    }
//TODO
    public boolean checkError(String keyword) {
        switch (keyword) {
            case "status:200":

                break;
            default:
                return  false;
//                break;
        }
        return true;
    }


    public static HttpResquestPang getInstance() {//仿imageLoader的单例模式

        if (instance == null) {
            synchronized (HttpResquestPang.class) {
                if (instance == null) {
                    instance = new HttpResquestPang();
                }
            }
        }
        return instance;
    }

    private HashMap<String, String> getDefaultHeaders() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("x-v", "107");
        hashMap.put("x-dt", "00000000-0d0d-6c49-ffff-fffffefd923d");
        hashMap.put("x-vs", "5.2.1");
        hashMap.put("x-a-sn", "c5d93ca3d04d1f027b30f52a750a70a2615fbc9a");
        hashMap.put("x-net", "WIFI");
        hashMap.put("x-sn", "5447780032dd82422231957bdd6c346e56c102e0/1469197053");
        hashMap.put("x-c", "2");
        hashMap.put("x-av", "9");
        hashMap.put("Cookie", "PHPSESSID=h3ojsl6a7aq22tvnnafsm1d5i0");


//        hashMap.put("Cookie", "PHPSESSID=4l1t5o6iovrufi91gojob5tuq6");
//        hashMap.put("Cookie", "PHPSESSID=0ip3sakccou7hmd2mu2ahhofh7");//0901过期
//        hashMap.put("Host", "echosystem.kibey.com");


        //可用
//        x-a-sn: 9a4246842c4195a770178c5054fed151c67f0dc1
//        x-sn: e777c0e12c7eb7f0e7382f940a396e9389a4ae8a/1470199362
        return hashMap;
    }

    public HashMap<String, String> getPCHeaders() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Cookie", "PHPSESSID=h3ojsl6a7aq22tvnnafsm1d5i0");

        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2729.4 Safari/537.36");

//        hashMap.put("Cookie", "PHPSESSID=4l1t5o6iovrufi91gojob5tuq6");
//        hashMap.put("Host", "echosystem.kibey.com");
        return hashMap;
    }


    public String get(String url) {
        return get(url, getDefaultHeaders());
    }

    public String get(String url, HashMap<String, String> header) {
        return get(url, header, "utf-8");
    }

    public String get(String url, HashMap<String, String> header, String charsetName) {
        String result = "";
        aCache = ACache.get(new File(DATA.CACHE_DIR));
//        System.out.println("===get请求地址：" + url);
        try {
            result = aCache.getAsString(url);
            if (TextUtils.isEmpty(result)) {
                throw new NullPointerException();
            }
            System.out.println("===get=读取缓存");
            System.out.println("====get=result::" + result);
            return result;
        } catch (NullPointerException e) {

            loop: while (true){ // 循环处理避免返回空白内容
                System.out.println("===get=无缓存开始请求");
                result = "";
                try {
                    URL u = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) u.openConnection();// 向下转型为HttpURLConnection
                    conn.setDoInput(true);// 系统默认为true，可以不用
                    conn.setConnectTimeout(5000);// 设置超时
                    if (header != null) {
                        Iterator<Map.Entry<String, String>> itor = header.entrySet().iterator();
                        while (itor.hasNext()) {
                            Map.Entry<String, String> en = itor.next();
                            conn.addRequestProperty(en.getKey(), en.getValue());// 请求头设置
                        }
                    }

                    InputStream in = conn.getInputStream();// 自定义请求头时，获取服务器返回的输入流
                    InputStreamReader inr = new InputStreamReader(in, charsetName);// 转换流，使用编码
                    BufferedReader bfr = new BufferedReader(inr);
                    String s = "";
                    while ((s = bfr.readLine()) != null) {
//                     System.out.println("=---------"+s);
                        result += s;
                    }
                    bfr.close();
                } catch (Exception ee) {
                    e.printStackTrace();
                    System.err.println("HttpResquestClass.post()---Exception");
                }
                System.out.println("===get请求地址：" + url+" ===result:" + result);

                if(!TextUtils.isEmpty(result)){
                    break loop;
                }
            }
        }

        if (!TextUtils.isEmpty(result)) {//TODO
//            aCache.put(url, result, DATA.CACHE_TIME);
//            System.out.println("====保存缓存！");
        }
        return result;
    }

//
//    public void post(String url) {
//        try {
//            URL urlObj = new URL(url);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();
//            // urlObj.openStream();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
