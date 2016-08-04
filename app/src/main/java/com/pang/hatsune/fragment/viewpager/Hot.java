package com.pang.hatsune.fragment.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pang.hatsune.R;
import com.pang.hatsune.adapter.Fragment3EchoHotGridAdapter;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.EchoHotInfo;

/**
 * Created by Pang on 2016/7/23.
 */
public class Hot extends Fragment {
    EchoHotInfo hotDataInfo;
    RecyclerView gridRecyclerView;
    private final int DOING = 10;
//    ArrayList<ImageFragment> imageFragmentList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == DOING) {
//                getEchoHotData
                System.out.println("hhtjim:78:handler");
                System.out.println("hhtjim:78:"+hotDataInfo.getDayHotList().size());
                System.out.println("hhtjim:78:"+hotDataInfo.getWeekHotList().size());

                GridLayoutManager gridManager = new GridLayoutManager(Hot.this.getContext(), 2);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position == 0 || position == 1 || position >= hotDataInfo.getDayHotList().size() + 2) {
                            return 2;
                        }
                        return 1;
                    }
                });

                gridRecyclerView.setLayoutManager(gridManager);
                Fragment3EchoHotGridAdapter adapter = new Fragment3EchoHotGridAdapter(Hot.this.getContext(), hotDataInfo);


                TextView dayTitle = new TextView(Hot.this.getContext());
                dayTitle.setText("今日最热");
                dayTitle.setTextSize(20);
                TextView weekTitle = new TextView(Hot.this.getContext());
                weekTitle.setText("周榜");
                weekTitle.setTextSize(20);
                adapter.setDayHotTitle(dayTitle);
                adapter.setWeekHotTitle(weekTitle);
                ImageView headerImage = new ImageView(Hot.this.getContext());
                headerImage.setImageResource(R.drawable.echo_hot_top_image);


                ViewGroup.LayoutParams lp =  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300);
                headerImage.setScaleType(ImageView.ScaleType.FIT_XY);
                headerImage.setLayoutParams(lp);
                adapter.setHeaderView(headerImage);

                gridRecyclerView.setAdapter(adapter);
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
        gridRecyclerView = (RecyclerView) v.findViewById(R.id.fragment3_echo_viewpager_fragment2_hot_grid);
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

}
