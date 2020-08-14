package com.lq.comnui.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lq.comnui.R;
import com.lq.comnui.control.BetterRedPointViewControl;
import com.lq.comnui.recyclerView.CommonAdapter;
import com.lq.comnui.recyclerView.base.ViewHolder;
import com.lq.comnui.recyclerView.wrapper.HeaderAndFooterWrapper;
import com.lq.comnui.recyclerView.wrapper.LoadMoreWrapper;
import com.lq.comnui.util.ComnToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author
 * @version 1.0
 * @date 2020/8/6
 */
public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private SwipeRefreshLayout sw;
    private RecyclerView rv;
    
    private CommonAdapter mAdapter;
    private HeaderAndFooterWrapper mHeaderWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;

    private List<JSONObject> mObjects;
    private int page = 0;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<JSONObject> data = getData();
            Log.e("xxx", "加载更多" + data.size());
            if (data.size() < 20) {
                mLoadMoreWrapper.hideLoadMore();
            } else {
                mLoadMoreWrapper.showLoadMore();
            }
            mAdapter.addDatas(data);
            mLoadMoreWrapper.notifyDataSetChanged();
            mLoadMoreWrapper.setloading(false);
            return false;
        }
    });

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addhead) {
            TextView head1 = new TextView(this);
            head1.setBackgroundResource(R.color.red);
            int index = mHeaderWrapper.getHeadersCount()+mHeaderWrapper.getRemoveHeaderSize() ;
            head1.setText("自定义head" + (index));
            mHeaderWrapper.addHeaderView(head1);
            mLoadMoreWrapper.notifyDataSetChanged();
        } else if (v.getId() == R.id.addfooter) {
            TextView head1 = new TextView(this);
            head1.setBackgroundResource(R.color.red);
            int index = mHeaderWrapper.getHeadersCount()+mHeaderWrapper.getRemoveFootSize() ;
            head1.setText("自定义Foot" + (index));
            mHeaderWrapper.addFootView(head1);
            mLoadMoreWrapper.notifyDataSetChanged();
        } else if (v.getId() == R.id.deletehead) {//删除头部
            if (mHeaderWrapper.getHeadersCount() > 0) {
                int delte = mHeaderWrapper.getHeadersCount() - 1;
                if (delte > -1) {
                    mHeaderWrapper.removeHeaderView(delte);
                    mLoadMoreWrapper.notifyDataSetChanged();
                }
            }
        } else if (v.getId() == R.id.deletefooter) {
            if (mHeaderWrapper.getFootersCount() > 0) {
                int delte = mHeaderWrapper.getFootersCount() - 1;
                if (delte > -1) {
                    mHeaderWrapper.removeFootView(delte);
                    mLoadMoreWrapper.notifyDataSetChanged();
                }
            }

        } else if (v.getId() == R.id.deleteheadindex) {//删除头部
            EditText text = findViewById(R.id.edit);
            if (!TextUtils.isEmpty(text.getText().toString())) {
                int index = Integer.parseInt(text.getText().toString());
                mHeaderWrapper.removeHeaderView(index);
                mLoadMoreWrapper.notifyItemRemoved(index);
                //mHeaderWrapper.notifyItemRangeChanged(index, mLoadMoreWrapper.getItemCount() - index);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        sw = findViewById(R.id.sw);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        sw.setOnRefreshListener(this);
        mObjects = new ArrayList<>();
        initView();
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                handler.sendMessageDelayed(new Message(), 3000);
            }
        });
        rv.setAdapter(mLoadMoreWrapper);

        findViewById(R.id.addhead).setOnClickListener(this);
        findViewById(R.id.addfooter).setOnClickListener(this);
        findViewById(R.id.deletefooter).setOnClickListener(this);
        findViewById(R.id.deletehead).setOnClickListener(this);
        findViewById(R.id.deleteheadindex).setOnClickListener(this);
    }

    @Override
    public void onRefresh() {
        sw.setRefreshing(false);
        page = 0;
        List<JSONObject> data = getData();
        if ((data).size() < 20) {
            mLoadMoreWrapper.hideLoadMore();
        } else {
            mLoadMoreWrapper.showLoadMore();
        }
        mAdapter.setDatas(data);
        mLoadMoreWrapper.notifyDataSetChanged();
    }

    public List<JSONObject> getData() {
        page++;
        int size = 20;
        if (page > 2) {
            size = 10;
        }
        List<JSONObject> mObjects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            JSONObject object = new JSONObject();
            mObjects.add(object);
        }
        return mObjects;
    }


    private void initView() {
        mAdapter = new CommonAdapter<JSONObject>(this, R.layout.item_rv_layout, mObjects) {
            @Override
            protected void convert(ViewHolder holder, JSONObject object, int position) {
                TextView redpoint = holder.getView(R.id.redpoint);
                redpoint.setText(position + "");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("xxx","当前点击:"+position );
                    }
                });
                //一个是拖拽的view 一个是拖拽的view布局
                new BetterRedPointViewControl(mContext, redpoint, R.layout.comn_red_point, new BetterRedPointViewControl.DragStatusListener() {
                    /**
                     * 在范围内
                     *
                     */
                    @Override
                    public void inScope() {
                        ComnToast.showMsg("在范围内");
                    }

                    /**
                     * 在范围外
                     *
                     */
                    @Override
                    public void outScope() {
                        ComnToast.showMsg("超出范围了");
                    }
                });
            }
        };
        mHeaderWrapper = new HeaderAndFooterWrapper(mAdapter);
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderWrapper);
        mLoadMoreWrapper.setLoadMoreView(R.layout.com_default_loading);
    }


}
