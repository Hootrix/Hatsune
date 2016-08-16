package com.pang.hatsune.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.pang.hatsune.R;

/**
 * Created by Administrator on 2016/7/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private boolean isExit;
    private int topBaralpha = -1;
    private boolean topBarBGShow;//判断是否显示顶部的黄色背景渐变


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    public boolean isTopBarBGShow() {
        return topBarBGShow;
    }

    public void setTopBarBGShow(boolean topBarBGShow) {
        this.topBarBGShow = topBarBGShow;
    }

    /**
     * 用于统一调度顶部的导航条背景透明度
     *
     * @return
     */
    public int getTopBaralpha() {
        return topBaralpha;
    }

    /**
     * 用于统一调度顶部的导航条背景透明度
     *
     * @return
     */
    public void setTopBaralpha(int topBaralpha) {
        this.topBaralpha = topBaralpha;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(setLayoutResourceID());
        initView();
        initViewData();
    }

    /***
     * 给绑定的控件设置数据
     */
    protected void initViewData() {
    }

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init() {
    }

    /**
     * 给view控件赋值 绑定
     */
    protected abstract void initView();


    /**
     * 设置view布局界面的layout ID
     */
    protected abstract int setLayoutResourceID();


    /**
     * 用于findViewById 绑定控件 ID
     */
    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }

    /***
     * 执行跳转 不带数据包Bundle
     */
    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /***
     * 执行跳转 带数据包Bundle
     */
    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }


    /**
     * 返回键监听 是否退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (this instanceof MainActivity) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {//如果侧滑栏已打开
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    exit();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);


    }

//    /**
//     *  监听后退键
//     */
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }else{
//            exit();
//        }
//
//        super.onBackPressed();
//    }


    /**
     * 执行退出方法
     */

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再次点击返回键退出",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            exit(true);
        }
    }

    /**
     * 不判断直接退出
     *
     * @param areYouSure
     */
    private void exit(boolean areYouSure) {
        // 执行退出
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
        System.exit(0);
        this.finish();
    }


    /**
     * 判断是否有网络连接
     */

    @Override
    protected void onResume() {
        super.onResume();
        validDataInternet();
    }

    public boolean validDataInternet() {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        if (manager == null) {
            openWirelessSet();
            return false;
        } else {
            NetworkInfo[] networkInfos = manager.getAllNetworkInfo();
            if (networkInfos != null) {
                for (NetworkInfo networkInfo : networkInfos) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        openWirelessSet();
        return false;
    }

    /**
     * 打开网络设置对话框
     */

    private void openWirelessSet() {
        AlertDialog.Builder diaBuilder = new AlertDialog.Builder(this);
        diaBuilder.setInverseBackgroundForced(true);
        diaBuilder.setCancelable(false);
        diaBuilder.setMessage("请检查网络");
        diaBuilder.setTitle("::ERROR::");
        diaBuilder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent in = new Intent(Settings.ACTION_WIFI_SETTINGS);
                BaseActivity.this.startActivity(in);
            }
        });
        diaBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                exit(true);
            }
        });
        diaBuilder.show();
    }

}
