package com.lq.comnui.test;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.lq.comnui.action.HandlerAction;
import com.lq.comnui.R;
import com.lq.comnui.listener.OnInputListener;
import com.lq.comnui.util.ComnToast;
import com.lq.comnui.editText.SplitEditTextView;

public class SpliteActivity extends AppCompatActivity implements View.OnClickListener, HandlerAction {

    AppCompatButton btn_Edit; //切换输入框样式
    AppCompatButton btn_Show; //切换显示模式
    SplitEditTextView splitEdit1;
    SplitEditTextView splitEdit2;
    SplitEditTextView splitEdit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splite);
        initView();
    }

    private void initView(){
        btn_Edit = findViewById(R.id.btn_Edit);
        btn_Show = findViewById(R.id.btn_Show);
        splitEdit1 = findViewById(R.id.splitEdit1);
        splitEdit2 = findViewById(R.id.splitEdit2);
        splitEdit3 = findViewById(R.id.splitEdit3);

        btn_Edit.setOnClickListener(this);
        btn_Show.setOnClickListener(this);

        splitEdit1.setOnInputListener(new OnInputListener() {
            @Override
            public void onInputFinished(String content) {
                ComnToast.showMsg(content);
            }

            @Override
            public void onInputChanged(String content) {

            }
        });
        splitEdit2.setOnInputListener(new OnInputListener() {
            @Override
            public void onInputFinished(String content) {
                ComnToast.showMsg(content);
            }

            @Override
            public void onInputChanged(String content) {

            }
        });
        splitEdit3.setOnInputListener(new OnInputListener() {
            @Override
            public void onInputFinished(String content) {
                ComnToast.showMsg(content);
            }

            @Override
            public void onInputChanged(String content) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_Edit: //切换输入框样式
                if (splitEdit1.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_SINGLE){
                    splitEdit1.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_CONNECT);
                }else if (splitEdit1.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_CONNECT){
                    splitEdit1.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_UNDERLINE);
                }else if (splitEdit1.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_UNDERLINE){
                    splitEdit1.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_SINGLE);
                }
                if (splitEdit2.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_SINGLE){
                    splitEdit2.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_CONNECT);
                }else if (splitEdit2.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_CONNECT){
                    splitEdit2.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_UNDERLINE);
                }else if (splitEdit2.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_UNDERLINE){
                    splitEdit2.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_SINGLE);
                }
                if (splitEdit3.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_SINGLE){
                    splitEdit3.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_CONNECT);
                }else if (splitEdit3.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_CONNECT){
                    splitEdit3.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_UNDERLINE);
                }else if (splitEdit3.getInputBoxStyle() == SplitEditTextView.INPUT_BOX_STYLE_UNDERLINE){
                    splitEdit3.setInputBoxStyle(SplitEditTextView.INPUT_BOX_STYLE_SINGLE);
                }
                break;
            case R.id.btn_Show: //切换显示模式
                if (splitEdit1.getContentShowMode() == SplitEditTextView.CONTENT_SHOW_MODE_PASSWORD) {
                    splitEdit1.setContentShowMode(SplitEditTextView.CONTENT_SHOW_MODE_TEXT);
                } else {
                    splitEdit1.setContentShowMode(SplitEditTextView.CONTENT_SHOW_MODE_PASSWORD);
                }
                if (splitEdit2.getContentShowMode() == SplitEditTextView.CONTENT_SHOW_MODE_PASSWORD) {
                    splitEdit2.setContentShowMode(SplitEditTextView.CONTENT_SHOW_MODE_TEXT);
                } else {
                    splitEdit2.setContentShowMode(SplitEditTextView.CONTENT_SHOW_MODE_PASSWORD);
                }
                if (splitEdit3.getContentShowMode() == SplitEditTextView.CONTENT_SHOW_MODE_PASSWORD) {
                    splitEdit3.setContentShowMode(SplitEditTextView.CONTENT_SHOW_MODE_TEXT);
                } else {
                    splitEdit3.setContentShowMode(SplitEditTextView.CONTENT_SHOW_MODE_PASSWORD);
                }
                break;
        }
    }

    public class MydA extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}