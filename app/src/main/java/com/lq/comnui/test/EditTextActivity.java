package com.lq.comnui.test;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.lq.comnui.R;
//import com.lq.comnui.listener.SoftKeyBoardListener;
import com.lq.comnui.util.ComnToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

/**
 * @author
 * @version 1.0
 * @date 2020/8/6
 */
public class EditTextActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton show; //显示软键盘
    private AppCompatButton hide; //隐藏软键盘
    private boolean isShow = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        initView();
    }

    private void initView(){
        show = findViewById(R.id.show);
        hide = findViewById(R.id.hide);

        show.setOnClickListener(this);
        hide.setOnClickListener(this);

        getKeyStatus();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.show) { //显示软键盘
            if (isShow) {
                hideKeyboard(show);
            } else {
                showKeyboard(show);
            }
            //                showKeyboard(show);
        } else if (id == R.id.hide) { //隐藏软键盘
            //                hideKeyboard(hide);
        }
    }


    //显示软键盘
    public static void showKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            view.requestFocus();
            imm.showSoftInput(view,0);
        }
    }

    //隐藏软键盘
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void getKeyStatus(){
//        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
//            @Override
//            public void keyBoardShow(int height) {
//                ComnToast.showMsg("显示了");
//                isShow = true;
//            }
//
//            @Override
//            public void keyBoardHide(int height) {
//                ComnToast.showMsg("隐藏了");
//                isShow = false;
//            }
//        });
    }

}
