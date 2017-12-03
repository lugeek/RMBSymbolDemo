package com.lugeek.rmbdemo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lugeek on 2017/12/3.
 */

public class PriceView extends AppCompatTextView {

    private Context mContext;

    private Paint mRMBSymbolPaint;
    private Path mRMBPath;

    public PriceView(Context context) {
        this(context, null);
    }

    public PriceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PriceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }



    private void init(Context context, AttributeSet attrs) {
        //https://developer.android.com/guide/topics/graphics/hardware-accel.html
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mContext = context;

        mRMBSymbolPaint = new Paint();
        mRMBSymbolPaint.setAntiAlias(true);
        mRMBSymbolPaint.setColor(0xff000000);
        mRMBSymbolPaint.setStyle(Paint.Style.FILL);
        mRMBSymbolPaint.setStrokeJoin(Paint.Join.ROUND);

        getPaint().ascent();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        printRMB(canvas);
        canvas.save();
        canvas.translate(getRMBWidth(), 0);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    private void printRMB(Canvas canvas) {
        if (mRMBPath == null) {
            int viewHeight = getViewHeight();
            int baseWidth = 188;
            int baseHeight = 245;
            float ratio = (float) baseHeight / baseWidth;
            int targetHeight = viewHeight;
            int targetWidth = (int) (targetHeight / ratio);
            int startX = 0;
            int startY = getOffsetY();
            int x30offset = targetWidth * 30 / baseWidth;
            int x80offset = targetWidth * 80 / baseWidth;
            int x60offset = targetWidth * 60 / baseWidth;
            int y140offset = targetHeight * 140 / baseHeight;
            int y16offset = targetHeight * 16 / baseHeight;
            int y34offset = targetHeight * 34 / baseHeight;
            int y53offset = targetHeight * 53 / baseHeight;


            mRMBPath = new Path();
            mRMBPath.moveTo(startX, startY);
            mRMBPath.rLineTo(x30offset, 0);
            mRMBPath.rLineTo(x80offset, y140offset);
            mRMBPath.rLineTo(-x30offset, 0);
            mRMBPath.rLineTo(x80offset, -y140offset);
            mRMBPath.rLineTo(x30offset, 0);
            mRMBPath.rLineTo(-x80offset, y140offset);
            mRMBPath.rLineTo(0, -y16offset);
            mRMBPath.rLineTo(x60offset, 0);
            mRMBPath.rLineTo(0, y16offset);
            mRMBPath.rLineTo(-x60offset, 0);
            mRMBPath.rLineTo(0, y34offset);
            mRMBPath.rLineTo(x60offset, 0);
            mRMBPath.rLineTo(0, y16offset);
            mRMBPath.rLineTo(-x60offset, 0);
            mRMBPath.rLineTo(0, y53offset);
            mRMBPath.rLineTo(-x30offset, 0);
            mRMBPath.rLineTo(0, -y53offset);
            mRMBPath.rLineTo(-x60offset, 0);
            mRMBPath.rLineTo(0, -y16offset);
            mRMBPath.rLineTo(x60offset, 0);
            mRMBPath.rLineTo(0, -y34offset);
            mRMBPath.rLineTo(-x60offset, 0);
            mRMBPath.rLineTo(0, -y16offset);
            mRMBPath.rLineTo(x60offset, 0);
            mRMBPath.rLineTo(0, y16offset);
            mRMBPath.rLineTo(-x80offset, -y140offset);
            canvas.drawPath(mRMBPath, mRMBSymbolPaint);
        } else {
            canvas.drawPath(mRMBPath, mRMBSymbolPaint);
        }
    }


    private int getViewHeight() {
        Rect minRect = new Rect();

        getPaint().getTextBounds("0123456789.", 0, 11, minRect);
        return minRect.bottom - minRect.top;
    }

    private int getOffsetY() {
        Rect minRect = new Rect();

        getPaint().getTextBounds("0123456789.", 0, 11, minRect);

        Paint.FontMetricsInt fm = getPaint().getFontMetricsInt();

        return minRect.top - fm.top;
    }

    private int getRMBWidth() {
        int viewHeight = getViewHeight();
        int baseWidth = 188;
        int baseHeight = 245;
        float ratio = (float) baseHeight / baseWidth;
        int targetHeight = viewHeight;
        int targetWidth = (int) (targetHeight / ratio);
        return targetWidth;
    }
}
