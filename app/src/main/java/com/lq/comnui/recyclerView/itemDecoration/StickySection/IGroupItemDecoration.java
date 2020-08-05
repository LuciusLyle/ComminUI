package com.lq.comnui.recyclerView.itemDecoration.StickySection;

import android.content.Context;

/**
 * Created by anlia on 2018/12/27
 */

public interface IGroupItemDecoration {
    public Context getContext();
    /**
     * 判断当前点击位置是否处于GroupItem区域
     * @param x
     * @param y
     * @return
     */
    public GroupItem findGroupItemUnder(int x, int y);
}