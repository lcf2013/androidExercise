package com.zhenai.toucheventmechanism;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/1/5.
 */
public class FatherView extends LinearLayout  {
    public FatherView(Context context) {
        super(context);
    }

    public FatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TouchEventUtil.TAG, "FatherView | onTouchEvent--->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    //return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TouchEventUtil.TAG,"FatherView | dispatchTouchEvent--->"+TouchEventUtil.getTouchAction(ev.getAction()));
        //getParent().requestDisallowInterceptTouchEvent(true);
       return super.dispatchTouchEvent(ev);
       // return false;
//     return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TouchEventUtil.TAG,"FatherView | onInterceptTouchEvent--->"+TouchEventUtil.getTouchAction(event.getAction()));
        return super.onInterceptHoverEvent(event);
        //return true;
    }
}
