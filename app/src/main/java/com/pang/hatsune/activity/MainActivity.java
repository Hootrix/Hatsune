package com.pang.hatsune.activity;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pang.hatsune.R;
import com.pang.hatsune.fragment.Fragment1News;
import com.pang.hatsune.fragment.Fragment2Channel;
import com.pang.hatsune.fragment.Fragment3Echo;
import com.pang.hatsune.fragment.Fragment4Celebrity;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends BaseActivity {//AppCompatActivity
    NavigationView navigationView;//侧滑栏
    DrawerLayout drawer;//整个视图布局  包含侧滑栏
    RadioGroup radioGroupTop;//顶部导航条的radiogroup
    RadioGroup radioGroupBottom;//底部的radiogroup
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ArrayList<Fragment> fragmentList;
    Fragment1News fragment1News;
    Fragment2Channel fragment2Channel;
    Fragment3Echo fragment3Echo;
    Fragment4Celebrity fragment4Celebrity;
    TextView topTitle;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);//设置toolbar
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        radioGroupTop = (RadioGroup) this.findViewById(R.id.main_top_radiogroup);
//        radioGroupBottom = (RadioGroup) this.findViewById(R.id.main_bottom_radiogroup);
//        topTitle = (TextView) this.findViewById(R.id.main_top_title);
//        topTitle.setVisibility(View.GONE);//默认隐藏标题
//        fragmentManager = this.getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
////topTitle.setBackgroundResource(android.support.design.R.drawable.);//R.drawable._selector_dark_borderless
//        RadioButton ra1  = (RadioButton) findViewById(R.id.main_top_radiogroup_r1);
//        RadioButton ra2  = (RadioButton) findViewById(R.id.main_top_radiogroup_r2);
//        Drawable drawable1 = getResources().getDrawable(R.drawable.main_top_title_radiogroup_selector1);
//        int wh = 50;
//        drawable1.setBounds(0, 0, wh, wh);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        ra1.setCompoundDrawables(drawable1, null, null, null);//只放左边
//
//         drawable1 = getResources().getDrawable(R.drawable.main_top_title_radiogroup_selector2);
//        drawable1.setBounds(0, 0, wh, wh);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
//        ra2.setCompoundDrawables(drawable1, null, null, null);//只放左边
//
//        setFragmentListData();
//        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment3Echo);
//        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment1News);
//        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment2Channel);
//        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment4Celebrity);
//        fragmentTransaction.commit(); //commit()方法提交对事务的操作
//        radioGroupBottom.check(R.id.main_bottom_radiogroup_r3);//默认点亮第三个radiobutton
//        radioGroupTop.check(R.id.main_top_radiogroup_r1);//默认顶部的第一个点亮：推荐按钮
//        //监听浮动按钮
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //Snackbar 为底部的弹窗效果
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


////        绑定toolbar左边的菜单图标的动作
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
////        toggle.setDrawerIndicatorEnabled(false);
//        toggle.syncState();


//        radioGroupBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Fragment currentFragment = null;
//                switch (checkedId) {
//                    case R.id.main_bottom_radiogroup_r1:
//                        currentFragment = fragment1News;
//                        break;
//                    case R.id.main_bottom_radiogroup_r2:
//                        currentFragment = fragment2Channel;
//                        break;
//                    case R.id.main_bottom_radiogroup_r3:
//                        currentFragment = fragment3Echo;
//                        break;
//                    case R.id.main_bottom_radiogroup_r4:
//                        currentFragment = fragment4Celebrity;
//                        break;
//                    default:
//                        return;
//                }
//                displayFragment(currentFragment);
//            }
//        });
//
//
//        /**
//         * 监听侧滑栏的item 点击
//         */
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                // Handle navigation view item clicks here.
//                int id = item.getItemId();
//
//                if (id == R.id.nav_camera) {
//                    // Handle the camera action
//                } else if (id == R.id.nav_gallery) {
//
//                } else if (id == R.id.nav_slideshow) {
//
//                } else if (id == R.id.nav_manage) {
//
//                } else if (id == R.id.nav_share) {
//
//                } else if (id == R.id.nav_send) {
//
//                }
//
//                //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
//}


    @Override
    protected void initViewData() {
        RadioButton ra1 = (RadioButton) $(R.id.main_top_radiogroup_r1);
        RadioButton ra2 = (RadioButton) $(R.id.main_top_radiogroup_r2);
        Drawable drawable1 = getResources().getDrawable(R.drawable.main_top_title_radiogroup_selector1);

        int wh = 50;
        drawable1.setBounds(0, 0, wh, wh);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        ra1.setCompoundDrawables(drawable1, null, null, null);//只放左边

        drawable1 = getResources().getDrawable(R.drawable.main_top_title_radiogroup_selector2);
        drawable1.setBounds(0, 0, wh, wh);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        ra2.setCompoundDrawables(drawable1, null, null, null);//只放左边


        setFragmentListData();
        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment3Echo);
        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment1News);
        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment2Channel);
        fragmentTransaction.add(R.id.main_fragment_viewgroup, fragment4Celebrity);
        fragmentTransaction.commit(); //commit()方法提交对事务的操作
        radioGroupBottom.check(R.id.main_bottom_radiogroup_r3);//默认点亮第三个radiobutton
        radioGroupTop.check(R.id.main_top_radiogroup_r1);//默认顶部的第一个点亮：推荐按钮
        //监听浮动按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Snackbar 为底部的弹窗效果
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        radioGroupBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment currentFragment = null;
                switch (checkedId) {
                    case R.id.main_bottom_radiogroup_r1:
                        currentFragment = fragment1News;
                        break;
                    case R.id.main_bottom_radiogroup_r2:
                        currentFragment = fragment2Channel;
                        break;
                    case R.id.main_bottom_radiogroup_r3:
                        currentFragment = fragment3Echo;
                        break;
                    case R.id.main_bottom_radiogroup_r4:
                        currentFragment = fragment4Celebrity;
                        break;
                    default:
                        return;
                }

                displayFragment(currentFragment);
            }
        });


        /**
         * 监听侧滑栏的item 点击
         */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    // Handle the camera action
                } else if (id == R.id.nav_gallery) {

                } else if (id == R.id.nav_slideshow) {

                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }

                //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//设置toolbar
        navigationView = (NavigationView) $(R.id.nav_view);
        drawer = (DrawerLayout) $(R.id.drawer_layout);
        radioGroupTop = (RadioGroup) this.$(R.id.main_top_radiogroup);
        radioGroupBottom = (RadioGroup) this.$(R.id.main_bottom_radiogroup);
        topTitle = (TextView) this.$(R.id.main_top_title);
        topTitle.setVisibility(View.GONE);//默认隐藏标题
        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }


    /**
     * 监听头像的点击 然后打开或关闭侧滑栏
     *
     * @param v
     */
    public void toggle_left_view(View v) {
        View clippedView = v;
        int margin = Math.min(clippedView.getWidth(), clippedView.getHeight()) / 10;
        Outline mClip = new Outline();
        mClip.setRoundRect(margin, margin, clippedView.getWidth() - margin,
                clippedView.getHeight() - margin, margin / 2);
/* Sets the Outline of the View. */
//        clippedView.setOutline(mClip);
/* Enables clipping on the View. */
        clippedView.setClipToOutline(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {//如果侧滑栏已打开
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 把fragment装进容器
     */
    public void setFragmentListData() {
        fragmentList = new ArrayList<Fragment>();
        fragment1News = new Fragment1News();
        fragment2Channel = new Fragment2Channel();
        fragment3Echo = new Fragment3Echo();
        fragment4Celebrity = new Fragment4Celebrity();
        fragmentList.add(fragment1News);
        fragmentList.add(fragment2Channel);
        fragmentList.add(fragment3Echo);
        fragmentList.add(fragment4Celebrity);
    }

    /**
     * 显示 隐藏fragment
     *
     * @param fragment
     */
    public void displayFragment(Fragment fragment) {//传入需要显示的Fragment
        showHideTopBar(fragment);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        Iterator<Fragment> it = fragmentList.iterator();
        while (it.hasNext()) {
            Fragment f = it.next();
            if (f != fragment) {//f != fragment
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commit();
    }


    /**
     * 显示隐藏顶部导航标题
     */
    public void showHideTopBar(Fragment fragment) {
        if (fragment instanceof Fragment3Echo) {
            radioGroupTop.setVisibility(View.VISIBLE);
            topTitle.setVisibility(View.GONE);
        } else {
            radioGroupTop.setVisibility(View.GONE);
            topTitle.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 监听顶部导航条 右边的按钮
     *
     * @param v
     */
    public void doMusic(View v) {
        Snackbar.make(v, "doMusic执行播放音乐", Snackbar.LENGTH_SHORT).show();
        // Toast.makeText(this, "doMusic执行播放音乐", Toast.LENGTH_SHORT).show();
    }
}
