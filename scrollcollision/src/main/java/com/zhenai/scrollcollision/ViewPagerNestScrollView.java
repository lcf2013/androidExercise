package com.zhenai.scrollcollision;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNestScrollView extends AppCompatActivity {


    private ViewPager viewPager;

    private PagerAdapter adapter;
    private List<String> data=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_nest_scroll_view);

        initData();
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter=new MyPageAdapter(this,data);
        viewPager.setAdapter(adapter);

    }

    private void initData() {
        data.add("aa");
        data.add("aa");
        data.add("aa");
        data.add("aa");
        data.add("aa");
    }

    private class MyPageAdapter extends PagerAdapter{

        private List<String> data;

        private Context context;
        public MyPageAdapter(Context context,List<String> data){
            this.data=data;
            this.context=context;
        }
        @Override
        public int getCount() {
            return data.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.scrollview_item,container,false);

            TextView textView = (TextView)view.findViewById(R.id.text);
            textView.setText(data.get(position));

            container.addView(view,0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
