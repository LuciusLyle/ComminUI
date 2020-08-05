package com.lq.comnui.recyclerView.itemDecoration.StickySection;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by anlia on 2018/12/27.
 * 
 */

public class SideGroupItemDecoration extends RecyclerView.ItemDecoration implements IGroupItemDecoration{

    private View groupView;
    private DecorationCallback decorationCallback;

    private List<GroupItem> groupList = new ArrayList<>();//用户设置的分组列表
    private Map<Object,GroupItem> groups = new HashMap<>();//保存startPosition与分组对象的对应关系
    private int[] groupPositions;//保存分组startPosition的数组
    private int positionIndex;//分组对应的startPosition在groupPositions中的索引

    private Context context;
    private boolean isFirst = true;
    private boolean isStickyHeader = true;//是否粘性头部
    private int groupViewHeight = 0;
    private int groupViewWidth = 0;
    private int indexCache = -1;

    public static final String KEY_RECT = "rect123456789";

    public SideGroupItemDecoration(Context context, View groupView, DecorationCallback decorationCallback) {
        this.context = context;
        this.groupView = groupView;
        this.decorationCallback = decorationCallback;
    }

    /**
     * 开关粘性头部
     * @param isStickyHeader
     */
    public void setStickyHeader(boolean isStickyHeader){
        this.isStickyHeader = isStickyHeader;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(!isLinearAndVertical(parent)){//若RecyclerView类型不是LinearLayoutManager.VERTICAL，跳出（下同）
            return;
        }

        if(isFirst){
            measureView(groupView,parent);//绘制View需要先测量View的大小及相应的位置
            decorationCallback.setGroup(groupList);//获取用户设置的分组列表
            if(groupList.size()==0){//若用户没有设置分组，跳出（下同）
                return;
            }
            groupPositions = new int[groupList.size()];
            positionIndex = 0;

            int a = 0;
            for(int i=0;i<groupList.size();i++){//保存groupItem与其startPosition的对应关系
                int p = groupList.get(i).getStartPosition();
                if(groups.get(p)==null){
                    groups.put(p,groupList.get(i));
                    groupPositions[a] = p;
                    a++;
                }
            }
            isFirst = false;
        }

        outRect.left = groupViewWidth;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(groupList.size()==0 || !isLinearAndVertical(parent)){
            return;
        }

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float left = child.getLeft();
            float top = child.getTop();

            float right = child.getRight();

            int position = parent.getChildAdapterPosition(child);
            if(groups.get(position)!=null){
                Rect rect = new Rect((int)(left - groupViewWidth),(int)top,(int)left,(int)(top + groupViewHeight));
                groups.get(position).setData(KEY_RECT,rect);//用于判断点击范围

                c.save();
                c.translate(left - groupViewWidth,top);//将画布起点移动到之前预留空间的左上角
                decorationCallback.buildGroupView(groupView,groups.get(position));//通过接口回调得知GroupView内部控件的数据
                measureView(groupView,parent);//因为内部控件设置了数据，所以需要重新测量View
                groupView.draw(c);
                c.restore();
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if(groupList.size()==0 || !isStickyHeader || !isLinearAndVertical(parent)){
            return;
        }
        int childCount = parent.getChildCount();
        Map<Object,Object> map = new HashMap<>();

        //遍历当前可见的childView，找到当前组和下一组并保存其position索引和GroupView的top位置
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            float top = child.getTop();
            int position = parent.getChildAdapterPosition(child);
            if(groups.get(position)!=null){
                positionIndex = searchGroupIndex(groupPositions,position);
                if(map.get("cur")==null){
                    map.put("cur", positionIndex);
                    map.put("curTop",top);
                }else {
                    if(map.get("next")==null){
                        map.put("next", positionIndex);
                        map.put("nextTop",top);
                    }
                }
            }
        }

        c.save();
        if(map.get("cur")!=null){//如果当前组不为空，说明RecyclerView可见部分至少有一个GroupView
            indexCache = (int)map.get("cur");
            float curTop = (float)map.get("curTop");
            if(curTop<=0){//保持当前组GroupView一直在顶部
                curTop = 0;
            }else {
                map.put("pre",(int)map.get("cur")-1);
                if(curTop < groupViewHeight){//判断与上一组的碰撞，推动当前的顶部GroupView
                    curTop = curTop - groupViewHeight;
                }else {
                    curTop = 0;
                }
                indexCache = (int)map.get("pre");
            }

            if(map.get("next")!=null){
                float nextTop = (float)map.get("nextTop");
                if(nextTop < groupViewHeight){//判断与下一组的碰撞，推动当前的顶部GroupView
                    curTop = nextTop - groupViewHeight;
                }
            }

            c.translate(0,curTop);
            if(map.get("pre")!=null){//判断顶部childView的分组归属，绘制对应的GroupView
                drawGroupView(c,parent,(int)map.get("pre"),(int)curTop);
            }else {
                drawGroupView(c,parent,(int)map.get("cur"),(int)curTop);
            }
        }else {//否则当前组为空时，通过之前缓存的索引找到上一个GroupView并绘制到顶部
            c.translate(0,0);
            drawGroupView(c,parent,indexCache,0);
        }
        c.restore();
    }

    /**
     * 绘制GroupView
     * @param canvas
     * @param parent
     * @param index
     */
    private void drawGroupView(Canvas canvas,RecyclerView parent,int index,int curTop){
        if(index<0){
            return;
        }

        Rect rect = (Rect) groups.get(groupPositions[index]).getData(KEY_RECT);
        rect.top = curTop;
        rect.bottom = curTop + groupViewHeight;
        groups.get(groupPositions[index]).setData(KEY_RECT,rect);

        decorationCallback.buildGroupView(groupView,groups.get(groupPositions[index]));
        measureView(groupView,parent);
        groupView.draw(canvas);
    }

    public interface DecorationCallback {
        /**
         * 设置分组
         * @param groupList
         */
        void setGroup(List<GroupItem> groupList);

        /**
         * 构建GroupView
         * @param groupView
         * @param groupItem
         */
        void buildGroupView(View groupView, GroupItem groupItem);
    }

    /**
     * 查询startPosition对应分组的索引
     * @param groupArrays
     * @param startPosition
     * @return
     */
    private int searchGroupIndex(int[] groupArrays, int startPosition){
        Arrays.sort(groupArrays);
        int result = Arrays.binarySearch(groupArrays,startPosition);
        return result;
    }

    @Override
    public GroupItem findGroupItemUnder(int x, int y) {
        Rect rect;
        for(GroupItem groupItem:groupList){
            rect = (Rect) groupItem.getData(KEY_RECT);
            if(rect == null){
                return null;
            }

            if(rect.contains(x,y)){
                return groupItem;
            }
        }
        return null;
    }

    @Override
    public Context getContext() {
        return context;
    }

    /**
     * 测量View的大小和位置
     * @param view
     * @param parent
     */
    private void measureView(View view,View parent){
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(), view.getLayoutParams().width);

        int childHeight;
        if(view.getLayoutParams().height > 0){
            childHeight = View.MeasureSpec.makeMeasureSpec(view.getLayoutParams().height, View.MeasureSpec.EXACTLY);
        } else {
            childHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);//未指定
        }

        view.measure(childWidth, childHeight);
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());

        groupViewHeight = view.getMeasuredHeight();
        groupViewWidth = view.getMeasuredWidth();
    }

    /**
     * 判断LayoutManager类型，目前GroupItemDecoration仅支持LinearLayoutManager.VERTICAL
     * @param parent
     * @return
     */
    private boolean isLinearAndVertical(RecyclerView parent){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return false;
        }else {
            if(((LinearLayoutManager) layoutManager).getOrientation()
                    != LinearLayoutManager.VERTICAL){
                return false;
            }
        }
        return true;
    }
}