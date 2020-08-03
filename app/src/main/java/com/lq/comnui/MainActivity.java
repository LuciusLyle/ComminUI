package com.lq.comnui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
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
import com.lq.comnui.popup.MenuPopup;
import com.lq.comnui.util.BarUtil;
import com.lq.comnui.util.ComnToast;
import com.lq.widget.view.CountdownView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {

    AppCompatButton btn_dialog; //弹框

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

        btn_dialog.setOnClickListener(this);
        cv_test_countdown.setOnClickListener(this);
        btn_bar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_bar: //状态栏
                startActivity(new Intent(this,BarActivity1.class));
                break;
            case R.id.btn_dialog:
                // dialog
                startActivity(new Intent(this,DialogActivity.class));
                break;
            case R.id.cv_test_countdown:
                // 发送验证码
                cv_test_countdown.start();
                break;
        }
    }
}