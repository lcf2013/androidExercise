package com.zhenai.camera3drotate;

import android.animation.Animator;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by admin on 2015/12/30.
 */
public class Rotate3dAnimation extends Animation {
   private float fromDegrees;//: 开始旋转角度。
    private float toDegrees;//： 旋转结束角度。
    private float centerX;//： 旋转中心X坐标
    private float centerY;//: 旋转中心Y坐标。
    private float depthZ;//： 旋转上Z轴上的距离。
    private boolean reverse;//： 正转或是反转。
    private Camera camera;

    public Rotate3dAnimation(float fromDegrees, float toDegrees,
                             float centerX, float centerY, float depthZ, boolean reverse){
        this.fromDegrees=fromDegrees;
        this.toDegrees=toDegrees;
        this.centerX=centerX;
        this.centerY=centerY;
        this.depthZ=depthZ;
        this.reverse=reverse;
        this.camera=new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = this.fromDegrees;
        float degrees = fromDegrees
                + ((this.toDegrees- fromDegrees) * interpolatedTime);

        final float centerX = this.centerX;
        final float centerY = this.centerY;
        final Camera camera = this.camera;

        final Matrix matrix = t.getMatrix();

        camera.save();
        if (this.reverse) {
            camera.translate(0.0f, 0.0f,
                    this.depthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f,
                    this.depthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);

    }
}
