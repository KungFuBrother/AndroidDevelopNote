package com.smartown.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smartown.note.library.R;

/**
 * Created by Smartown on 2016-09-25.
 */
public class InstallmentView extends View {

    /**
     * 期数
     */
    private int partCount = 3;
    /**
     * 未选中状态点大小
     */
    private float partWidth = 32;
    private int color = Color.GRAY;
    /**
     * 选中状态点大小
     */
    private float selectedPartWidth = 48;
    private int selectedColor = Color.RED;
    /**
     * 线条高度
     */
    private float lineHeight = 4;
    private float textSize = 24;

    private float pointWidth;
    private float lineWidth;
    private float intervalLineWidth;

    private Paint paint;
    private Paint textPaint;
    private float textHeight;

    private int selection = -1;
    private OnSelectPartListener onSelectPartListener;

    public InstallmentView(Context context) {
        super(context);
        init(null);
    }

    public InstallmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InstallmentView);
            partCount = typedArray.getInt(R.styleable.InstallmentView_partCount, 3);
            partWidth = typedArray.getDimensionPixelSize(R.styleable.InstallmentView_partWidth, 32);
            selectedPartWidth = typedArray.getDimensionPixelSize(R.styleable.InstallmentView_selectedPartWidth, 48);
            color = typedArray.getColor(R.styleable.InstallmentView_color, Color.GRAY);
            selectedColor = typedArray.getColor(R.styleable.InstallmentView_selectedColor, Color.RED);
            lineHeight = typedArray.getDimensionPixelSize(R.styleable.InstallmentView_lineHeight, 4);
            textSize = typedArray.getDimensionPixelSize(R.styleable.InstallmentView_textSize, 24);
            typedArray.recycle();
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        pointWidth = Math.max(partWidth, selectedPartWidth);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textHeight = fontMetrics.bottom - fontMetrics.top;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float width = MeasureSpec.getSize(widthMeasureSpec);
        float height;
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
                height = pointWidth;
                break;
        }
        lineWidth = width - pointWidth;
        intervalLineWidth = lineWidth / (partCount - 1);
        setMeasuredDimension((int) width, (int) height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        for (int i = 0; i < partCount; i++) {
            if (i == selection) {
                drawSelectedPart(i, canvas);
            } else {
                drawPart(i, canvas);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            selectPart(event.getX());
        }
        return true;
    }

    private void selectPart(float x) {
        int position = (int) (x / intervalLineWidth);
        if (selection == position) {
            return;
        }
        float pointEndX = pointWidth + intervalLineWidth * position;
        if (x < pointEndX) {
            selection = position;
            invalidate();
            if (onSelectPartListener != null) {
                onSelectPartListener.onSelectPart(this, selection);
            }
        }
    }

    private void drawLine(Canvas canvas) {
        paint.setColor(color);
        canvas.drawRect(pointWidth / 2,
                (getHeight() - lineHeight) / 2,
                getWidth() - pointWidth / 2,
                (getHeight() + lineHeight) / 2, paint);
    }

    private void drawPart(int position, Canvas canvas) {
        float x = pointWidth / 2 + intervalLineWidth * position;
        float y = getHeight() / 2;
        RectF rectF = new RectF(x - partWidth / 2,
                y - partWidth / 2,
                x + partWidth / 2,
                y + partWidth / 2);
        canvas.drawOval(rectF, paint);
        drawNumber(x, position + 1, canvas);
    }

    private void drawSelectedPart(int position, Canvas canvas) {
        float x = pointWidth / 2 + intervalLineWidth * position;
        float y = getHeight() / 2;
        RectF rectF = new RectF(x - selectedPartWidth / 2,
                y - selectedPartWidth / 2,
                x + selectedPartWidth / 2,
                y + selectedPartWidth / 2);
        paint.setColor(selectedColor);
        canvas.drawOval(rectF, paint);
        drawNumber(x, position + 1, canvas);
        paint.setColor(color);
    }

    private void drawNumber(float x, int number, Canvas canvas) {
        canvas.drawText(number + "", x, (getHeight() + textHeight / 2) / 2, textPaint);
    }

    public void setOnSelectPartListener(OnSelectPartListener onSelectPartListener) {
        this.onSelectPartListener = onSelectPartListener;
    }

    public interface OnSelectPartListener {

        void onSelectPart(InstallmentView view, int position);

    }

}
