package com.zhenai.autocompleteconcattext;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String[] CONTACTS_SUMMARY_PROJECTION = new String[]{ContactsContract.Data.DISPLAY_NAME};
    private AppCompatAutoCompleteTextView autoCompleteTextView;

    private SimpleCursorAdapter adapter;
    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};
    private static final String SELECTION="((" + ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND ("

            + ContactsContract.Data.HAS_PHONE_NUMBER + "=1) AND ("

            + ContactsContract.Data.DISPLAY_NAME + " != '' ))";


    LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks  =new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(MainActivity.this, ContactsContract.Data.CONTENT_URI,
                    PROJECTION, SELECTION, null, null);

        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            adapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            adapter.swapCursor(null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AppCompatAutoCompleteTextView) findViewById(R.id.autoComplete);

        adapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,null,CONTACTS_SUMMARY_PROJECTION,new int[]{android.R.id.text1},0);
        autoCompleteTextView.setAdapter(adapter);
        getLoaderManager().initLoader(0,null,loaderCallbacks);

    }

}
