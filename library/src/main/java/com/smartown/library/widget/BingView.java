package com.smartown.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-08-23 15:43
 * <p>
 * 描述：饼状图控件
 */
public class BingView extends View {

    private Paint paint;
    private ValueGetter valueGetter;

    public BingView(Context context) {
        super(context);
        init();
    }

    public BingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (valueGetter == null) {
            return;
        }
        if (valueGetter.getSize() <= 0) {
            return;
        }
        if (getWidth() > getHeight()) {
            drawStroke(canvas, getHeight() / 2 - 16);
            drawArc((getWidth() - getHeight()) / 2 + 2 + 16, 2 + 16, (getWidth() + getHeight()) / 2 - 2 - 16, getHeight() - 2 - 16, canvas);
        } else {
            drawStroke(canvas, getWidth() / 2 - 16);
            drawArc(2 + 16, (getHeight() - getWidth()) / 2 + 2 + 16, getWidth() - 2 - 16, (getHeight() + getWidth()) / 2 - 2 - 16, canvas);
        }
    }

    /**
     * 描边
     *
     * @param canvas
     * @param radius
     */
    private void drawStroke(Canvas canvas, int radius) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }

    /**
     * 画各部分扇形
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param canvas
     */
    private void drawArc(float left, float top, float right, float bottom, Canvas canvas) {
        RectF rectF = new RectF(left, top, right, bottom);
        paint.setStyle(Paint.Style.FILL);
        int size = valueGetter.getSize();
        float totalCount = getTotalCount();
        float totalAngle = 0;
        for (int i = 0; i < size; i++) {
            paint.setColor(valueGetter.getColor(i));
            float angle = valueGetter.getCount(i) / totalCount * 360;
            canvas.drawArc(rectF, totalAngle, (i == size - 1) ? (360 - totalAngle) : angle, true, paint);
            totalAngle += angle;
        }
    }

    private float getTotalCount() {
        int size = valueGetter.getSize();
        int totalCount = 0;
        for (int i = 0; i < size; i++) {
            totalCount += valueGetter.getCount(i);
        }
        return totalCount;
    }

    public void setValueGetter(ValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    public interface ValueGetter {

        int getSize();

        int getColor(int position);

        float getCount(int position);

    }

}
