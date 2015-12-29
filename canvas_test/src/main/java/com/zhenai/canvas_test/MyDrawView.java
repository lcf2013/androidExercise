package com.zhenai.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2015/12/29.
 */
public class MyDrawView extends View {

    private Paint mPaint;

    public MyDrawView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.RED);
    }

    public MyDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
//        canvas.translate(100, 100);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        canvas.save();
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.rotate(45,200,200);
        canvas.drawRect(new Rect(0,0,400,400),mPaint);

//        canvas.restore();
//        canvas.scale(0.5f,0.5f,200,200);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(new Rect(0,0,400,400),mPaint);



    }
}
