package com.lq.comnui.test;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lq.comnui.recyclerView.base.ViewHolder;
import com.lq.comnui.recyclerView.utils.WrapperUtils;

import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            ViewHolder holder =
                    ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
         
            return holder;
        } else if (mFootViews.get(viewType) != null) {
            ViewHolder holder =
                    ViewHolder.createViewHolder(parent.getContext(), mFootViews.get(viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("xxx","绑定数据:"+position);
        if (isHeaderViewPos(position)) {
            ((TextView)holder.itemView).setText(((TextView)holder.itemView).getTag()+"下标:"+ position);
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView,
                new WrapperUtils.SpanSizeCallback() {
                    @Override
                    public int getSpanSize(GridLayoutManager layoutManager,
                                           GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                        int viewType = getItemViewType(position);
                        if (mHeaderViews.get(viewType) != null) {
                            return layoutManager.getSpanCount();
                        } else if (mFootViews.get(viewType) != null) {
                            return layoutManager.getSpanCount();
                        }
                        if (oldLookup != null) return oldLookup.getSpanSize(position);
                        return 1;
                    }
                });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            WrapperUtils.setFullSpan(holder);
        }
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        Log.e("xxx","加入的key: "+(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER));
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }
    public void removeHeaderView(int headsPostion){
        if (headsPostion>-1 /*&& mHeaderViews.size()>headsPostion*/){
           
            Log.e("xxx","删除前："+mHeaderViews.size() );
            Log.e("xxx","删除的key: "+(headsPostion + BASE_ITEM_TYPE_HEADER) );
            //mHeaderViews.removeAt(headsPostion+BASE_ITEM_TYPE_HEADER);
            mHeaderViews.remove(headsPostion+BASE_ITEM_TYPE_HEADER);
            Log.e("xxx","删除后："+mHeaderViews.size() );
            //mInnerAdapter.notifyItemRemoved(headsPostion);
        }
    }
    public void removeFootView(int footsPostion){
        if (footsPostion>-1 && mFootViews.size()>footsPostion){
            mFootViews.remove(footsPostion + BASE_ITEM_TYPE_FOOTER);
            mInnerAdapter.notifyItemRemoved(footsPostion);
        }
    }
    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }
}
