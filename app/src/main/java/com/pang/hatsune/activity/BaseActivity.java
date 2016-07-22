package com.pang.hatsune.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.pang.hatsune.R;

/**
 * Created by Administrator on 2016/7/21.
 */
public class BaseActivity extends AppCompatActivity {
    private boolean isExit;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            // 执行退出
            Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exit);
            System.exit(0);
            this.finish();
        }
    }
}
