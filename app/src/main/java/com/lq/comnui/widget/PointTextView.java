package com.lq.comnui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.lq.comnui.R;
import com.lq.comnui.util.ComnUtil;

import java.util.regex.Pattern;

public final class PointTextView extends FrameLayout {

    private final FrameLayout mFrameLayout;
    private final TextView mPointText; //文本文字
    private final TextView mRedText; //红点带文字
    private final TextView mRedPoint; //红点不带文字

    public PointTextView(Context context) {
        this(context, null);
    }

    public PointTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mFrameLayout = new FrameLayout(getContext());
        mPointText = new TextView(getContext());
        mRedText = new TextView(getContext());
        mRedPoint = new TextView(getContext());

        mPointText.setGravity(Gravity.CENTER);
        mRedText.setGravity(Gravity.CENTER);
//        mPointText.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()), mPointText.getLineSpacingMultiplier());

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textParams.gravity = Gravity.CENTER;
        textParams.setMargins(5,5,5,5);
        mFrameLayout.addView(mPointText, textParams);

        mRedText.setPaddingRelative((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        mRedText.setMinWidth((int) getResources().getDimension(R.dimen.dp_18));
        mRedText.setMinHeight((int) getResources().getDimension(R.dimen.dp_18));

//        mRedPoint.setPaddingRelative((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
//        mRedText.setCompoundDrawablePadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));

        mRedPoint.setWidth((int) getResources().getDimension(R.dimen.dp_18));
        mRedPoint.setHeight((int) getResources().getDimension(R.dimen.dp_18));
        addView(mRedPoint, 0, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT));
        addView(mRedText, 0, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.RIGHT));
        addView(mFrameLayout, 0, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.PointText);

        // 文本设置
        if (array.hasValue(R.styleable.PointText_pointStr)) {
            setPointText(array.getString(R.styleable.PointText_pointStr));
        }
        setPointColor(array.getColor(R.styleable.PointText_pointColor, ContextCompat.getColor(getContext(), R.color.Text333)));
        setPointSize(TypedValue.COMPLEX_UNIT_SP, array.getDimensionPixelSize(R.styleable.PointText_pointSize, 13));
        // 红点文本
        if (array.hasValue(R.styleable.PointText_pointRedBg)) {
            setRedBg(array.getDrawable(R.styleable.PointText_pointRedBg));
        }
        // 文本与图标距离设置
        if (array.hasValue(R.styleable.PointText_pointPadding)) {
            setPadding(array.getDimensionPixelSize(R.styleable.PointText_pointPadding, 0));
        }
        // 图标设置
        if (array.hasValue(R.styleable.PointText_iconTop)) {
            setTopIcon(array.getDrawable(R.styleable.PointText_iconTop));
        }
        if (array.hasValue(R.styleable.PointText_iconBottom)) {
            setBottomIcon(array.getDrawable(R.styleable.PointText_iconBottom));
        }
        if (array.hasValue(R.styleable.PointText_iconLeft)) {
            setLeftIcon(array.getDrawable(R.styleable.PointText_iconLeft));
        }
        if (array.hasValue(R.styleable.PointText_iconRight)) {
            setRightIcon(array.getDrawable(R.styleable.PointText_iconRight));
        }
        //红点类型
        if (array.hasValue(R.styleable.PointText_pointType)) {
            int regexType = array.getInt(R.styleable.PointText_pointType, 0);
            switch (regexType) {
                case 0x01: //普通类型
                    mRedPoint.setVisibility(VISIBLE);
                    mRedText.setVisibility(GONE);
                    break;
                case 0x02: //数字类型
                    mRedPoint.setVisibility(GONE);
                    mRedText.setVisibility(VISIBLE);
                    // 红点文字
                    if (array.hasValue(R.styleable.PointText_pointRedText)) {
                        setRedText(array.getString(R.styleable.PointText_pointRedText));
                    }
                    setRedColor(array.getColor(R.styleable.PointText_pointRed, ContextCompat.getColor(getContext(), R.color.white)));
                    setRedSize(TypedValue.COMPLEX_UNIT_DIP, array.getDimensionPixelSize(R.styleable.PointText_pointRedSize, 8));
                    break;
                default:
                    break;
            }
        }
        array.recycle();
    }

    /**
     * 设置文本
     */
    public PointTextView setPointText(@StringRes int id) {
        return setPointText(getResources().getString(id));
    }
    public PointTextView setPointText(CharSequence text) {
        mPointText.setText(text);
        return this;
    }

    /**
     * 设置红点文本
     */
    public PointTextView setRedText(@StringRes int id) {
        return setRedText(getResources().getString(id));
    }
    public PointTextView setRedText(CharSequence text) {
        if (!ComnUtil.isEmpty(text)){
            int count = Integer.parseInt(text.toString());
            if (count > 99){
                mRedText.setText("99+");
            }else {
                mRedText.setText(text);
            }
        }
        return this;
    }

    /**
     * 设置文本与图标距离
     */
    public PointTextView setPadding(int size) {
        mPointText.setCompoundDrawablePadding(size);
        return this;
    }

    /**
     * 设置红点文字颜色
     */
    public PointTextView setPointColor(@ColorInt int color) {
        mPointText.setTextColor(color);
        return this;
    }

    /**
     * 设置文字大小
     */
    public PointTextView setPointSize(int unit, float size) {
        mPointText.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置红点文字颜色
     */
    public PointTextView setRedColor(@ColorInt int color) {
        mRedText.setTextColor(color);
        return this;
    }

    /**
     * 设置红点文字大小
     */
    public PointTextView setRedSize(int unit, float size) {
        mRedText.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置红点背景
     */
    public PointTextView setRedBg(@DrawableRes int id) {
        setRedBg(ContextCompat.getDrawable(getContext(), id));
        return this;
    }
    public PointTextView setRedBg(Drawable drawable) {
        mRedText.setBackground(drawable);
        mRedPoint.setBackground(drawable);
        return this;
    }

    /**
     * 设置顶部的图标
     */
    public PointTextView setTopIcon(@DrawableRes int id) {
        setTopIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }
    public PointTextView setTopIcon(Drawable drawable) {
        mPointText.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        return this;
    }

    /**
     * 设置底部的图标
     */
    public PointTextView setBottomIcon(@DrawableRes int id) {
        setBottomIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }
    public PointTextView setBottomIcon(Drawable drawable) {
        mPointText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
        return this;
    }

    /**
     * 设置左边的图标
     */
    public PointTextView setLeftIcon(@DrawableRes int id) {
        setLeftIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }
    public PointTextView setLeftIcon(Drawable drawable) {
        mPointText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return this;
    }

    /**
     * 设置右边的图标
     */
    public PointTextView setRightIcon(@DrawableRes int id) {
        setRightIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }
    public PointTextView setRightIcon(Drawable drawable) {
        mPointText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return this;
    }
}