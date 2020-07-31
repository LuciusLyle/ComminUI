package com.lq.comnui.navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lq.comnui.R;


/**
 * Created by Jue on 2018/6/2.
 */

public class WBSecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container,false);
        return view;
    }

    //提示消息
    public void showToast(String str) {
        Log.e(getClass().getName(),"hhhhhhhh");
    }
}
