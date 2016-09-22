package com.smartown.library.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.smartown.note.library.R;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-09-22 9:29
 * <p>
 * 描述：星级评分
 */
public class RatingView extends View {

    private int height = 0;
    private int rating = 0;
    private Bitmap bitmap;

    public RatingView(Context context) {
        this(context, null);
    }

    public RatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
                height = 24;
                break;
        }
        setMeasuredDimension(6 * height, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = 0;
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                left += height / 2 * 3;
            }
            if (i < rating) {
                canvas.drawBitmap(bitmap, left, 0, null);
            } else {
                canvas.drawBitmap(bitmap, left, 0, null);
            }
        }
    }

    public void setRating(int rating) {
        this.rating = rating;
        invalidate();
    }

}
