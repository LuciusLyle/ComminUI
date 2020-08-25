package com.lq.comnui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lq.comnui.R;
import com.lq.comnui.util.DensityUtil;


/**
 * 通讯字母选择
 */
public class LetterView extends View {

   
    protected String[] b = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    protected int chooseIndex = -1;//选中位置
    protected boolean showBkg = false;
    protected int bkgColor = Color.parseColor("#10000000");
    protected int textSize;
    protected int textColor = Color.parseColor("#ffffff");
    protected int textColorChoose = Color.parseColor("#3399ff");
    
    protected Paint paint;
    protected OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    
    public LetterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
        initView();
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public LetterView(Context context) {
        super(context);
        initView();
       
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LetterView);
        if (null != ta) {
            bkgColor = ta.getColor(R.styleable.LetterView_lv_background, bkgColor);
            showBkg = ta.getBoolean(R.styleable.LetterView_lv_showBackground,showBkg);
            textSize = ta.getDimensionPixelSize(R.styleable.LetterView_lv_textSize, DensityUtil.sp2px(getContext(), 9));
            textColor = ta.getColor(R.styleable.LetterView_lv_textSize, textColor);
            textColorChoose= ta.getColor(R.styleable.LetterView_lv_textColorChoose, textColorChoose);
            ta.recycle();
        }
    }

    private void initView() {
        //textSize=Util.spToPx(getContext(), 9);
        paint = new Paint();
        paint.setColor(textColor);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBkg) {
            canvas.drawColor(bkgColor);
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            if (i == chooseIndex) {
                paint.setColor(textColorChoose);
                paint.setFakeBoldText(true);
            }else {
                paint.setColor(textColor);
            }
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            //paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = chooseIndex;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c <= b.length) {
                        listener.onTouchingLetterChanged(b[c]);
                        chooseIndex = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < b.length) {
                        listener.onTouchingLetterChanged(b[c]);
                        chooseIndex = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                chooseIndex = -1;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}
