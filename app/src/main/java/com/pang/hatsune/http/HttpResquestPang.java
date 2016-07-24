package com.pang.hatsune.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pang on 2016/7/23.
 */
public class HttpResquestPang {

    private HttpResquestPang() {
    }

    public static HttpResquestPang getInstance() {
        HttpResquestPang o = null;
        if (o == null) {
             o = new HttpResquestPang();
        }
        return o;
    }

    public void get(String url) {
        try {
            URL urlObj = new URL(url);
           HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();
           // urlObj.openStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
