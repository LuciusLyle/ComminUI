package com.lq.comnui.test;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.lq.comnui.R;
import com.lq.comnui.util.BarUtil;
import com.lq.comnui.util.ComnToast;

public class BarActivity extends AppCompatActivity implements View.OnClickListener {

    View BarHeight; //View
    AppCompatButton btn_bar_height; //获取状态栏高度（px）
    AppCompatButton btn_bar_gone; //设置状态栏是否可见
    AppCompatButton btn_bar; //设置状态栏是否为浅色模式
    AppCompatButton btn_bar_add; //增加状态栏高度
    AppCompatButton btn_bar_delete; //减少状态栏高度
    AppCompatButton btn_bar_color; //设置状态栏颜色
    AppCompatButton btn_bar_actionBar; //获取ActionBar高度
    AppCompatButton btn_bar_tips; //设置通知栏是否可见
    AppCompatButton btn_bar_navIs; //判断是否支持导航栏
    AppCompatButton btn_bar_navHeight; //获取导航栏高度
    AppCompatButton btn_bar_navLook; //设置导航栏是否可见
    AppCompatButton btn_bar_navColor; //获取导航栏颜色
    AppCompatButton btn_bar_navSetColor; //设置导航栏颜色

    private boolean isWhite = false; //状态栏颜色是否为白色
    private boolean isLook = false; //通知栏是否可见
    private boolean isColor = false; //导航栏是否设置颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        initView();
    }

    private void initView(){
        BarHeight = findViewById(R.id.BarHeight);
        btn_bar_height = findViewById(R.id.btn_bar_height);
        btn_bar_gone = findViewById(R.id.btn_bar_gone);
        btn_bar = findViewById(R.id.btn_bar);
        btn_bar_add = findViewById(R.id.btn_bar_add);
        btn_bar_delete = findViewById(R.id.btn_bar_delete);
        btn_bar_color = findViewById(R.id.btn_bar_color);
        btn_bar_actionBar = findViewById(R.id.btn_bar_actionBar);
        btn_bar_tips = findViewById(R.id.btn_bar_tips);
        btn_bar_navIs = findViewById(R.id.btn_bar_navIs);
        btn_bar_navHeight = findViewById(R.id.btn_bar_navHeight);
        btn_bar_navLook = findViewById(R.id.btn_bar_navLook);
        btn_bar_navColor = findViewById(R.id.btn_bar_navColor);
        btn_bar_navSetColor = findViewById(R.id.btn_bar_navSetColor);

        btn_bar_height.setOnClickListener(this);
        btn_bar_gone.setOnClickListener(this);
        btn_bar.setOnClickListener(this);
        btn_bar_add.setOnClickListener(this);
        btn_bar_delete.setOnClickListener(this);
        btn_bar_color.setOnClickListener(this);
        btn_bar_actionBar.setOnClickListener(this);
        btn_bar_tips.setOnClickListener(this);
        btn_bar_navIs.setOnClickListener(this);
        btn_bar_navHeight.setOnClickListener(this);
        btn_bar_navLook.setOnClickListener(this);
        btn_bar_navColor.setOnClickListener(this);
        btn_bar_navSetColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_bar_height: //获取状态栏高度
                ComnToast.showMsg(BarUtil.getStatusBarHeight() + "px");
                break;
            case R.id.btn_bar_gone: //设置状态栏是否可见
                if (BarUtil.isStatusBarVisible(this)){
                    BarUtil.setStatusBarVisibility(this,false);
                }else {
                    BarUtil.setStatusBarVisibility(this,true);
                }
                break;
            case R.id.btn_bar: //设置状态栏是否为浅色模式
                if (BarUtil.isStatusBarLightMode(this)){
                    BarUtil.setStatusBarLightMode(this,false);
                }else {
                    BarUtil.setStatusBarLightMode(this,true);
                }
                break;
            case R.id.btn_bar_add: //增加状态栏高度
                BarUtil.addMarginTopEqualStatusBarHeight(BarHeight);
                break;
            case R.id.btn_bar_delete: //减少状态栏高度
                BarUtil.subtractMarginTopEqualStatusBarHeight(BarHeight);
                break;
            case R.id.btn_bar_color: //设置状态栏颜色
                if (isWhite){
                    BarUtil.setStatusBarColor(this,getResources().getColor(R.color.application_color));
                    isWhite = false;
                }else {
                    BarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
                    isWhite = true;
                }
                break;
            case R.id.btn_bar_actionBar: //获取 ActionBar 高度
                ComnToast.showMsg(BarUtil.getActionBarHeight() + "px");
                break;
            case R.id.btn_bar_tips: //设置通知栏是否可见
                if (isLook){
                    BarUtil.setNotificationBarVisibility(false);
                    isLook = false;
                }else {
                    BarUtil.setNotificationBarVisibility(true);
                    isLook = true;
                }
                break;
            case R.id.btn_bar_navIs: //判断是否支持导航栏
                if (BarUtil.isSupportNavBar()){
                    ComnToast.showMsg("当前设备支持导航栏");
                }else {
                    ComnToast.showMsg("当前设备暂不支持导航栏");
                }
                break;
            case R.id.btn_bar_navHeight: //获取导航栏高度
                ComnToast.showMsg(BarUtil.getNavBarHeight() + "px");
                break;
            case R.id.btn_bar_navLook: //设置导航栏是否可见
                if (BarUtil.isNavBarVisible(this)){
                    BarUtil.setNavBarVisibility(this,false);
                }else {
                    BarUtil.setNavBarVisibility(this,true);
                }
                break;
            case R.id.btn_bar_navColor: //获取导航栏颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ComnToast.showMsg(BarUtil.getNavBarColor(this) + "");
                }
                break;
            case R.id.btn_bar_navSetColor: //设置导航栏颜色
                if (isColor){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        BarUtil.setNavBarColor(this,getResources().getColor(R.color.application_color));
                        isColor = false;
                    }
                }else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        BarUtil.setNavBarColor(this,getResources().getColor(R.color.white));
                        isColor = true;
                    }
                }
                break;
        }
    }
}