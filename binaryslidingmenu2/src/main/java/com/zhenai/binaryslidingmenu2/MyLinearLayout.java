package com.zhenai.binaryslidingmenu2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by admin on 2016/3/15.
 */
public class MyLinearLayout  extends LinearLayout
{

    public MyLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
//      Log.e("TAG", "MyLinearLayout");
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i)
    {
//      Log.e("tag", "getChildDrawingOrder" + i + " , " + childCount);

        if (i == 0)
            return 1;
        if (i == 2)
            return 2;
        if (i == 1)
            return 0;
        return super.getChildDrawingOrder(childCount, i);

    }

}
