package com.zhenai.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by admin on 2016/1/6.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    DrawThread thread;
    private void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread   = new DrawThread(holder,getContext());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning=true;
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRunning=false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class DrawThread extends Thread {
        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;

        public DrawThread(SurfaceHolder surfaceHolder, Context context) {
            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;

            paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void run() {
            Canvas c = null;

            while (isRunning) {
                try {
                    synchronized (surfaceHolder) {
                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }

        public void doDraw(Canvas c) {
            //清除上次绘制的残留
            c.drawColor(Color.BLACK);
            c.translate(200, 200);
            c.drawCircle(0,0,radius++,paint);
            if(radius>100){
                radius=10f;
            }
        }
    }
}
