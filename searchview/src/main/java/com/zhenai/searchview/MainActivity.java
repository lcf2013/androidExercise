package com.zhenai.searchview;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView sv;
    private ListView lv;
    private final String[] mString={"aaa","bbb","ccc"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mString));
        lv.setTextFilterEnabled(true);


        sv=(SearchView)findViewById(R.id.sv);
        sv.setIconifiedByDefault(false);
        sv.setOnQueryTextListener(this);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("search");
        AlertDialog alertDialog;
        ProgressDialog progressDialog;
        TimePickerDialog timePickerDialog;
        DatePickerDialog datePickerDialog;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this,"您选择的是："+query,Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
     if(TextUtils.isEmpty(newText)){
         lv.clearTextFilter();
     }else{
         lv.setFilterText(newText);
     }

        return true;
    }
}
