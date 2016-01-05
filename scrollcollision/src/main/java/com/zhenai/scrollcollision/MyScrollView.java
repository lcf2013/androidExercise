package com.zhenai.scrollcollision;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by admin on 2016/1/5.
 */
public class MyScrollView extends ScrollView {
    private GestureDetector gestureDetector;
    public MyScrollView(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //如果更接近水平滚动，则返回false,让子视图处理
                return Math.abs(distanceY)>Math.abs(distanceX);
            }
        });
    }
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //如果更接近水平滚动，则返回false,让子视图处理
                return Math.abs(distanceY)>Math.abs(distanceX);
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean f1=gestureDetector.onTouchEvent(ev);
        boolean f2=super.onInterceptTouchEvent(ev);
        return f2&& f1;
    }
}
