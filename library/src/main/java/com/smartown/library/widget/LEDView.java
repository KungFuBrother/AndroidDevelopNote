package com.smartown.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.smartown.note.library.R;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-09-14 10:50
 * <p/>
 * 描述：
 */
public class LEDView extends View {

    private int color;
    private String number;
    private Paint paint;
    //最小单位点的大小
    private int pointWidth;
    //间隔大小
    private int intervalWidth;

    public LEDView(Context context) {
        this(context, null);
    }

    public LEDView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        color = Color.BLACK;
        number = "";
        pointWidth = 4;
        intervalWidth = 1;
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LEDView);
            color = typedArray.getColor(R.styleable.LEDView_ledColor, Color.BLACK);
            number = typedArray.getString(R.styleable.LEDView_ledNumber);
            pointWidth = typedArray.getDimensionPixelSize(R.styleable.LEDView_ledPointWidth, 4);
            intervalWidth = typedArray.getDimensionPixelSize(R.styleable.LEDView_ledIntervalWidth, 1);
            typedArray.recycle();
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int length = number.length();
        setMeasuredDimension(((length * 5) + 1) * pointWidth + (length * 3) * intervalWidth, 9 * pointWidth + 6 * intervalWidth);
    }

    //    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        switch (heightMode) {
//            case MeasureSpec.EXACTLY:
//                final int height = MeasureSpec.getSize(heightMeasureSpec);
//                measure(height);
//                break;
//            default:
//                measure(42);
//                break;
//        }
//    }
//
//    private void measure(int height) {
//        intervalWidth = height / 42;
//        pointWidth = 4 * intervalWidth;
//        int length = number.length();
//        setMeasuredDimension(((length * 5) + 1) * pointWidth + (length * 3) * intervalWidth, height);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startX = pointWidth;
        final int length = number.length();
        for (int i = 0; i < length; i++) {
            drawNumber(number.substring(i, i + 1), startX, canvas);
            startX += (5 * pointWidth + 3 * intervalWidth);
        }
    }

    private void drawNumber(String number, int startX, Canvas canvas) {
        int[][] points;
        switch (number) {
            case "0":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "1":
                points = new int[][]{
                        {0, 0, 1, 0},
                        {0, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 1}};
                break;
            case "2":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 1, 0},
                        {0, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 1, 1, 1}};
                break;
            case "3":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "4":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 1}};
                break;
            case "5":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {1, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 1},
                        {0, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "6":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 0},
                        {1, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "7":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 1, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0}};
                break;
            case "8":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "9":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 1},
                        {0, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case ".":
                points = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 1, 0}};
                break;
            case ":":
                points = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}};
                break;
            case "a":
            case "A":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1}};
                break;
            case "b":
            case "B":
                points = new int[][]{
                        {1, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 0}};
                break;
            case "c":
            case "C":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "d":
            case "D":
                points = new int[][]{
                        {1, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 0}};
                break;
            case "e":
            case "E":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 1, 1, 1},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 1, 1, 1}};
                break;
            case "f":
            case "F":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 1, 1, 1},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0}};
                break;
            case "g":
            case "G":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 0},
                        {1, 0, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "h":
            case "H":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1}};
                break;
            case "i":
            case "I":
                points = new int[][]{
                        {0, 1, 1, 1},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 1}};
                break;
            case "j":
            case "J":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {1, 0, 1, 0},
                        {0, 1, 0, 0}};
                break;
            case "k":
            case "K":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 1, 0},
                        {1, 1, 1, 0},
                        {1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 1, 0},
                        {1, 0, 0, 1}};
                break;
            case "l":
            case "L":
                points = new int[][]{
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 1, 1, 1}};
                break;
            case "m":
            case "M":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1}};
                break;
            case "n":
            case "N":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1}};
                break;
            case "o":
            case "O":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "p":
            case "P":
                points = new int[][]{
                        {1, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0}};
                break;
            case "q":
            case "Q":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 0, 1, 1},
                        {0, 1, 1, 1}};
                break;
            case "r":
            case "R":
                points = new int[][]{
                        {1, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 0},
                        {1, 0, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1}};
                break;
            case "s":
            case "S":
                points = new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "t":
            case "T":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0}};
                break;
            case "u":
            case "U":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0}};
                break;
            case "v":
            case "V":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0}};
                break;
            case "w":
            case "W":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1, 0, 0, 1}};
                break;
            case "x":
            case "X":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {1, 0, 0, 1},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 1, 1, 0},
                        {1, 0, 0, 1},
                        {1, 0, 0, 1}};
                break;
            case "y":
            case "Y":
                points = new int[][]{
                        {1, 0, 0, 1},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0}};
                break;
            case "z":
            case "Z":
                points = new int[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                        {0, 2, 0, 0},
                        {0, 0, 0, 0},
                        {1, 0, 0, 1}};
                break;
            default:
                points = new int[7][4];
                break;
        }
        drawPoints(points, startX, canvas);
    }

    private void drawPoints(int[][] points, int startX, Canvas canvas) {
        for (int i = 0; i < 7; i++) {
            final int[] row = points[i];
            for (int j = 0; j < 4; j++) {
                if (row[j] == 1) {
                    drawPoint(i, j, startX, canvas);
                }
            }
        }
    }

    private void drawPoint(int column, int row, int startX, Canvas canvas) {
        final int left = startX + (row * (pointWidth + intervalWidth));
        final int top = column * (pointWidth + intervalWidth) + pointWidth;
        canvas.drawRect(left, top, left + pointWidth, top + pointWidth, paint);
    }

}