package com.zhenai.menu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        root = (RelativeLayout) findViewById(R.id.root);
        registerForContextMenu(text);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        menu.setHeaderTitle("请选择背景颜色");
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(item.isCheckable())
            item.setChecked(true);
        switch (id) {
            case R.id.font_10:
                text.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,10,getResources().getDisplayMetrics()));
                break;
            case R.id.font_12:
                text.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics()));
                break;
            case R.id.font_14:
                text.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
                break;
            case R.id.font_16:
                text.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                break;
            case R.id.font_18:
                text.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,18,getResources().getDisplayMetrics()));
                break;
            case R.id.red_font:
                text.setTextColor(Color.RED);
                break;
            case R.id.green_font:
                text.setTextColor(Color.GREEN);
                break;

            case R.id.blue_font:
                text.setTextColor(Color.BLUE);
                break;





        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.red:
                item.setChecked(true);
                root.setBackgroundColor(Color.RED);
                break;

            case R.id.green:
                item.setChecked(true);
                root.setBackgroundColor(Color.GREEN);

                break;

            case R.id.blue:
                item.setChecked(true);
                root.setBackgroundColor(Color.BLUE);
                break;

        }
        return true;
    }
}
