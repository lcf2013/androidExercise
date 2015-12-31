package com.zhenai.simplecursoradapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.baseExpandableListAdapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,BaseExpandableListAdapterActivity.class));

            }
        });
        findViewById(R.id.simpleCursorTreeAdapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SimpleCursorTreeAdapterActivity.class));
            }
        });findViewById(R.id.simpleExpandableListAdapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SimpleExpandableListAdapterActivity.class));
            }
        });
    }


}
