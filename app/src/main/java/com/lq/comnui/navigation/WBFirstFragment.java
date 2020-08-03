package com.lq.comnui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lq.comnui.R;
import com.lq.comnui.util.ComnToast;
import com.lq.comnui.util.ComnUtil;


/**
 * Created by Administrator on 2018/6/2.
 */

public class WBFirstFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wb_first, null);

        Button bt01 = view.findViewById(R.id.bt01);
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).getNavigationBar().setMsgPointCount(2, 109);
                    ((WeiboActivity) getActivity()).getNavigationBar().setMsgPointCount(0, 5);
//                    ((WeiboActivity) getActivity()).getNavigationBar().setMsgPointCount(3, 520);
                    ((WeiboActivity) getActivity()).getNavigationBar().setHintPoint(3, true);
                }
            }
        });


        Button bt02 = view.findViewById(R.id.bt02);
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).getNavigationBar().clearAllHintPoint();
                    ((WeiboActivity) getActivity()).getNavigationBar().clearAllMsgPoint();
                }

            }
        });

        Button bt03 = view.findViewById(R.id.bt03);
        bt03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).getNavigationBar().clearMsgPoint(0);
                }
            }
        });


        Button bt04 = view.findViewById(R.id.bt04);
        bt04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).getNavigationBar().clearHintPoint(3);

                }
            }
        });

        Button bt05 = view.findViewById(R.id.bt05);
        bt05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).getNavigationBar().selectTab(1, true);
                    ComnToast.showMsg("嘻嘻哈哈嗝");
//                    ((WBSecondFragment) (((WeiboActivity) getActivity()).getNavigationBar().getAdapter().getItem(1))).showToast("嘻嘻哈哈嗝");
                }
            }
        });

        Button bt06 = view.findViewById(R.id.bt06);
        bt06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).changeStyle();
                }
            }
        });

        Button bt07 = view.findViewById(R.id.bt07);
        bt07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof WeiboActivity) {
                    ((WeiboActivity) getActivity()).getNavigationBar().updateNavigationText(1, true, "变了");
                    ((WeiboActivity) getActivity()).getNavigationBar().updateNavigationIcon(1, true, R.mipmap.add_image);
                }
            }
        });

        return view;
    }


}
