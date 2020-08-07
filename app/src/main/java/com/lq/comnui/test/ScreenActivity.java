package com.lq.comnui.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.lq.comn.action.HandlerAction;
import com.lq.comnui.R;
import com.lq.comnui.util.ComnToast;
import com.lq.comnui.util.ScreenUtil;

public class ScreenActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {

    AppCompatButton btn_ScreenWidth; //获取屏幕的宽度（单位：px）
    AppCompatButton btn_ScreenHeight; //获取屏幕的高度（单位：px）
    AppCompatButton btn_FullScreen; //切换屏幕为全屏与否状态
    AppCompatButton btn_Landscape; //设置屏幕为横屏
    AppCompatButton btn_Portrait; //设置屏幕为竖屏
    AppCompatButton btn_Tablet; //判断是否是平板

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        initView();
    }

    private void initView(){
        btn_ScreenWidth = findViewById(R.id.btn_ScreenWidth);
        btn_ScreenHeight = findViewById(R.id.btn_ScreenHeight);
        btn_FullScreen = findViewById(R.id.btn_FullScreen);
        btn_Landscape = findViewById(R.id.btn_Landscape);
        btn_Portrait = findViewById(R.id.btn_Portrait);
        btn_Tablet = findViewById(R.id.btn_Tablet);

        btn_ScreenWidth.setOnClickListener(this);
        btn_ScreenHeight.setOnClickListener(this);
        btn_FullScreen.setOnClickListener(this);
        btn_Landscape.setOnClickListener(this);
        btn_Portrait.setOnClickListener(this);
        btn_Tablet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_ScreenWidth: //获取屏幕的宽度（单位：px）
                ComnToast.showMsg(ScreenUtil.getScreenWidth() + "");
                break;
            case R.id.btn_ScreenHeight: //获取屏幕的高度（单位：px）
                ComnToast.showMsg(ScreenUtil.getScreenHeight() + "");
                break;
            case R.id.btn_FullScreen: //切换屏幕为全屏与否状态
                if (ScreenUtil.isFullScreen(this)){
                    ScreenUtil.setNonFullScreen(this);
                }else {
                    ScreenUtil.setFullScreen(this);
                }
                break;
            case R.id.btn_Landscape: //设置屏幕为横屏
                if (ScreenUtil.isLandscape()){
                    ComnToast.showMsg("当前已是横屏");
                }else {
                    ScreenUtil.setLandscape(this);
                }
                break;
            case R.id.btn_Portrait: //设置屏幕为竖屏
                if (ScreenUtil.isPortrait()){
                    ComnToast.showMsg("当前已是竖屏");
                }else {
                    ScreenUtil.setPortrait(this);
                }
                break;
            case R.id.btn_Tablet: //判断是否是平板
                if (ScreenUtil.isTablet()){
                    ComnToast.showMsg("当前设备为平板");
                }else {
                    ComnToast.showMsg("当前设备不为平板");
                }
                break;
        }
    }
}