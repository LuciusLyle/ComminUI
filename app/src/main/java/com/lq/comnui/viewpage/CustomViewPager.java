package com.lq.comnui.viewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by Jue on 2018/6/8.
 */

public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否可滑动
     * @param isCanScroll
     */
    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return isCanScroll && super.onInterceptTouchEvent(ev);
        return isCanScroll?super.onInterceptTouchEvent(ev):false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return isCanScroll && super.onTouchEvent(ev);
        return isCanScroll?super.onTouchEvent(ev):true;
    }
}
