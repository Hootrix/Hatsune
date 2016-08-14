package com.pang.hatsune.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pang.hatsune.R;
import com.pang.hatsune.adapter.SearchResultRecycleviewAdapter;
import com.pang.hatsune.data.DATA;
import com.pang.hatsune.dejson.Dejson;
import com.pang.hatsune.http.HttpResquestPang;
import com.pang.hatsune.info.gsonfactory.SearchResltTipInfo;
import com.pang.hatsune.info.gsonfactory.SearchResultInfo;
import com.pang.hatsune.token.Token;
import com.pang.hatsune.utils.StringFilter;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果页
 * Created by Pang on 2016/8/7.
 */
public class SearchActivity extends BaseActivity {
    private EditText editText;
    private RecyclerView recyclerView;
    private SearchResultRecycleviewAdapter adapter;
    private String keyword = "HEBE";
    private SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<SearchResultInfo.ResultBean.DataBean> searchList;

    public static final String KEYWORD = "kw";

    private int lastNum = 0;//上次打开的数据的页数
    private final int LOADING = 10;
    private final int NORMAL = 11;
    private final int REMOVE_LOADING = 12;
    private boolean isLoading;
    private boolean isEnd;
    private boolean isFirst = true;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            isFirst = false;
            editText.setText(keyword);//修改编辑框内容

            if (msg.what == REMOVE_LOADING) {
                searchList.remove(searchList.size() - 1);
                adapter.notifyItemRemoved(searchList.size());
                return;
            }

            if (msg.what == LOADING) {
                adapter.notifyItemRemoved(searchList.size());
                isLoading = false;

                swipeRefreshLayout.setRefreshing(false);
                return;
            }

            if (msg.what == NORMAL) {
                adapter = new SearchResultRecycleviewAdapter(searchList, SearchActivity.this);
                TextView empty = new TextView(SearchActivity.this);
                empty.setGravity(Gravity.CENTER);
                empty.setText("-EOF-");
                empty.setTextSize(60);
                empty.setTypeface(null, Typeface.BOLD);
                adapter.setEmptyView(empty);
                recyclerView.setAdapter(adapter);
                isLoading = false;

                swipeRefreshLayout.setRefreshing(false);
                return;
            }

        }
    };

    @Override
    protected void initView() {
        editText = (EditText) findViewById(R.id.search_result_edittext);
        recyclerView = (RecyclerView) findViewById(R.id.search_result_list);
        swipeRefreshLayout = $(R.id.search_result_refreshlayout);
    }

    @Override
    protected void initViewData() {
        swipeRefreshLayout.setEnabled(false);

        searchList = new ArrayList<SearchResultInfo.ResultBean.DataBean>();
        try {
            String getkey = getIntent().getStringExtra(KEYWORD);
            if (getkey == null) {//为空就进catch
                throw new NullPointerException();
            }
//            editText.setText(getkey);
            keyword = StringFilter.getInstance().fitlerSearchKeyword(getkey);//这样操作  避免上面报空之后被赋值为空 && 过滤某些符号
            editText.setText(keyword);//修改编辑框内容
        } catch (NullPointerException e) {
        }


        swipeRefreshLayout.measure(0, 0);//手动通知系统去测量
        swipeRefreshLayout.setRefreshing(true);

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


                    /**
                     * 此处判断：若结果只有一个 那么会执行到此语句。把loading状态取消
                     */
                    if (dy == 0) {//Y轴位移为0  表示当前页面不能滚动：也就是搜索结果数量较少
//                        System.out.println("hhtjim:bottom:dy:isLoading====false;" );
                        isLoading = false;
                        return;
                    }

                    if (!isEnd) {
                        isLoading = true;//放在此处最好不过
//                        System.out.println("hhtjim:add(null)");
                        searchList.add(null);
                        adapter.notifyItemInserted(searchList.size() - 1);
                    }
                    if (dy > 0) {//触摸点向上N托
                        thread(LOADING);
                    }
                }

            }
        });
        thread();

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        if (KeyEvent.ACTION_UP == event.getAction()) {
                            doSearch(v);
                            return true;
                        }
                }
                return false;
            }
        });
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_search;
    }


    private void thread() {
        thread(NORMAL);
    }

    private void thread(final int statue) {
        if (statue != LOADING) {
            swipeRefreshLayout.setRefreshing(true);
        }

        new Thread() {
            @Override
            public void run() {
//                super.run();
                String urlEncodekeyword = "";

                if (!TextUtils.isEmpty(keyword) || !isEnd) {
                    String jsonString = "";
                    try {
                        if (statue == LOADING) {
                            lastNum++;
                        } else {
                            lastNum = 1;
                        }
                        urlEncodekeyword = URLEncoder.encode(keyword, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("hhtjim:keyword:" + keyword);
                    jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_SEARCH_DATA + "keyword=" + urlEncodekeyword + "&page=" + lastNum + "&token=" + Token.getLinkSearchToken(keyword, lastNum + ""));

                    SearchResultInfo info = Dejson.getInstance().getSearchResult(jsonString);
                    List<SearchResultInfo.ResultBean.DataBean> tempList = null;
                    try {
                        tempList = info.getResult().getData();

                        if (tempList == null || tempList.size() == 0) {
                            throw new NullPointerException();
                        }
                    } catch (NullPointerException e) {

                        // Snackbar.make(editText,"NOT FOUND.",Snackbar.LENGTH_LONG).show();
                        if (isFirst) {
                            handler.sendEmptyMessage(NORMAL);//万一第一次就为空  也得把recycleView的适配器装好
                        }
                        if (!isEnd && searchList.get(searchList.size() - 1) == null) {
                            handler.sendEmptyMessage(REMOVE_LOADING);
                        }
                        isEnd = true;
                        isLoading = false;
                        return;
                    }

                    if (statue == NORMAL) {
                        searchList.clear();
                    }
                    if (tempList.size() < 1) {
                        isEnd = true;
                    }
                    if (statue == LOADING) {
                        searchList.remove(searchList.size() - 1);
                    }

                    searchList.addAll(tempList);
                    handler.sendEmptyMessage(statue);
                }
            }
        }.start();
    }


    /**
     * 执行搜索按钮
     *
     * @param v
     */
    public void doSearch(View v) {
        keyword = editText.getText().toString();
        isEnd = false;//必须重置此标记
//        System.out.println("hhtjim:isLoading"+isLoading);
        if (!isLoading) {
            isLoading = true;
            if (TextUtils.isEmpty(keyword)) {
                Snackbar.make(v, "请输入内容", Snackbar.LENGTH_SHORT).show();
                isLoading = false;
            } else {
//        System.out.println("hhtjim:thread(NORMAL)" );
                thread();
            }
        }
    }


    /**
     * 返回键监听 是否退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
