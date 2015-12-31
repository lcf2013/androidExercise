package com.zhenai.simplecursoradapter;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;


/**
 * Created by admin on 2015/12/31.
 */
public class BaseExpandableListAdapterActivity extends ExpandableListActivity {

    MyExpandableListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_expandable);

        adapter = new MyExpandableListAdapter(this);
        getExpandableListView().setAdapter(adapter);
        registerForContextMenu(getExpandableListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("文件操作");
        // add context menu item
        menu.add(0, 1, Menu.NONE, "发送");
        menu.add(0, 2, Menu.NONE, "标记为重要");
        menu.add(0, 3, Menu.NONE, "重命名");
        menu.add(0, 4, Menu.NONE, "删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info
                = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();

        TextView parentTextView;
        TextView childTextView;
        if (info.targetView instanceof TwoLineListItem) {
            TwoLineListItem tolineListItem = (TwoLineListItem) info.targetView;
            parentTextView = tolineListItem.getText1();
            childTextView = tolineListItem.getText2();


            int type = ExpandableListView.getPackedPositionType(info.packedPosition);
            if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                int groupPos
                        = ExpandableListView.getPackedPositionGroup(info.packedPosition);
                int childPos
                        = ExpandableListView.getPackedPositionChild(info.packedPosition);
                Toast.makeText(this, childTextView.getText().toString() + ": Child " + childPos
                                + " clicked in group " + groupPos,
                        Toast.LENGTH_SHORT).show();
                return true;
            } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                int groupPos
                        = ExpandableListView.getPackedPositionGroup(info.packedPosition);
                Toast.makeText(this, parentTextView.getText().toString() + ": Group " + groupPos
                        + " clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return false;
    }
}

class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;

    public MyExpandableListAdapter(Context context) {
        this.context = context;
    }

    String[] groups = {"peole names", "dog names", "cat names", "fish names"};

    String[][] children = {{"Jack", "Barry", "Rose"},
            {"Alice", "Mary", "Col"},
            {"Ha", "Jj", "Klo"},
            {"Fis", "Dwod", "Dwd"}};

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.textView.setText(groups[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(android.R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.textView.setText(children[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolder {
        public TextView textView;

    }
}
