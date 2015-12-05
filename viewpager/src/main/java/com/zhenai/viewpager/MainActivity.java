package com.zhenai.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    MyPageAdapter myPageAdapter = new MyPageAdapter();
    List<View> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TextView textView = new TextView(this);
        textView.setText("text1");

        TextView textView1 = new TextView(this);
        textView1.setText("text2");
        items.add(textView);
        items.add(textView1);

        viewPager.setAdapter(myPageAdapter);

        items.clear();

        TextView textView2 = new TextView(this);
        textView2.setText("text3");

        TextView textView3 = new TextView(this);
        textView2.setText("text4");

        items.add(textView2);
        items.add(textView3);
        myPageAdapter.notifyDataSetChanged();


    }

    class MyPageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = items.get(position);
            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = items.get(position);
            container.removeView(view);
        }


        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

}

