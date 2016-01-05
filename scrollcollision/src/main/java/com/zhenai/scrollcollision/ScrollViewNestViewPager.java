package com.zhenai.scrollcollision;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewNestViewPager extends AppCompatActivity {

    private ViewPager viewPager;

    PagerAdapter pagerAdapter;

    List<String> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_nest_view_pager);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initData();
        pagerAdapter=new MyPageAdapter(this ,data);
        viewPager.setAdapter(pagerAdapter);
    }

    private void initData() {
        data.add("aa");
        data.add("aa");
        data.add("aa");
        data.add("aa");
        data.add("aa");
        data.add("aa");
    }

    private class MyPageAdapter extends PagerAdapter {
        private List<String> data;
        private Context context;

        public MyPageAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.viewpager_item,container,false);
            TextView textview=(TextView)itemView.findViewById(R.id.text);
            textview.setText(data.get(position));
            container.addView(itemView,0);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }

}
