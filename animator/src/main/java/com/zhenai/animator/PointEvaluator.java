package com.zhenai.animator;

import android.animation.TypeEvaluator;

/**
 * Created by admin on 2015/12/1.
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        Point start=(Point)o;
        Point end=(Point)t1;
        float x=start.getX()+v*(end.getX()-start.getX());
        float y=start.getY()+v*(end.getY()-start.getY());
        Point p=new Point(x,y);
        return p;
    }
}
