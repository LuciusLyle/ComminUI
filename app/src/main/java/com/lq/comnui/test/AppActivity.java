package com.lq.comnui.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.lq.comnui.action.HandlerAction;
import com.lq.comnui.R;

public class AppActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {

    AppCompatButton btn_PackageName; //获取App包名
    AppCompatButton btn_Name; //获取App名称
    AppCompatButton btn_Path; //获取App路径
    AppCompatButton btn_VersionName; //获取App版本号
    AppCompatButton btn_VersionCode; //获取App版本码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        initView();
    }

    private void initView(){
        btn_PackageName = findViewById(R.id.btn_PackageName);
        btn_Name = findViewById(R.id.btn_Name);
        btn_Path = findViewById(R.id.btn_Path);
        btn_VersionName = findViewById(R.id.btn_VersionName);
        btn_VersionCode = findViewById(R.id.btn_VersionCode);

        btn_PackageName.setOnClickListener(this);
        btn_Name.setOnClickListener(this);
        btn_Path.setOnClickListener(this);
        btn_VersionName.setOnClickListener(this);
        btn_VersionCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_PackageName) { //获取App包名
            //ComnToast.showMsg(AppUtil.getAppPackageName());
        } else if (id == R.id.btn_Name) { //获取App名称
            //ComnToast.showMsg(AppUtil.getAppName());
        } else if (id == R.id.btn_Path) { //获取App路径
            //ComnToast.showMsg(AppUtil.getAppPath());
        } else if (id == R.id.btn_VersionName) { //获取App版本号
            //ComnToast.showMsg(AppUtil.getAppVersionName());
        } else if (id == R.id.btn_VersionCode) { //获取App版本码
            //ComnToast.showMsg(AppUtil.getAppVersionCode() + "");
        }
    }
}