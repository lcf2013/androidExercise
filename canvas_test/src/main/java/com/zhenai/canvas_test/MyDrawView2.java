package com.zhenai.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by admin on 2015/12/29.
 */
public class MyDrawView2 extends View {

    private Paint mPaint;

    private float mLineWidth=1;

    private Context mContext;
    private int TOTAL_SQUARE=8;
    private float mHalfHeight;
    private float mHalfWidth;

    public MyDrawView2(Context context) {
        super(context);
        mContext=context;
        init();
    }

    private void init() {
        mLineWidth= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,mLineWidth,mContext.getResources().getDisplayMetrics());

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mLineWidth);


    }

    public MyDrawView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public MyDrawView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHalfWidth=getWidth()/2;
        mHalfHeight=getHeight()/2;
        drawSqura(canvas);

    }


    private void drawSqura(Canvas canvas) {
        for(int i=0;i<TOTAL_SQUARE;i++){
                        canvas.save();
            float fraction=(float)i/TOTAL_SQUARE;
            canvas.scale(fraction,fraction,mHalfWidth,mHalfHeight);

            canvas.drawRect(new Rect(0,0,(int)mHalfWidth/2,(int)mHalfHeight/2),mPaint);

            canvas.restore();



        }

    }
}
