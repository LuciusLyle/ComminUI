package com.lq.comnui.recyclerView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.lq.comnui.recyclerView.base.ItemViewDelegate;
import com.lq.comnui.recyclerView.base.ItemViewDelegateManager;
import com.lq.comnui.recyclerView.base.ViewHolder;
import com.lq.comnui.recyclerView.utils.WrapperUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener mOnItemClickListener;

    public MultiItemTypeAdapter(Context context, List<T> datas) {
        
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager())
            return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewHolder holder;
//        if (parent.getTag() == null) {
//            ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
//            int layoutId = itemViewDelegate.getItemViewLayoutId();
//            holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
//            onViewHolderCreated(holder, holder.getConvertView());
//            setListener(parent, holder, viewType);
//            parent.setTag(holder);
//        }else {
//            holder = (ViewHolder) parent.getTag();
//        }
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        onViewHolderCreated(holder, holder.getConvertView());
        setListener(parent, holder, viewType);
        
        return holder;
    }

    public void onViewHolderCreated(ViewHolder holder, View itemView) {

    }

    public void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType))
            return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return mOnItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    public List<T> getDatas() {
        return mDatas;
    }
    public void setDatas(List<T> datas) {
//        if (datas==null ||datas.size()==0){
//            mDatas.clear();
//        }else {
//            mDatas.clear();
//            mDatas.addAll(datas);
//        }
        mDatas = datas;
        notifyDataSetChanged();
    }
    public void addDatas(List<T> datas) {
        if (datas==null  || datas.size()==0)return;
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }


    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        WrapperUtils.onAttachedToRecyclerView(recyclerView.getAdapter(), recyclerView, new WrapperUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                //int viewType = getItemViewType(position);
                if (mItemViewDelegateManager.getItemViewDelegate(position).isWidthFull()) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null)
                    return oldLookup.getSpanSize(position);
                return 1;
            }
        });

        //        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        //        if (manager instanceof GridLayoutManager) {
        //            final GridLayoutManager g = (GridLayoutManager) manager;
        //            g.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        //                @Override
        //                public int getSpanSize(int position) {
        //                    return mItemViewDelegateManager.getItemViewDelegate(position).isWidthFull() ? g.getSpanCount() : 1;
        //                    // return TYPE_FOOTER == getItemViewType(position) ? g.getSpanCount() : 1;
        //                }
        //            });
        //        }

    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        //WrapperUtils.setFullSpan(holder);
        setFullSpan(holder);

    }

    private void setFullSpan(ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null) {
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                if (mItemViewDelegateManager.getItemViewDelegate(holder.getPosition()).isWidthFull()) {
                    p.setFullSpan(true);
                }
            }
        }
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
