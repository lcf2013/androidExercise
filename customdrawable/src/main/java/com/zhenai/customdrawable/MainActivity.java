package com.zhenai.customdrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.test1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.test2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),
                R.drawable.test3);

        ImageView iv = (ImageView) findViewById(R.id.img1);
        iv.setImageDrawable(new RoundImageDrawable(bitmap));
        ImageView iv2 = (ImageView) findViewById(R.id.img2);
        iv2.setImageDrawable(new RoundImageDrawable(bitmap2));
        ImageView iv3 = (ImageView) findViewById(R.id.img3);
        iv3.setImageDrawable(new CircleImageDrawable(bitmap3));


        TextView textView =(TextView)findViewById(R.id.text);
        textView.setBackgroundDrawable(new RoundImageDrawable(bitmap));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
