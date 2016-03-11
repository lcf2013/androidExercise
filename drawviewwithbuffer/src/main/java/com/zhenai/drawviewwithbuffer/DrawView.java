package com.zhenai.drawviewwithbuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2016/1/20.
 */
public class DrawView extends View {
    float preX;
    float preY;
    private Path path;
    public Paint paint = null;
    final int VIEW_WIDTH = 320;
    final int VIEW_HEIGHT = 480;
    Bitmap cacheBitmap = null;
    Canvas cacheCanvas = null;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas =new Canvas();
        path=new Path();
        cacheCanvas.setBitmap(cacheBitmap);
        paint=new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x=event.getX();
        float y=event.getY();
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);//向缓冲图中绘画
                path.reset();
                break;

        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint bmpPaint  =new Paint();
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);//将缓冲中的bitmap画到View
        canvas.drawPath(path,paint);
    }
}
