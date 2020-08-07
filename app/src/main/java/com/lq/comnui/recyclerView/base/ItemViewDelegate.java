package com.lq.comnui.recyclerView.base;

/**
 *
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    /**
     * 判断 GridLayoutManage 与 StaggeredGridLayoutManager 是否占满一行 
     * @return 默认不撑满一行
     * 
     */
    default boolean isWidthFull() {
        return false;
    }

    void convert(ViewHolder holder, T t, int position);
}
