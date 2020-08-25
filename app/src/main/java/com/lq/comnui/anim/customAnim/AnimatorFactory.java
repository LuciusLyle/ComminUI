package com.lq.comnui.anim.customAnim;

import android.view.View;

import com.lq.comnui.anim.BaseEasingMethod;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 */
public class AnimatorFactory extends Factory {

    @Override
    public <T extends BaseAnimator> T createAnima(Class<T> clz) {
        BaseAnimator animator = null;
        // return (BaseEasingMethod)easingMethod.getConstructor(float.class).newInstance(duration);
        try {
            animator = (BaseAnimator) Class.forName(clz.getName())/*.getConstructor(View.class)*/.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) animator;
    }



    @Override
    public <M extends BaseEasingMethod> M createAnimaEasing(Class<M> clz) {
        BaseEasingMethod baseEasingMethod = null;
        // return (BaseEasingMethod)easingMethod.getConstructor(float.class).newInstance(duration);
        try {
            baseEasingMethod = (BaseEasingMethod) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (M) baseEasingMethod;
    }
}
