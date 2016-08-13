package com.pang.hatsune.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pang.hatsune.R;
import com.pang.hatsune.activity.SearchActivity;
import com.pang.hatsune.adapter.Fragment4CelebrityListAdapter;
import com.pang.hatsune.custom_view.FullyGridLayoutManager;
import com.pang.hatsune.custom_view.FullyLinearLayoutManager;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dehtml.DeHtml;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.gsonfactory.Fragment4CelebrityStartinfo;

import java.util.ArrayList;

/**
 * 名人
 * A simple {@link Fragment} subclass.
 */
public class Fragment4Celebrity extends BaseFragment {
    RecyclerView recyclerView;
    ArrayList<Fragment4CelebrityStartinfo> StartsList;
    ArrayList<Fragment4CelebrityStartinfo> MvsList;
    ArrayList<Fragment4CelebrityStartinfo> RecommendStartsList;
    private final int GO = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what == GO) {
                recyclerView.setLayoutManager(new LinearLayoutManager(Fragment4Celebrity.this.getContext()));
                Fragment4CelebrityListAdapter adapter = new Fragment4CelebrityListAdapter(Fragment4Celebrity.this.getContext(), RecommendStartsList);
                ImageView headerImage = new ImageView(Fragment4Celebrity.this.getContext());
                headerImage.setImageResource(R.drawable.start_banner);
                headerImage.setScaleType(ImageView.ScaleType.FIT_XY);
                adapter.setmHeaderView(headerImage);
                adapter.setmHorizontalListview(getHorizontalListview());
                adapter.setmMvsGridListView(getMvsGridListView());
                TextView title = new TextView(Fragment4Celebrity.this.getContext());
                title.setText("初音推荐");
                LinearLayout linearLayout = new LinearLayout(Fragment4Celebrity.this.getContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(title);
                title.setTextSize(20);
                 LinearLayout.LayoutParams lp =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//                LayoutParams lp = title.getLayoutParams();
                lp.setMargins(10, 10, 10, 10);
                linearLayout.setLayoutParams(lp);
                adapter.setmNormalRecommendTitleView(linearLayout);
                recyclerView.setAdapter(adapter);
            }
        }
    };

    public Fragment4Celebrity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment4_celebrity, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment3_celebrity_list);
        new Thread() {
            @Override
            public void run() {
//        super.run();
                String htmlString = HttpResquestPang.getInstance().get(DATA.DOMAIN, HttpResquestPang.getInstance().getPCHeaders());
                StartsList = DeHtml.getInstance().getCelebrityStarts(htmlString);
                MvsList = DeHtml.getInstance().getCelebrityMvs(htmlString);
                htmlString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_CELEBRITY_RECOMMEND_START_DATA, HttpResquestPang.getInstance().getPCHeaders());
                RecommendStartsList = DeHtml.getInstance().getCelebrityRecommendStarts(htmlString);
                handler.sendEmptyMessage(GO);
            }
        }.start();

        return rootView;
    }

    /**
     * 设置初音群星 横向recycleView 数据
     *
     * @return
     */
    public View getHorizontalListview() {
        View v = LayoutInflater.from(Fragment4Celebrity.this.getContext()).inflate(R.layout.fragment4_celebrity_recommend_child_horizontallistview_layout, null);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.fragment4_celebrity_recommend_child_horizontallistview_layout_recycleview);
        recyclerView.setBackgroundColor(0xffffffff);
//        RecyclerView recyclerView = new RecyclerView(this.getContext());
//        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new VH(LayoutInflater.from(Fragment4Celebrity.this.getContext()).inflate(R.layout.fragment4_celebrity_starts_horizontal_list_item, null));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((VH) holder).image.setImageURI(Uri.parse(StartsList.get(position).getPic()));
                ((VH) holder).name.setText(StartsList.get(position).getName());
                ((VH) holder).desc.setText(StartsList.get(position).getDescOrChannel());
            }

            @Override
            public int getItemCount() {
                return StartsList.size();
            }

            class VH extends RecyclerView.ViewHolder {
                SimpleDraweeView image;
                TextView name;
                TextView desc;

                public VH(View itemView) {
                    super(itemView);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Fragment4Celebrity.this.getContext(), SearchActivity.class);
                            intent.putExtra(SearchActivity.KEYWORD,StartsList.get(getAdapterPosition()).getName());
                            Fragment4Celebrity.this.getContext().startActivity(intent);
                        }
                    });
                    image = (SimpleDraweeView) itemView.findViewById(R.id.fragment4_celebrity_starts_horizontal_list_item_image);
                    name = (TextView) itemView.findViewById(R.id.fragment4_celebrity_starts_horizontal_list_item_name);
                    desc = (TextView) itemView.findViewById(R.id.fragment4_celebrity_starts_horizontal_list_item_desc);
                }
            }
        });
        return v;
    }

    /**
     * 设置Mvs 初音视频的数据 recycleView  GridList 的数据
     *
     * @return
     */
    public View getMvsGridListView() {
        LinearLayout v = (LinearLayout) LayoutInflater.from(Fragment4Celebrity.this.getContext()).inflate(R.layout.fragment4_celebrity_recommend_child_mvs_gridlist_layout, null);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.fragment4_celebrity_recommend_child_mvs_gridlist_layout_recycleview);
        recyclerView.setBackgroundColor(0xffffffff);
        recyclerView.setLayoutManager(new FullyGridLayoutManager(Fragment4Celebrity.this.getContext(), 2));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new VH(LayoutInflater.from(Fragment4Celebrity.this.getContext()).inflate(R.layout.fragment4_celebrity_mvs_list_item, null));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((VH) holder).image.setImageURI(Uri.parse(MvsList.get(position).getPic()));
                ((VH) holder).desc.setText(MvsList.get(position).getDescOrChannel());
                ((VH) holder).name.setText(MvsList.get(position).getName());
            }

            @Override
            public int getItemCount() {
                return MvsList.size();
            }

            class VH extends RecyclerView.ViewHolder {
                SimpleDraweeView image;
                TextView name;
                TextView desc;

                public VH(View itemView) {
                    super(itemView);
                    image = (SimpleDraweeView) itemView.findViewById(R.id.fragment4_celebrity_mvs_list_item_image);
                    name = (TextView) itemView.findViewById(R.id.fragment4_celebrity_mvs_list_item_name);
                    desc = (TextView) itemView.findViewById(R.id.fragment4_celebrity_mvs_list_item_desc);

                }
            }
        });

        return v;
    }
}
