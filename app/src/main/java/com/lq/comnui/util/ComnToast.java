package com.lq.comnui.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.lq.comnui.R;

public class ComnToast extends Toast{
    public static final String MSG_DEFAULT="服务器偷懒了";
    public static final String MSG_RE_LOGIN="RE_LOGIN";//重新登录的提示标识

    public static Context applicationContext;

    private String msg="";
    private static ComnToast _instance;
    private static boolean isShow = false;
    
    private int customView =0;
    private int customViewDuration=Toast.LENGTH_SHORT;
    private int customGravity = Gravity.CENTER;
    private int xOffset=0;
    private int intyOffsetl=0;

    public static void showMsg(String msg){
        if (ComnUtil.isEmpty(msg)){
           return;
        }
        getInstance().setMsg(msg).show();
    }

    public static ComnToast getInstance(){
        if (null==_instance){
            synchronized (ComnToast.class) {
                _instance = new ComnToast();
            }
        }
        return _instance;
    }

    public ComnToast(){
        super(applicationContext);
        customView= R.layout.comn_toast_default;
        setView(customView);
        _instance.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                isShow = true;
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                isShow = false;
            }
        });
        setDuration(customViewDuration);
        setGravity(customGravity,xOffset,intyOffsetl);
    }

    //需要使用新的布局可以 setView(新布局资源ID).setMsg(新布局中控件ID,显示内容)
    public ComnToast setView(@LayoutRes int layoutResID) {
        LayoutInflater inflate = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflate.inflate(layoutResID,null);

        setView(view);
        return this;
    }


    public ComnToast setMsg(String msg) {
        return setMsg(R.id.content,msg);
    }

    public ComnToast setMsg(@IdRes int resID, String msg) {
        this.msg=msg;
        if (ComnUtil.isEmpty(msg)){
            msg=MSG_DEFAULT;
        }
        View view=getView().findViewById(resID);
        if (view instanceof TextView){
            ((TextView)view).setText(msg);
        }else{
            setText(msg);
        }
        return this;
    }

    public ComnToast setIcon(@IdRes int resID, @DrawableRes int drawableId) {
        View view=getView().findViewById(resID);
        if (view instanceof ImageView){
            ((ImageView)view).setImageResource(drawableId);
        }
        return this;
    }

    @Override
    public void show() {
        if (ComnUtil.isEmpty(msg)){
            //屏蔽空弹窗
            return;
        }
        if (isShow){
            //当前Toast还没有消失
            return;
        }
        if(MSG_RE_LOGIN.equals(msg)){
            //屏蔽掉重新登录的提示
            return;
        }
        super.show();
    }
}
