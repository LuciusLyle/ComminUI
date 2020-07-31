package com.lq.comnui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.lq.comn.BaseDialog;
import com.lq.comn.action.HandlerAction;
import com.lq.comnui.dialog.AddressDialog;
import com.lq.comnui.dialog.DateDialog;
import com.lq.comnui.dialog.InputDialog;
import com.lq.comnui.dialog.MenuDialog;
import com.lq.comnui.dialog.MessageDialog;
import com.lq.comnui.dialog.PayPasswordDialog;
import com.lq.comnui.dialog.SelectDialog;
import com.lq.comnui.dialog.TimeDialog;
import com.lq.comnui.dialog.ToastDialog;
import com.lq.comnui.dialog.WaitDialog;
import com.lq.comnui.navigation.WeiboActivity;
import com.lq.comnui.popup.MenuPopup;
import com.lq.comnui.util.BarUtil;
import com.lq.comnui.util.ComnToast;
import com.lq.comnui.widget.PointView;
import com.lq.widget.view.CountdownView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {

    AppCompatButton btn_dialog; //弹框
    AppCompatButton btn_app; //App信息
    AppCompatButton btn_device; //设备信息
    AppCompatButton btn_screen; //屏幕
    PointView pointText; //红点
    boolean isNumber = false;

    CountdownView cv_test_countdown; //发送验证码
    AppCompatButton btn_bar; //状态栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        BarUtils.getStatusBarHeight();
    }

    private void initView(){
        btn_dialog = findViewById(R.id.btn_dialog);
        cv_test_countdown = findViewById(R.id.cv_test_countdown);
        btn_bar = findViewById(R.id.btn_bar);
        btn_app = findViewById(R.id.btn_app);
        btn_device = findViewById(R.id.btn_device);
        btn_screen = findViewById(R.id.btn_screen);
        pointText = findViewById(R.id.pointText);

        btn_dialog.setOnClickListener(this);
        cv_test_countdown.setOnClickListener(this);
        btn_bar.setOnClickListener(this);
        btn_app.setOnClickListener(this);
        btn_device.setOnClickListener(this);
        btn_screen.setOnClickListener(this);

        pointText.setOnClickListener(v -> {
            startActivity(new Intent(this, WeiboActivity.class));
//            if (isNumber){
//                pointText.setRedNumber(1 + "");
//                isNumber = false;
//            }else {
//                pointText.setRedNumber(10086 + "");
//                isNumber = true;
//            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_bar: //状态栏
                startActivity(new Intent(this,BarActivity.class));
                break;
            case R.id.btn_dialog: //弹框
                startActivity(new Intent(this,DialogActivity.class));
                break;
            case R.id.btn_app: //App
                startActivity(new Intent(this,AppActivity.class));
                break;
            case R.id.btn_device: // 设备信息
                startActivity(new Intent(this,DeviceActivity.class));
                break;
            case R.id.btn_screen: //屏幕
                startActivity(new Intent(this,ScreenActivity.class));
                break;
            case R.id.cv_test_countdown: // 发送验证码
                cv_test_countdown.start();
                break;
        }
    }
}