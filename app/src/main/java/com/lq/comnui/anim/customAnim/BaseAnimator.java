package com.lq.comnui.anim.customAnim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * @author
 * @version 1.0
 * @date 2020/8/3
 * 管理Animator 
 * "alpha","scaleX","scaleY","translationX","translationY"
 */
public abstract class BaseAnimator {

    public static final long DURATION = 1000;
    private long mDuration = DURATION;
    
    private AnimatorSet mAnimatorSet = new AnimatorSet();
    
    protected abstract void prepare(View target);
    

    public  BaseAnimator(){
       
    }

    public BaseAnimator setTarget(View targetView) {
        reset(targetView);
        prepare(targetView);
        return this;
    }

    /**
     * 初始化
     * @param target
     */
    public void reset(View target) {
        target.setAlpha(1);
        target.setScaleX( 1);
        target.setScaleY( 1);
        target.setTranslationX(0);
        target.setTranslationY( 0);
        target.setRotation(0);
        target.setRotationY( 0);
        target.setRotationX( 0);
    }
    

    public void start(View targetView) {
        setTarget(targetView);
        mAnimatorSet.setDuration(mDuration);
        mAnimatorSet.start();
    }

    public BaseAnimator setDuration(long duration) {
        mDuration = duration;
        return this;
    }

    public BaseAnimator setStartDelay(long delay) {
        getAnimatorAgent().setStartDelay(delay);
        return this;
    }

    public long getStartDelay() {
        return mAnimatorSet.getStartDelay();
    }

    public BaseAnimator addAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.addListener(l);
        return this;
    }

    public void cancel() {
        mAnimatorSet.cancel();
    }

    public boolean isRunning() {
        return mAnimatorSet.isRunning();
    }

    public boolean isStarted() {
        return mAnimatorSet.isStarted();
    }

    public void removeAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.removeListener(l);
    }

    public void removeAllListener() {
        mAnimatorSet.removeAllListeners();
    }

    public BaseAnimator setInterpolator(Interpolator interpolator) {
        mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public long getDuration() {
        return mDuration;
    }

    public AnimatorSet getAnimatorAgent() {
        return mAnimatorSet;
    }
}
