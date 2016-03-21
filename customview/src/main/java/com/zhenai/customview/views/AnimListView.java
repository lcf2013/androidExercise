package com.zhenai.customview.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * listview看上去是倒着的，透视效果
 */
@SuppressLint("NewApi")
public class AnimListView extends ListView {
	private Camera mCamera;
	private Matrix mMatrix;

	public AnimListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCamera = new Camera();
		mMatrix = new Matrix();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mCamera.save();
		mCamera.rotate(30, 0, 0);
		mCamera.getMatrix(mMatrix);
		mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
		mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
		canvas.concat(mMatrix);
		super.onDraw(canvas);
		mCamera.restore();
	}
}
