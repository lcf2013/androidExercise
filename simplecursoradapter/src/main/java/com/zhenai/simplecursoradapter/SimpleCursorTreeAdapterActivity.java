package com.zhenai.simplecursoradapter;

import android.app.ExpandableListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorTreeAdapter;

/**
 * Created by admin on 2015/12/31.
 */
public class SimpleCursorTreeAdapterActivity extends ExpandableListActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private Cursor groupCursor;
    private String[] projection = {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

    private int groupIdColumnIndex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_cursor_tree_adapter);
        Uri.Builder builder = ContactsContract.CommonDataKinds.Phone.CONTENT_URI.buildUpon();
        groupCursor = managedQuery(builder.build(), projection, null, null, null);

        groupIdColumnIndex = groupCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID);

        MySimpleCursorTreeAdapter simpleCursorTreeAdapter = new MySimpleCursorTreeAdapter(this, groupCursor,
                android.R.layout.simple_expandable_list_item_1, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}, new int[]{android.R.id.text1},
                android.R.layout.simple_expandable_list_item_1, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, new int[]{android.R.id.text1});


        getExpandableListView().setAdapter(simpleCursorTreeAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    private class MySimpleCursorTreeAdapter extends SimpleCursorTreeAdapter {


        Context context ;
        public MySimpleCursorTreeAdapter(Context context, Cursor cursor, int groupLayout, String[] groupFrom, int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom, childTo);
            this.context=context;
        }

        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {

            Uri.Builder builder = ContactsContract.CommonDataKinds.Phone.CONTENT_URI.buildUpon();
            Uri uri = builder.build();


            long contactId = groupCursor.getLong(groupIdColumnIndex);

            Cursor cursor =  managedQuery(uri,new String[]{ContactsContract.CommonDataKinds.Phone._ID,ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{String.valueOf(contactId)},null);
            return cursor;
        }
    }
}

