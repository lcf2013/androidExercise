package com.zhenai.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zhenai.customview.views.BounceScrollView;
import com.zhenai.customview.views.BounceScrollView.Callback;
import com.zhenai.customview.views.SecondActivity;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by admin on 2016/3/30.
 */
public class BounceScrollViewActivity extends AppCompatActivity{
    private ListView mListView;
    private BounceScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_scrollview);
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollView);
        mScrollView.setCallBack(new Callback()
        {

            @Override
            public void callback()
            {
                Toast.makeText(BounceScrollViewActivity.this, "you can do something!", 0)
                        .show();
                Intent intent = new Intent(BounceScrollViewActivity.this,
                        SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        mListView = (ListView) findViewById(R.id.id_listView);
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>(
                Arrays.asList("Hello", "World", "Welcome", "Java",
                        "Android", "Lucene", "C++", "C#", "HTML",
                        "Welcome", "Java", "Android", "Lucene", "C++",
                        "C#", "HTML"))));
    }
}
