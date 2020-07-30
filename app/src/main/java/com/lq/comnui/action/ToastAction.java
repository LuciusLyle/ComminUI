package com.lq.comnui.action;

import androidx.annotation.StringRes;

import com.lq.comnui.util.ComnToast;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/08
 *    desc   : 吐司意图
 */
public interface ToastAction {

    default void toast(CharSequence text) {
//        ToastUtils.show(text);
        ComnToast.showMsg(text.toString());
    }

    default void toast(@StringRes int id) {
//        ToastUtils.show(id);
    }

    default void toast(Object object) {
//        ToastUtils.show(object);
    }
}