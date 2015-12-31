package com.zhenai.simplecursoradapter;

import android.app.ExpandableListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/12/31.
 */
public class SimpleExpandableListAdapterActivity extends ExpandableListActivity {


    private SimpleExpandableListAdapter adapter;
    private List<Map<String, String>> groupData;
    private List<List<Map<String, String>>> childData;
    private final String NAME = "name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.simple_cursor_tree_adapter);
        adapter = new SimpleExpandableListAdapter(this, groupData, android.R.layout.simple_expandable_list_item_1, new String[]{NAME},
                new int[]{android.R.id.text1}, childData, android.R.layout.simple_expandable_list_item_1,
                new String[]{NAME}, new int[]{android.R.id.text1});

        getExpandableListView().setAdapter(adapter);
    }

    private void initData() {
        groupData = new ArrayList<>();
        Map<String, String> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<>();
            map.put(NAME, "group" + i);
            groupData.add(map);
        }
        childData = new ArrayList<>();
        List<Map<String, String>> child;
        for (int i = 0; i < groupData.size(); i++) {
            child = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                map = new HashMap<>();
                map.put(NAME, "group" + i + "child" + j);
                child.add(map);
            }
            childData.add(child);
        }
    }
}

