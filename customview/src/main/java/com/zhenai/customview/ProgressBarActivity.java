package com.zhenai.customview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zhenai.customview.views.HorizontalProgressBarWithNumber;
import com.zhenai.customview.views.RoundProgressBarWidthNumber;

public class ProgressBarActivity extends AppCompatActivity {

    private RoundProgressBarWidthNumber mRoundProgressBar;

    private HorizontalProgressBarWithNumber mProgressBar;
    private static final int MSG_PROGRESS_UPDATE = 0x110;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int progress = mProgressBar.getProgress();
            int roundProgress = mRoundProgressBar.getProgress();
            mProgressBar.setProgress(++progress);
            mRoundProgressBar.setProgress(++roundProgress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        mProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.id_progressbar01);
        mRoundProgressBar = (RoundProgressBarWidthNumber) findViewById(R.id.id_progress02);
        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
