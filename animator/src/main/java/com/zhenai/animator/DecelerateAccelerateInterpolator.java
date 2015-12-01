package com.zhenai.animator;

import android.animation.TimeInterpolator;

/**
 * Created by admin on 2015/12/1.
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float v) {
        float result;
        if(v<=0.5){
            result=(float)(Math.sin(v*Math.PI))/2;
        }else{
            result=(float)(2-Math.sin(Math.PI*v))/2;
        }


        return result;
    }
}
