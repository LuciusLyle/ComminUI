package com.lq.comnui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.lq.comnui.R;
import com.lq.comnui.util.ComnUtil;

public final class PointView extends FrameLayout {

    private final View pointView; //红点
    private final AppCompatImageView pointImg; //红点图片
    private final AppCompatTextView pointTv; //红点文本
    private final View point; //红点(单纯一个点)
    private final AppCompatTextView pointNumber; //红点(存在数量的时候)

    public PointView(Context context) {
        this(context, null);
    }

    public PointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        pointView = View.inflate(getContext(), R.layout.point_red, null);
        pointImg = pointView.findViewById(R.id.pointImg);
        pointTv = pointView.findViewById(R.id.pointTv);
        point = pointView.findViewById(R.id.point);
        pointNumber = pointView.findViewById(R.id.pointNumber);

        addView(pointView, 0, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.PointText);
        if (array != null) {
            //图标
            if (array.hasValue(R.styleable.PointText_iconTop)) {
                setPointIcon(array.getDrawable(R.styleable.PointText_iconTop));
            }
            //文本
            if (array.hasValue(R.styleable.PointText_pointStr)) {
                setPointText(array.getString(R.styleable.PointText_pointStr));
            }
            setPointColor(array.getColor(R.styleable.PointText_pointColor, ContextCompat.getColor(getContext(), R.color.Text333)));
            setPointTvSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelOffset(R.styleable.PointText_pointSize, 0));
            if (array.hasValue(R.styleable.PointText_pointType)) {
                int regexType = array.getInt(R.styleable.PointText_pointType, 0);
                switch (regexType) {
                    case 0x01: //普通类型
                        point.setVisibility(VISIBLE);
                        pointNumber.setVisibility(GONE);
                        break;
                    case 0x02: //数字类型
                        point.setVisibility(GONE);
                        pointNumber.setVisibility(VISIBLE);
                        //红点
                        if (array.hasValue(R.styleable.PointText_pointRedText)) {
                            setRedNumber(array.getString(R.styleable.PointText_pointRedText));
                        }
                        setRedSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelOffset(R.styleable.PointText_pointRedSize, 0));
                        setRedColor(array.getColor(R.styleable.PointText_pointRed, ContextCompat.getColor(getContext(), R.color.white)));
                        break;
                    default:
                        break;
                }
            }
            array.recycle();
        }
    }

    /**
     * 设置图标
     */
    public PointView setPointIcon(@DrawableRes int id) {
        setPointIcon(ContextCompat.getDrawable(getContext(), id));
        return this;
    }

    public PointView setPointIcon(Drawable drawable) {
        pointImg.setImageDrawable(drawable);
        return this;
    }

    /**
     * 文本
     */
    public PointView setPointText(@StringRes int id) {
        return setPointText(getResources().getString(id));
    }

    public PointView setPointText(CharSequence text) {
        pointTv.setText(text);
        return this;
    }

    /**
     * 设置文本颜色
     */
    public PointView setPointColor(@ColorInt int color) {
        pointTv.setTextColor(color);
        return this;
    }

    /**
     * 设置文本大小
     */
    public PointView setPointTvSize(int unit, float size) {
        pointTv.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置红点文本
     */
    public PointView setRedNumber(@StringRes int id) {
        return setRedNumber(getResources().getString(id));
    }

    public PointView setRedNumber(CharSequence text) {
        if (!ComnUtil.isEmpty(text)) {
            int count = Integer.parseInt(text.toString());
            if (count > 99) {
                pointNumber.setText("99+");
            } else {
                pointNumber.setText(text);
            }
        }
        return this;
    }

    /**
     * 设置红点文字大小
     */
    public PointView setRedSize(int unit, float size) {
        pointNumber.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置文本颜色
     */
    public PointView setRedColor(@ColorInt int color) {
        pointNumber.setTextColor(color);
        return this;
    }
}