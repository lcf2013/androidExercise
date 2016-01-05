package com.zhenai.toucheventmechanism;

import android.content.Context;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/1/5.
 */
public class ChildView extends LinearLayout {
    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TouchEventUtil.TAG, "ChildView | onTouchEvent--->" + TouchEventUtil.getTouchAction(event.getAction()));
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TouchEventUtil.TAG, "ChildView | onInterceptTouchEvent--->" + TouchEventUtil.getTouchAction(event.getAction()));
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
       // getParent().requestDisallowInterceptTouchEvent(true);
        Log.i(TouchEventUtil.TAG, "ChildView | dispatchTouchEvent--->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.dispatchTouchEvent(event);
        //return false;
    }
}
