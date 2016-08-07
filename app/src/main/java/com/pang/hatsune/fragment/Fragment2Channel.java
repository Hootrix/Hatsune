package com.pang.hatsune.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment2ChannelHorizontalAdapter;
import com.pang.hatsune.custom_view.FullyLinearLayoutManager;
import com.pang.hatsune.custom_view.IndicatorView;
import com.pang.hatsune.custom_view.RecycleViewDivider;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.dejson.Dejson;
import com.pang.hatsune.fragment.channel_hotnew.HotAndNewFragment;
import com.pang.hatsune.fragment.image.ImageFragment;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.Fragment2ChannelHorizontalInfo;
import com.pang.hatsune.info.gsonfactory.SearchResltInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 频道
 * A simple {@link Fragment} subclass.
 */
public class Fragment2Channel extends BaseFragment {
    ViewPager galleryViewpager;
    ScrollView myScrollview;
    ArrayList<Fragment> imageFragmentList;
    HashMap httpResutl;
    View rootView;
    boolean isFinish;//是否完成分类文本数据加载
    private final int UPDATE_BANNER = 1;//更新banner
    private final int UPDATE_CLASS_NAME = 2;//更新分类数据  只是分类名字  没有图片
    private final int UPDATE_CLASS_IMAGE = 10;//更新分类数据  显示图片
    private final String DEFAULT_IMAGE = "http://kibey-echo.b0.upaiyun.com/poster/2014/06/06/40c1270e870ab214.jpg";//默认的图片

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    RecyclerView horizontalRecyclerView;//横向的recycleView
    Fragment2ChannelHorizontalAdapter horizontalRecyclerViewAdapter;//横向的recycleView 适配器
    ArrayList<Fragment2ChannelHorizontalInfo> hoRelist;//横向的recycleView 容器

    ArrayList<HotAndNewFragment> hotAndNewFragmentList;//装热门/最新 fragment的容器

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_BANNER) {//更新banner
                setData();
                galleryViewpager.setAdapter(new FragmentPagerAdapter(Fragment2Channel.this.getActivity().getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return imageFragmentList.get(position);
                    }

                    @Override
                    public int getCount() {
                        return imageFragmentList.size();
                    }
                });

                IndicatorView mIndicatorView = (IndicatorView) rootView.findViewById(R.id.id_indicator);
                mIndicatorView.setViewPager(galleryViewpager);//设置滑动指示器
            }

            if (msg.what == UPDATE_CLASS_NAME) {//  更新分类数据  只是分类名字  没有图片
                horizontalRecyclerViewAdapter = new Fragment2ChannelHorizontalAdapter(hoRelist, Fragment2Channel.this.getContext());
                LinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(Fragment2Channel.this.getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                linearLayoutManager.setSmoothScrollbarEnabled();
                horizontalRecyclerView.setLayoutManager(linearLayoutManager);
                horizontalRecyclerView.setAdapter(horizontalRecyclerViewAdapter);
                horizontalRecyclerView.addItemDecoration(new RecycleViewDivider(Fragment2Channel.this.getActivity(), RecycleViewDivider.VERTICAL, 20, 0xffffffff));//设置分割线
                isFinish = true;
            }


            if (msg.what >= UPDATE_CLASS_IMAGE) {//更新分类数据  显示图片
//                int updateId = msg.what - UPDATE_CLASS_IMAGE;
//                Fragment2ChannelHorizontalInfo uodateInfoItem = hoRelist.get(updateId);
//                hoRelist.remove(updateId);
//                horizontalRecyclerViewAdapter.notifyItemRemoved(updateId);
//                hoRelist.add(uodateInfoItem);
//                horizontalRecyclerViewAdapter.notifyItemInserted(updateId);
                //上面的只更新某一项  无效 so 注释

                horizontalRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    };


    public Fragment2Channel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = this.getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hotAndNewFragmentList = new ArrayList<HotAndNewFragment>();
        HotAndNewFragment hot = new HotAndNewFragment().setType(HotAndNewFragment.HOT);
        HotAndNewFragment newf = new HotAndNewFragment().setType(HotAndNewFragment.NEW);
        hotAndNewFragmentList.add(hot);
        hotAndNewFragmentList.add(newf);


        rootView = inflater.inflate(R.layout.fragment_fragment2_channel, null);
        myScrollview = (ScrollView) rootView.findViewById(R.id.myscrollview);

        galleryViewpager = (ViewPager) rootView.findViewById(R.id.image_viewpager);
        horizontalRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment2_channel_horizontal_recyclerView);


        transaction.add(R.id.fragment2_channel_hot_and_new, hot);
        transaction.add(R.id.fragment2_channel_hot_and_new,newf);
        transaction.commit();


        new Thread() {
            @Override
            public void run() {
//        super.run();

                //banner图片的de获取请求
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2729.4 Safari/537.36");
                String httpString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_CHANNEL_DATA, hashMap);
                httpResutl = DeHtml.getInstance().getChannelViewPagerImage(httpString);
                handler.sendEmptyMessage(UPDATE_BANNER);


//1.获取分类的文本数据 设置给适配器
                ArrayList<String> httpStringChannelClassList = DeHtml.getInstance().getChannelClassname(httpString);
                hoRelist = new ArrayList<Fragment2ChannelHorizontalInfo>();
                for (int i = 0; i < httpStringChannelClassList.size(); i++) {
                    Fragment2ChannelHorizontalInfo hRinfo = new Fragment2ChannelHorizontalInfo();
                    hRinfo.setName(httpStringChannelClassList.get(i));
                    hoRelist.add(hRinfo);
                }
                handler.sendEmptyMessage(UPDATE_CLASS_NAME);
            }
        }.start();

//2.更新分类的图片
        Thread thread = new Thread(new FetchImageThread());
        thread.setPriority(Thread.NORM_PRIORITY - 3);//降低线程优先级
        thread.start();



        //最新 最热  按钮监听
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.fragment2_channel_hot_and_new_radiogroup);
        radioGroup.check(R.id.fragment2_channel_hot_and_new_r1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // int position = 0;
                switch (checkedId) {
                    case R.id.fragment2_channel_hot_and_new_r1:
                        //  position = 0;
                        displayFragment(hotAndNewFragmentList.get(0));
                        break;
                    case R.id.fragment2_channel_hot_and_new_r2:
                        // position = 1;
                        displayFragment(hotAndNewFragmentList.get(1));
                        break;
                    default:
                        break;
                }

            }
        });

        return rootView;
    }


    /**
     * 设置图片数据
     */
    public void setData() {
        imageFragmentList = new ArrayList<Fragment>();
        Iterator<Map.Entry<String, String>> itor = httpResutl.entrySet().iterator();
        while (itor.hasNext()) {
            Map.Entry<String, String> t = itor.next();
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(Fragment2Channel.this.getContext());
            simpleDraweeView.setImageURI(Uri.parse(t.getValue()));
//            simpleDraweeView.setMaxWidth(600);

            imageFragmentList.add(new ImageFragment().setView(simpleDraweeView));
//            System.out.println(itor.next().getKey());
        }

    }


    /**
     * 显示隐藏 fragment
     *
     * @param fr
     */
    public void displayFragment(Fragment fr) {//传入需要显示的Fragment
        transaction = fragmentManager.beginTransaction();
        transaction.show(fr);
        Iterator<HotAndNewFragment> it = hotAndNewFragmentList.iterator();
        while (it.hasNext()) {
            Fragment f = it.next();
            if (f != fr) {
                transaction.hide(f);
            }
        }
        transaction.commit();
    }


    /**
     * 用于抓取分类图标的线程
     */
    public class FetchImageThread implements Runnable {

        @Override
        public void run() {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2729.4 Safari/537.36");
            loop:
            while (true) {
                if (isFinish) {
                    int sendNum = 0;//发送次数
                    for (int i = 0; i < hoRelist.size(); i++) {
                        Fragment2ChannelHorizontalInfo info = hoRelist.get(i);
                        String string = null;
                        try {
                            string = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_SEARCH_INPUT_BOX_DATA + URLEncoder.encode(info.getName(), "UTF-8"), hashMap);
                        } catch (UnsupportedEncodingException e) {
                            System.out.println("fragment2：url 编码错误");
                            e.printStackTrace();
                        }
                        SearchResltInfo searchResltInfo = Dejson.getInstance().getSearchResult(string);
                        String url = "";
                        try {
                            url = searchResltInfo.getResult().getData().get(0).getSound().getPic();
                        } catch (NullPointerException e) {
                            url = DEFAULT_IMAGE;//没有图片就默认显示爱因斯坦的这张
                        }
                        info.setUrl(url);
//                        System.out.println("hhtjim:1111:" + url);

                        //偶然间发现不用发handler更新适配器也可以自动更新recycleview的布局
                        //上面只是把recycleview适配器里面的数据容器添加一个属性值，也能达到更新数据，添加图片的目的
                        //因为我会手动 拉动这个横向的recycleview  导致视图被重新更新，也就会重新执行适配器的onBindViewHolder方法  重新更新数据
                        if ((i + 1) % 2 == 0 && sendNum < 3) {//取到2个图才更新适配器 且只发送3次
                            handler.sendEmptyMessage(UPDATE_CLASS_IMAGE + i);//发消息更新整个recycleview
                            sendNum++;
                        }
                    }
                    break loop;
                }
            }
        }
    }
}
