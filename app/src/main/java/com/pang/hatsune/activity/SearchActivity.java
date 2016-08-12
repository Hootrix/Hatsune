package com.pang.hatsune.activity;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
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
    EditText editText;
    RecyclerView recyclerView;
    SearchResultRecycleviewAdapter adapter;
    String keyword = "HEBE";

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
            editText.setText(keyword);//修改编辑框内容
            if (msg.what == LOADING) {
                adapter.notifyItemRemoved(searchList.size());
                isLoading = false;
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
            String getkey = getIntent().getStringExtra(KEYWORD);
            if (getkey == null) {//为空就进catch
                throw new NullPointerException();
            }
//            editText.setText(getkey);
            keyword = getkey;//这样操作  避免上面报空之后被赋值为空
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
                    isLoading = true;

                    /**
                     * 此处判断：若结果只有一个 那么会执行到此语句。把loading状态取消
                     */
                    if(dy==0){//Y轴位移为0  表示当前页面不能滚动：也就是搜索结果数量较少
//                        System.out.println("hhtjim:bottom:dy:isLoading====false;" );
                        isLoading = false;
                    }
                    if (!isEnd) {
//                        System.out.println("hhtjim:add(null)" );
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
                String urlEncodeBefore = "";
                if (!TextUtils.isEmpty(keyword) || !isEnd) {
                    String jsonString = "";
                    try {
                        if (statue == LOADING) {
                            lastNum++;
                        } else {
                            lastNum = 1;
                        }
//                        System.out.println("hhtjim:99:" + keyword);
//                        ;
                        keyword = URLEncoder.encode(keyword, "UTF-8");
                        urlEncodeBefore = URLDecoder.decode(keyword, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("hhtjim:keyword:" + keyword);
                    jsonString = HttpResquestPang.getInstance().get(DATA.DOMAIN_API_SEARCH_DATA + "keyword=" + keyword + "&page=" + lastNum + "&token=" + Token.getLinkSearchToken(urlEncodeBefore, lastNum + ""));

                    SearchResultInfo info = Dejson.getInstance().getSearchResult(jsonString);
                    List<SearchResultInfo.ResultBean.DataBean> tempList = null;
                    try {
                        tempList = info.getResult().getData();
                    } catch (NullPointerException e) {
                        isEnd = true;
                        isLoading = false;
                        Snackbar.make(editText,"NOT FOUND.",Snackbar.LENGTH_LONG).show();
                        handler.sendEmptyMessage(NORMAL);//万一第一次就为空  也得把recycleView的适配器装好
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
}
