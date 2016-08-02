package com.pang.hatsune.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 *
 * 屏蔽 滑动事件  设置滚动监听..
 * Created by fc on 2015/7/16.
 *
 */

 class MyScrollView extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;


    public OnScrollChangeListener onScrollChangeListener;

    public View contentView;

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener){
        this.onScrollChangeListener = onScrollChangeListener;
    }




    public MyScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        if(getChildCount() > 0){
            contentView = getChildAt(0);
        }
    }

    public interface OnScrollChangeListener{
        void onScrollChange(MyScrollView view, int x, int y, int oldx, int oldy);
        void onScrollBottomListener();
        void onScrollTopListener();
    }
    /**
     *l当前水平滚动的开始位置
     *t当前的垂直滚动的开始位置
     *oldl上一次水平滚动的位置。
     *oldt上一次垂直滚动的位置。
     **/
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollChangeListener != null){
            onScrollChangeListener.onScrollChange(this, l, t, oldl, oldt);
        }
        if(t + getHeight() >= contentView.getHeight() && onScrollChangeListener != null){
            onScrollChangeListener.onScrollBottomListener();
        }
        if(t == 0 || t + getHeight() > contentView.getHeight() && onScrollChangeListener != null){
            onScrollChangeListener.onScrollTopListener();
        }
    }
}