package com.smartown.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.smartown.note.library.R;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-10-12 16:12
 * <p/>
 * 描述：
 */
public class MyTextView extends View {

    private int lineNumber = 2;
    private int textColor = Color.BLACK;
    private int textSize = 32;
    private String text = "";

    private Paint paint;

    private float textWidth;
    private float textHeight;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
        init();
    }

    private void initAttr(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
            lineNumber = typedArray.getInt(R.styleable.MyTextView_tv_lineNumber, 1);
            textColor = typedArray.getColor(R.styleable.MyTextView_tv_textColor, Color.BLACK);
            textSize = typedArray.getDimensionPixelSize(R.styleable.MyTextView_tv_textSize, 32);
            text = typedArray.getString(R.styleable.MyTextView_tv_text);
            typedArray.recycle();
        }
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        textWidth = getTextWidth(text, paint) / text.length();
        textHeight = getTextHeight(text, paint);
    }

    private float getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    private float getTextWidth(String text, Paint paint) {
        return paint.measureText(text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final float width = MeasureSpec.getSize(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        float height;
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
                height = lineNumber * textHeight;
                break;
        }
        setMeasuredDimension((int) width, (int) height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //最多显示的个数
        int lineTextNumber = (int) (getWidth() / textWidth);
        if (lineTextNumber > text.length()) {
            canvas.drawText(text, 0, getHeight() / 2, paint);
        } else {
            for (int i = 0; i < lineNumber; i++) {
                if (lineTextNumber * (i + 1) > text.length()) {
                    canvas.drawText(text.substring(i * lineTextNumber), 0, (i + 1) * textHeight, paint);
                } else {
                    if (i == lineNumber - 1) {
                        canvas.drawText(text.substring(i * lineTextNumber, (1 + i) * lineTextNumber - 3) + "...", 0, (i + 1) * textHeight, paint);
                    } else {
                        canvas.drawText(text.substring(i * lineTextNumber, (1 + i) * lineTextNumber), 0, (i + 1) * textHeight, paint);
                    }
                }
            }
        }
    }
}
