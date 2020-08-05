package com.lq.comnui.anim.customAnim.animaFactory;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;

import com.lq.comnui.anim.customAnim.BaseAnimator;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 */
public class DropOutAnimator extends BaseAnimator {
    
    public DropOutAnimator(View targetView) {
        super(targetView);
    }

    @Override
    protected void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        getAnimatorAgent().playTogether(
                test(target)
                );
    }
    
    private ValueAnimator test(View view){
        new ArgbEvaluator();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
      
        objectAnimator.setEvaluator(new TypeEvaluator<Float>() {
            @Override
            public Float evaluate(float fraction, Float startValue, Float endValue) {
//                float t = DropOutAnimator.this.getDuration()* fraction;//2000 *(0~1)
//                float b = startValue.floatValue();//0
//                float c = endValue.floatValue() - startValue.floatValue();//1
//                float d = DropOutAnimator.this.getDuration();//2000
//                Log.e("xxx","fraction: "+fraction +"   startValue:"+startValue+"   endValue:"+endValue);
//                if (fraction<0.4){
//                    //拦截动画执行过程(0~1)的比例值
//                    return (float)0.40000000;
//                }else {
//                    return fraction;
//                }
                //return c*((t=t/d-1)*t*t*t*t + 1) + b;
                return fraction;

//                int startInt = startValue.intValue();
//                float startA = ((startInt >> 24) & 0xff) / 255.0f;
//                float startR = ((startInt >> 16) & 0xff) / 255.0f;
//                float startG = ((startInt >>  8) & 0xff) / 255.0f;
//                float startB = ( startInt        & 0xff) / 255.0f;
//
//                int endInt = endValue.intValue();
//                float endA = ((endInt >> 24) & 0xff) / 255.0f;
//                float endR = ((endInt >> 16) & 0xff) / 255.0f;
//                float endG = ((endInt >>  8) & 0xff) / 255.0f;
//                float endB = ( endInt        & 0xff) / 255.0f;
//
//                // convert from sRGB to linear
//                startR = (float) Math.pow(startR, 2.2);
//                startG = (float) Math.pow(startG, 2.2);
//                startB = (float) Math.pow(startB, 2.2);
//
//                endR = (float) Math.pow(endR, 2.2);
//                endG = (float) Math.pow(endG, 2.2);
//                endB = (float) Math.pow(endB, 2.2);
//
//                // compute the interpolated color in linear space
//                float a = startA + fraction * (endA - startA);
//                float r = startR + fraction * (endR - startR);
//                float g = startG + fraction * (endG - startG);
//                float b = startB + fraction * (endB - startB);
//
//                // convert back to sRGB in the [0..255] range
//                a = a * 255.0f;
//                r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
//                g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
//                b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;
//
//                return Float.valueOf(Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b));
            }
        });
        return objectAnimator;
    };
}
