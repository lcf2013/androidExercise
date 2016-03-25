package com.zhenai.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zhenai.customview.views.VerticalLinearLayout;

/**
 * Created by admin on 2016/3/25.
 */
public class VerticalLinearLayoutActivity extends AppCompatActivity {
    private VerticalLinearLayout mMianLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_linearlayout);

        mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);
        mMianLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener()
        {
            @Override
            public void onPageChange(int currentPage)
            {
//				mMianLayout.getChildAt(currentPage);
                Toast.makeText(VerticalLinearLayoutActivity.this, "ตฺ" + (currentPage + 1) + "าณ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
