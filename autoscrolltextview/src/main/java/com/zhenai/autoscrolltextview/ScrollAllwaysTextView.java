package com.zhenai.autoscrolltextview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2015/12/30.
 */
public class ScrollAllwaysTextView extends TextView {
    public ScrollAllwaysTextView(Context context) {
        this(context, null);
    }

    public ScrollAllwaysTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollAllwaysTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused)
            super.onWindowFocusChanged(focused);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
