package com.lq.comnui.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lq.comnui.action.HandlerAction;
import com.lq.comnui.R;
import com.lq.comnui.navigation.WeiboActivity;
import com.lq.comnui.util.ComnToast;
import com.lq.comnui.widget.PointView;
import com.lq.comnui.anim.customAnim.AnimatorFactory;
import com.lq.comnui.anim.customAnim.animaFactory.DropOutAnimator;
import com.lq.comnui.widget.CountdownView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {
    
    AppCompatButton btn_dialog; //弹框
    AppCompatButton btn_app; //App信息
    AppCompatButton btn_device; //设备信息
    AppCompatButton btn_screen; //屏幕
    AppCompatButton btn_splite; //分隔框
    PointView pointText; //红点
    boolean isNumber = false;

    CountdownView cv_test_countdown; //发送验证码
    AppCompatButton btn_bar; //状态栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //BarUtils.getStatusBarHeight();
    }

    private void initView(){
        btn_dialog = findViewById(R.id.btn_dialog);
        cv_test_countdown = findViewById(R.id.cv_test_countdown);
        btn_bar = findViewById(R.id.btn_bar);
        btn_app = findViewById(R.id.btn_app);
        btn_device = findViewById(R.id.btn_device);
        btn_screen = findViewById(R.id.btn_screen);
        pointText = findViewById(R.id.pointText);
        btn_splite = findViewById(R.id.btn_splite);

        btn_dialog.setOnClickListener(this);
        cv_test_countdown.setOnClickListener(this);
        btn_bar.setOnClickListener(this);
        btn_app.setOnClickListener(this);
        btn_device.setOnClickListener(this);
        btn_screen.setOnClickListener(this);
        btn_splite.setOnClickListener(this);
        findViewById(R.id.btn_adapter).setOnClickListener(this);
        findViewById(R.id.btn_edit).setOnClickListener(this);
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
                new AnimatorFactory().createAnima(DropOutAnimator.class).setDuration(2000).start(view);
               // startActivity(new Intent(this,BarActivity1.class));
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
//                cv_test_countdown.start();
                ComnToast.showMsg("xxxxxx");
                break;
            case R.id.btn_adapter:// 自定义adapter
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_edit:// 输入框
                startActivity(new Intent(this,EditTextActivity.class));
                break;
            case R.id.btn_splite:// 分隔框
                startActivity(new Intent(this, SpliteActivity.class));
                break;
            
        }
    }
}