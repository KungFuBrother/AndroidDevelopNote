package com.smartown.library.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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

    private int animationDuration = 400;

    private float pointWidth;
    private float pointArea;
    private float lineWidth;
    private float intervalLineWidth;

    private Paint paint;
    private Paint textPaint;
    private float textHeight;

    private int lastSelection = -1;
    private int selection = -1;
    private OnSelectPartListener onSelectPartListener;

    private ValueAnimator animator;
    private float animationValue = 0;

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
        animator = ValueAnimator.ofFloat(0, animationDuration);
        animator.setDuration(animationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
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
        pointArea = (float) (Math.PI * Math.pow(pointWidth / 2, 2));
        setMeasuredDimension((int) width, (int) height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        for (int i = 0; i < partCount; i++) {
            drawPart(i, canvas);
        }
        paint.setColor(selectedColor);
        drawAnimation(canvas);
        paint.setColor(color);
        for (int i = 0; i < partCount; i++) {
            drawNumber(i, canvas);
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
            setSelection(position);
        }
    }

    public void setSelection(int selection) {
        lastSelection = this.selection;
        this.selection = selection;
        animator.start();
        if (onSelectPartListener != null) {
            onSelectPartListener.onSelectPart(this, selection);
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
    }

    private void drawAnimation(Canvas canvas) {
        if (lastSelection == -1) {
            drawSelectedPart(selection, (float) Math.sqrt(pointArea / animationDuration * animationValue / Math.PI) * 2, canvas);
            return;
        }
        final float linkLineArea = Math.abs(lastSelection - selection) * intervalLineWidth * lineHeight;
        final float animationSpeed = (pointArea + linkLineArea) / animationDuration;
        final float animatedArea = animationSpeed * animationValue;
        float lastPointArea = 0;
        float lineArea = 0;
        float nextPointArea = 0;
        if (linkLineArea >= pointArea) {
            if (animatedArea >= pointArea) {
                if (animatedArea >= linkLineArea) {
                    nextPointArea = animatedArea - linkLineArea;
                    lineArea = pointArea - nextPointArea;
                } else {
                    //选中部分全在连接线上
                    lineArea = pointArea;
                }
            } else {
                lastPointArea = pointArea - animatedArea;
                lineArea = animatedArea;
            }
        } else {
//            linkLineArea < pointArea
            if (animatedArea >= linkLineArea) {
                if (animatedArea >= pointArea) {
                    nextPointArea = animatedArea - linkLineArea;
                    lineArea = pointArea - nextPointArea;
                } else {
                    lastPointArea = pointArea - animatedArea;
                    lineArea = linkLineArea;
                    nextPointArea = animatedArea - linkLineArea;
                }
            } else {
                lastPointArea = pointArea - animatedArea;
                lineArea = animatedArea;
            }
        }
        System.out.println("animate/" + pointArea + "/" + linkLineArea + "/" + animationValue + "/" + lastPointArea + "/" + lineArea + "/" + nextPointArea);
        if (lastPointArea > 0) {
            final float lastPointWidth = (float) Math.sqrt(lastPointArea / Math.PI) * 2;
            drawSelectedPart(lastSelection, lastPointWidth, canvas);
        }
        if (nextPointArea > 0) {
            final float nextPointWidth = (float) Math.sqrt(nextPointArea / Math.PI) * 2;
            drawSelectedPart(selection, nextPointWidth, canvas);
        }
        if (lineArea > 0) {
            if (lineArea >= linkLineArea) {
                final float left = pointWidth / 2 + intervalLineWidth * Math.min(lastSelection, selection);
                final float right = pointWidth / 2 + intervalLineWidth * Math.max(lastSelection, selection);
                drawSelectedLine(left, right, canvas);
            } else {
                if (animatedArea < linkLineArea) {
                    if (animatedArea > lineArea) {
                        if (selection > lastSelection) {
                            final float right = pointWidth / 2 + intervalLineWidth * lastSelection + (animatedArea / lineHeight);
                            final float left = right - (lineArea / lineHeight);
                            drawSelectedLine(left, right, canvas);
                        } else {
                            final float left = pointWidth / 2 + intervalLineWidth * lastSelection - (animatedArea / lineHeight);
                            final float right = left + (lineArea / lineHeight);
                            drawSelectedLine(left, right, canvas);
                        }
                    } else {
                        if (selection > lastSelection) {
                            final float left = pointWidth / 2 + intervalLineWidth * lastSelection;
                            final float right = left + (lineArea / lineHeight);
                            drawSelectedLine(left, right, canvas);
                        } else {
                            final float right = pointWidth / 2 + intervalLineWidth * lastSelection;
                            final float left = right - (lineArea / lineHeight);
                            drawSelectedLine(left, right, canvas);
                        }
                    }
                } else {
                    if (selection > lastSelection) {
                        final float right = pointWidth / 2 + intervalLineWidth * selection;
                        final float left = right - (lineArea / lineHeight);
                        drawSelectedLine(left, right, canvas);
                    } else {
                        final float left = pointWidth / 2 + intervalLineWidth * selection;
                        final float right = left + (lineArea / lineHeight);
                        drawSelectedLine(left, right, canvas);
                    }
                }
            }
        }
    }

    private void drawSelectedPart(int position, float width, Canvas canvas) {
        float x = pointWidth / 2 + intervalLineWidth * position;
        float y = getHeight() / 2;
        RectF rectF = new RectF(x - width / 2,
                y - width / 2,
                x + width / 2,
                y + width / 2);
        canvas.drawOval(rectF, paint);
    }

    private void drawSelectedLine(float left, float right, Canvas canvas) {
        canvas.drawRect(left,
                (getHeight() - lineHeight) / 2,
                right,
                (getHeight() + lineHeight) / 2, paint);
    }

    private void drawNumber(int position, Canvas canvas) {
        float x = pointWidth / 2 + intervalLineWidth * position;
        canvas.drawText((position + 1) + "", x, (getHeight() + textHeight / 2) / 2, textPaint);
    }

    public void setOnSelectPartListener(OnSelectPartListener onSelectPartListener) {
        this.onSelectPartListener = onSelectPartListener;
    }

    public interface OnSelectPartListener {

        void onSelectPart(InstallmentView view, int position);

    }

}
