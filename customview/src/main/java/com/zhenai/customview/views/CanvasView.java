package com.zhenai.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/3/18.
 */
public class CanvasView extends View {
    private Rect mRect;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect(0, 0, 500, 500);

        //不是单纯地计算相交 而是去计算相交区域最近的左上端点和最近的右下端点
        mRect.intersect(250, 250, 750, 750);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        canvas.clipRect(mRect);

        canvas.drawColor(Color.RED);
    }
}
