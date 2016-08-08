package com.pang.hatsune.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.pang.hatsune.R;
import com.pang.hatsune.adapter.SearchResultRecycleviewAdapter;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dejson.Dejson;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.gsonfactory.SearchResltTipInfo;
import com.pang.hatsune.info.gsonfactory.SearchResultInfo;
import com.pang.hatsune.token.Token;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果页
 * Created by Pang on 2016/8/7.
 */
public class SearchActivity extends BaseActivity {
    EditText editText;
    RecyclerView recyclerView;
    SearchResultRecycleviewAdapter adapter;
    String keyword = "hebe";//todo

    ArrayList<SearchResultInfo.ResultBean.DataBean> searchList;

    public static final String KEYWORD = "kw";

    private int lastNum = 0;//上次打开的数据的页数
    private final int LOADING = 10;
    private final int NORMAL = 11;
    private boolean isLoading;
    private boolean isEnd;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);

            if (msg.what == LOADING) {
                adapter.notifyItemRemoved(searchList.size());
                isLoading = false;
                return;
            }

            if (msg.what == NORMAL) {
                adapter = new SearchResultRecycleviewAdapter(searchList, SearchActivity.this);
                recyclerView.setAdapter(adapter);
                return;
            }
        }
    };

    @Override
    protected void initView() {
        editText = (EditText) findViewById(R.id.search_result_edittext);
        recyclerView = (RecyclerView) findViewById(R.id.search_result_list);

    }

    @Override
    protected void initViewData() {
        searchList = new ArrayList<SearchResultInfo.ResultBean.DataBean>();
        try {
            keyword = getIntent().getStringExtra(KEYWORD);
        } catch (NullPointerException e) {
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastItemIndex = manager.findLastVisibleItemPosition();
                int firstItemIndex = manager.findFirstVisibleItemPosition();
                //滑动到最后一个并且状态不是加载中,执行加载更多，isLoading默认值false
                if (lastItemIndex >= searchList.size() - 1 && !isLoading) {//若最后一项的布局高度比较高，列表的下边界在最后一项高度以内 都会触发if
//                    System.out.println("hhtjim:999");
                    isLoading = true;
                    if (!isEnd) {
                        searchList.add(null);
                        adapter.notifyItemInserted(searchList.size() - 1);
                    }
                    if (dy > 0) {//触摸点向上托
                        thread(LOADING);
                    }
                }

            }
        });
        thread();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_search;
    }


    private void thread() {
        thread(NORMAL);
    }

    private void thread(final int statue) {
        new Thread() {
            @Override
            public void run() {
//                super.run();
                if (!TextUtils.isEmpty(keyword) || !isEnd) {
                    String jsonString = "", key = "";
                    try {
                        if (statue == LOADING) {
                            lastNum++;
                        } else {
                            lastNum = 1;
                        }

                        key = URLEncoder.encode(keyword, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_SEARCH_DATA + "keyword=" + key + "&page=" + lastNum + "&token=" + Token.getLinkSearchToken(key, lastNum + ""));

                    SearchResultInfo info = Dejson.getInstance().getSearchResult(jsonString);
                    List<SearchResultInfo.ResultBean.DataBean> tempList = info.getResult().getData();

                    if (statue == NORMAL) {
                        searchList.clear();
                    }
                    if (tempList.size() < 1) {
                        isEnd = true;
                    }
                    if(statue == LOADING){
                        searchList.remove(searchList.size() - 1);
                    }

                    searchList.addAll(tempList);
                    handler.sendEmptyMessage(statue);
                }
            }
        }.start();
    }


    public void doSearch(View v){
         keyword =  editText.getText().toString();
        thread(NORMAL);
    }
}
