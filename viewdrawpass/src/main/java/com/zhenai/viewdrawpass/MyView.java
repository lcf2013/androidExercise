package com.zhenai.viewdrawpass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by admin on 2016/1/4.
 */
public class MyView extends View {
    private Paint paint=new Paint();
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(80, MeasureSpec.EXACTLY)
                , MeasureSpec.makeMeasureSpec(80, MeasureSpec.EXACTLY));
//        setMeasuredDimension(50,50);
//        super.setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }
    @Override
    public void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        Log.i("MyViewGroup", "MyView is onDraw ") ;
        //加粗
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE) ;
        canvas.drawRect(0, 0, 30, 30, paint);
        canvas.drawText("MyView", 10, 40, paint);
    }
}
