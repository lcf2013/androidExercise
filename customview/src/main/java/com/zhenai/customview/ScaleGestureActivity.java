package com.zhenai.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class ScaleGestureActivity extends AppCompatActivity implements  SurfaceHolder.Callback{
    private SurfaceView mSurfaceView = null;
    private SurfaceHolder mSurfaceHolder = null;
    private ScaleGestureDetector mScaleGestureDetector = null;
    private Bitmap mBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_gesture);

        mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureListener());
        mSurfaceHolder.addCallback(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //返回给ScaleGestureDetector来处理
        return mScaleGestureDetector.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // TODO Auto-generated method stub
            mBitmap = BitmapFactory.decodeStream(getAssets().open("world.jpg"));
            //锁定整个SurfaceView
            Canvas mCanvas = mSurfaceHolder.lockCanvas();
            //画图
            mCanvas.drawBitmap(mBitmap, 0f, 0f, null);
            //绘制完成，提交修改
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            //重新锁一次
            mSurfaceHolder.lockCanvas(new Rect(0, 0, 0, 0));
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public class ScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub

            Matrix mMatrix = new Matrix();
            //缩放比例
            float scale = detector.getScaleFactor() / 3;
            mMatrix.setScale(scale, scale);

            //锁定整个SurfaceView
            Canvas mCanvas = mSurfaceHolder.lockCanvas();
            //清屏
            mCanvas.drawColor(Color.BLACK);
            //画缩放后的图
            mCanvas.drawBitmap(mBitmap, mMatrix, null);
            //绘制完成，提交修改
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            //重新锁一次
            mSurfaceHolder.lockCanvas(new Rect(0, 0, 0, 0));
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);

            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            //一定要返回true才会进入onScale()这个函数
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scale_gesture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
