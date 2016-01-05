package com.zhenai.toucheventmechanism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TouchEventUtil.TAG, "Activity | dispatchTouchEvent--->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.dispatchTouchEvent(event);
//        return false;
       // return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TouchEventUtil.TAG, "Activity | onTouchEvent--->" + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }

}
