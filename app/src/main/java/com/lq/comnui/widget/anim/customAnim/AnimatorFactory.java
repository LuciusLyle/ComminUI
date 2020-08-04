package com.lq.comnui.widget.anim.customAnim;

import android.view.View;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 */
public class AnimatorFactory extends Factory {

    @Override
    public <T extends BaseAnimator> T createAnima(Class<T> clz, View view) {
        BaseAnimator animator = null;
        // return (BaseEasingMethod)easingMethod.getConstructor(float.class).newInstance(duration);
        try {
            animator = (BaseAnimator) Class.forName(clz.getName()).getConstructor(View.class).newInstance(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) animator;
    }


}
