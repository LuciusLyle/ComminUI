package com.lq.comnui.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.lq.comnui.action.HandlerAction;
import com.lq.comnui.R;
import com.lq.comnui.util.ComnToast;
import com.lq.comnui.util.DeviceUtil;

public class DeviceActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {

    AppCompatButton btn_VersionName; //获取设备系统版本号
    AppCompatButton btn_VersionCode; //获取设备系统版本码
    AppCompatButton btn_AndroidID; //获取设备 AndroidID
    AppCompatButton btn_Address; //获取设备 MAC 地址
    AppCompatButton btn_Manufacturer; //获取设备厂商
    AppCompatButton btn_Model; //获取设备型号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        initView();
    }

    private void initView(){
        btn_VersionName = findViewById(R.id.btn_VersionName);
        btn_VersionCode = findViewById(R.id.btn_VersionCode);
        btn_AndroidID = findViewById(R.id.btn_AndroidID);
        btn_Address = findViewById(R.id.btn_Address);
        btn_Manufacturer = findViewById(R.id.btn_Manufacturer);
        btn_Model = findViewById(R.id.btn_Model);

        btn_VersionName.setOnClickListener(this);
        btn_VersionCode.setOnClickListener(this);
        btn_AndroidID.setOnClickListener(this);
        btn_Address.setOnClickListener(this);
        btn_Manufacturer.setOnClickListener(this);
        btn_Model.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_VersionName: //获取设备系统版本号
                ComnToast.showMsg(DeviceUtil.getSDKVersionName());
                break;
            case R.id.btn_VersionCode: //获取设备系统版本码
                ComnToast.showMsg(DeviceUtil.getSDKVersionCode() + "");
                break;
            case R.id.btn_AndroidID: //获取设备 AndroidID
                ComnToast.showMsg(DeviceUtil.getAndroidID());
                break;
            case R.id.btn_Address: //获取设备 MAC 地址
                ComnToast.showMsg(DeviceUtil.getMacAddress());
                break;
            case R.id.btn_Manufacturer: //获取设备厂商
                ComnToast.showMsg(DeviceUtil.getManufacturer());
                break;
            case R.id.btn_Model: //获取设备型号
                ComnToast.showMsg(DeviceUtil.getModel());
                break;
        }
    }
}