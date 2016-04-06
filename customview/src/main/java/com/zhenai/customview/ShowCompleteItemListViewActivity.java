package com.zhenai.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.zhenai.customview.utils.CommonAdapter;
import com.zhenai.customview.utils.ViewHolder;
import com.zhenai.customview.views.ShowCompleteItemListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2016/4/6.
 */
public class ShowCompleteItemListViewActivity extends AppCompatActivity {

    private ShowCompleteItemListView mListView;
    private CommonAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complete_listitem);

        mListView = (ShowCompleteItemListView) findViewById(R.id.id_lv);
        initDatas();
        initViews();

    }

    private void initViews()
    {
        mListView = (ShowCompleteItemListView) findViewById(R.id.id_lv);
        mAdapter = new CommonAdapter<String>(this, mDatas, R.layout.show_complete_listitem)
        {
            @Override
            public void convert(ViewHolder helper, String item)
            {
                View convertView = helper.getConvertView();
                convertView.setBackgroundColor(Color.rgb(
                        new Random().nextInt(255), new Random().nextInt(255),
                        new Random().nextInt(255)));
                ViewGroup.LayoutParams lp = convertView.getLayoutParams();
                lp.height = mListView.getItemHeight();
                convertView.setLayoutParams(lp);
                helper.setText(R.id.id_title, item);
            }
        };

        mListView.setAdapter(mAdapter);
    }

    private void initDatas()
    {
        mDatas = new ArrayList<String>();

        for (int i = 'A'; i <= 'Z'; i++)
        {
            mDatas.add(String.valueOf((char) +i));
        }
    }
}
