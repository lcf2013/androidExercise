package com.zhenai.viewdrawpass;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by admin on 2016/1/4.
 */
public class MyViewGroup extends ViewGroup {
    private static String TAG="MyViewGroup";
    private Context mContext;

    public MyViewGroup(Context context) {
        super(context);
        mContext=context;
        init();
    }



    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
        Button btn=new Button(mContext);
        btn.setText("I am a Button");
        addView(btn);

        ImageView img=new ImageView(mContext);
        img.setBackgroundResource(R.mipmap.ic_launcher);
        addView(img);

        TextView txt=new TextView(mContext);
        txt.setText("Only Text");
        addView(txt);

        MyView myView=new MyView(mContext);
        addView(myView);
    }


    private void init() {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int childCount=getChildCount();
        Log.i(TAG, "the size of this ViewGroup is -->" + childCount);
        Log.i(TAG,"********onMeasure start**********");

        int specSize_width=MeasureSpec.getSize(widthMeasureSpec);
        int specSize_height=MeasureSpec.getSize(heightMeasureSpec);

        Log.i(TAG, "***specSize_width:" + specSize_width + " ***specSize_height:" + specSize_height);
        setMeasuredDimension(specSize_width,specSize_height);

        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec(50,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(50,MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount=getChildCount();
        int startLeft=0;
        int startTop=10;

        Log.i(TAG,"***onLayout start********");
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            child.layout(startLeft,startTop,startLeft+child.getMeasuredWidth(),startTop+child.getMeasuredHeight());
            startLeft+=startLeft+child.getMeasuredWidth()+10;
            Log.i(TAG,"*******onLayout startLeft:"+startLeft);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(TAG,"***dispatchDraw start*****");
        super.dispatchDraw(canvas);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Log.i(TAG,"******drawChild start********");
        return super.drawChild(canvas, child, drawingTime);
    }
}
