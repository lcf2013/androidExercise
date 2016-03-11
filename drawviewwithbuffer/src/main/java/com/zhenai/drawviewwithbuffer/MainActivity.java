package com.zhenai.drawviewwithbuffer;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    EmbossMaskFilter emboss;
    BlurMaskFilter blur;

    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emboss=new EmbossMaskFilter(new float[]{1.5f,1.5f,1.5f},0.6f,6,4.2f);
        blur = new BlurMaskFilter(8,BlurMaskFilter.Blur.NORMAL);
        drawView  =(DrawView)findViewById(R.id.drawView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.red:
                drawView.paint.setColor(Color.RED);
                item.setChecked(true);

                break;
            case R.id.green:
                drawView.paint.setColor(Color.GREEN);
                item.setChecked(true);

                break;
            case R.id.blue:
                drawView.paint.setColor(Color.BLUE);
                item.setChecked(true);

                break;
            case R.id.width_1:
                drawView.paint.setStrokeWidth(1);
                item.setChecked(true);

                break;
            case R.id.width_2:
                drawView.paint.setStrokeWidth(3);
                item.setChecked(true);

                break;
            case R.id.width_3:
                drawView.paint.setStrokeWidth(5);
                item.setChecked(true);

                break;

            case R.id.blur:

                drawView.paint.setMaskFilter(blur);
                break;

            case R.id.emboss:
                drawView.paint.setMaskFilter(emboss);
                break;
        }

        return true;
    }
}
