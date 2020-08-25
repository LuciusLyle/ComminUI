package com.lq.comnui.anim;

/**
 * @author
 * @version 1.0
 * @date 2020/8/24
 */
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;

import com.daimajia.easing.Skill;
import com.lq.comnui.anim.customAnim.AnimatorFactory;
import com.lq.comnui.anim.customAnim.animaFactory.DropOutAnimator;

public class Glider {

    public static ValueAnimator glide(Class<? extends BaseEasingMethod> clazz, float duration, ValueAnimator animator) {
        return Glider.glide(clazz, duration, animator, (BaseEasingMethod.EasingListener[]) null);
    }

    public static ValueAnimator glide(Class<? extends BaseEasingMethod> clazz, float duration, ValueAnimator animator,BaseEasingMethod.EasingListener... listeners) {
        //BaseEasingMethod t = skill.getMethod(duration);
    
        BaseEasingMethod t = new AnimatorFactory().createAnimaEasing(clazz);
        t.setDuration(duration);
        if (listeners != null)
            t.addEasingListeners(listeners);

        animator.setEvaluator(t);
        return animator;
    }

    public static PropertyValuesHolder glide(Skill skill, float duration, PropertyValuesHolder propertyValuesHolder) {
        propertyValuesHolder.setEvaluator(skill.getMethod(duration));
        return propertyValuesHolder;
    }


}
