package com.lq.comnui.anim.customAnim;

import android.view.View;

import com.lq.comnui.anim.BaseEasingMethod;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 */

public abstract  class  Factory {
    public abstract <T extends BaseAnimator> T createAnima(Class<T> clz);
    public abstract <M extends BaseEasingMethod> M createAnimaEasing(Class<M> clz);
}