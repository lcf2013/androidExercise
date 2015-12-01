package com.zhenai.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

/**
 * Created by admin on 2015/12/1.
 */
public class MyAnimatorView extends View {
    private final int radius=50;
    Point curPoint;
    private Paint paint;

    String color ;
    public MyAnimatorView(Context context) {
        super(context);
    }

    public MyAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    public MyAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(curPoint==null){
            curPoint=new Point(radius,radius);
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }
    }
    private  void startAnimation(){

        Point point=new Point(getWidth()/2,radius);
        Point end=new Point(getWidth()/2,getHeight()-radius);
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluator(),point,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                curPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator ani2=ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#0000ff","#ff0000");
        AnimatorSet set=new AnimatorSet();
        set.play(valueAnimator).with(ani2);
        set.setDuration(5000);
        /*valueAnimator.setRepeatCount(3);
        ani2.setRepeatCount(3);
        valueAnimator.setRepeatMode(5);
        ani2.setRepeatMode(5);*/

//        valueAnimator.setInterpolator(new AccelerateInterpolator(2f));
        valueAnimator.setInterpolator(new DecelerateAccelerateInterpolator());

        set.start();
    }

    private void drawCircle(Canvas canvas) {
        float x=curPoint.getX();
        float y=curPoint.getY();
        canvas.drawCircle(x,y,radius,paint);
    }
}
