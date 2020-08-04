package com.lq.comnui.widget.anim.customAnim.animaFactory;

import android.animation.ObjectAnimator;
import android.view.View;

import com.lq.comnui.widget.anim.customAnim.BaseAnimator;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 */
public class PulseAnimator extends BaseAnimator {
    
    public PulseAnimator(View targetView) {
        super(targetView);
    }

    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "scaleY", 1, 1.1f, 1),
                ObjectAnimator.ofFloat(target, "scaleX", 1, 1.1f, 1)
        );
    }
}
