package com.lq.comnui.anim.customAnim;

import android.view.View;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 */

public abstract  class  Factory {
    public abstract <T extends BaseAnimator> T createAnima(Class<T> clz,View view);
}