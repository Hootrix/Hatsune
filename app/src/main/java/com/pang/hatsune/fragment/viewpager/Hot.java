package com.pang.hatsune.fragment.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment3EchoHotGridAdapter;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.fragment.BaseFragment;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.EchoHotInfo;

import java.util.List;

/**
 * Created by Pang on 2016/7/23.
 */
public class Hot extends BaseFragment {
    Toolbar topBar;
    EchoHotInfo hotDataInfo;
    RecyclerView gridRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private final int DOING = 10;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == DOING) {

                GridLayoutManager gridManager = new GridLayoutManager(Hot.this.getContext(), 2);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
//                        if (position == 0 || position == 1 || position >= hotDataInfo.getDayHotList().size() + 2) {
//                            return 2;
//                        }
//                        return 1;
                        return 2;
                    }
                });

                gridRecyclerView.setLayoutManager(gridManager);
                Fragment3EchoHotGridAdapter adapter = new Fragment3EchoHotGridAdapter(Hot.this.getContext(), hotDataInfo);


                TextView dayTitle = new TextView(Hot.this.getContext());
                dayTitle.setText("今日最热");
                dayTitle.setTextSize(20);
                LinearLayout dayTitleLayout = new LinearLayout(Hot.this.getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lp.setMargins(20, 10, 0, 10);
                dayTitleLayout.setLayoutParams(lp);
                dayTitleLayout.addView(dayTitle);

                TextView weekTitle = new TextView(Hot.this.getContext());
                weekTitle.setText("周榜");
                weekTitle.setTextSize(20);
                LinearLayout weekTitleLayout = new LinearLayout(Hot.this.getContext());
                weekTitleLayout.setLayoutParams(lp);
                weekTitleLayout.addView(weekTitle);

                adapter.setDayHotTitle(dayTitleLayout);
                adapter.setWeekHotTitle(weekTitleLayout);
                ImageView headerImage = new ImageView(Hot.this.getContext());
                headerImage.setImageResource(R.drawable.echo_hot_top_image);

                //拉伸图片
                ViewGroup.LayoutParams lp2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                headerImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                headerImage.setLayoutParams(lp2);
                adapter.setHeaderView(headerImage);

                gridRecyclerView.setAdapter(adapter);
                gridRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    private int y = 0;

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
//                        System.out.println("hhtjim7:"+newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
//                        System.out.println("hhtjim8:"+dx);
//                        System.out.println("hhtjim8:"+dy);
                        y += dy;
//                        if(dy>0&&y<350){//触点上滑
//                            float fl = (float) (y / (350f / 100f) / 100f);
//                            System.out.println("hhtjim:9:"+fl);
//                            toolbar.setAlpha(fl);
//                        }

                        float heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
                        float scrollY = y;//该值 大于0
                        float alpha = 1 - scrollY / (heightPixels / 3);//
                        int al = (int) (alpha * 255);
                        if (al < 0) {
                            al = 0;
                        }
                        getBaseActivity().setTopBaralpha(al);
                        topBar.getBackground().setAlpha(al);//0~255

                    }
                });


                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3_echo_viewpager_fragment2_hot, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.fragment3_echo_viewpager_fragment2_hot_refreshlayout);
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);


        gridRecyclerView = (RecyclerView) v.findViewById(R.id.fragment3_echo_viewpager_fragment2_hot_grid);
        topBar = (Toolbar) this.getActivity().findViewById(R.id.toolbar);
        thread();
        return v;
    }

    /**
     * 网络请求
     */
    private void thread() {
        new Thread() {
            @Override
            public void run() {
//               super.run();
                String htmlString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_ECHO_HOT_DATA, HttpResquestPang.getInstance().getPCHeaders());
                hotDataInfo = DeHtml.getInstance().getEchoHotData(htmlString);
//                   System.out.println("hhtjim88:send");
                handler.sendEmptyMessage(DOING);
            }
        }.start();
    }


    /**
     * 判断当前fragment是否被用户看到
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
////        System.out.println("hhtjim:88:isVisibleToUser"+isVisibleToUser);
////        System.out.println("hhtjim:88:getUserVisibleHint"+getUserVisibleHint());
        try {
            if (isVisibleToUser) {
                getBaseActivity().setTopBarBGShow(true);
//                topBar.getBackground().setAlpha(1);//0~255
                topBar.setBackgroundResource(R.drawable.top_hot_color_gradient);
                if (this.getBaseActivity().getTopBaralpha() != -1) {
                    topBar.getBackground().setAlpha(this.getBaseActivity().getTopBaralpha());//0~255
                }
            } else {
                getBaseActivity().setTopBarBGShow(false);
//                topBar.setBackgroundColor(0xffffffff);
                topBar.getBackground().setAlpha(0);//TMD!!!
            }
        } catch (NullPointerException e) {
        }

    }
}
