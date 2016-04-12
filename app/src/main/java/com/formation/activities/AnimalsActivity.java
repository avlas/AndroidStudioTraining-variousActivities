package com.formation.activities;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.formation.data.AnimalsContract.Animals;

public class AnimalsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;

    private static final String SCHEME = "content://";
    private static final String AUTHORITY = "com.formation.data";
    private static final String BASE_PATH = "animals";

    // These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] { Animals._ID, Animals.COLUMN_NAME_NAME };

    // This is the select criteria
    static final String SELECTION = "((" + Animals.COLUMN_NAME_NAME + " NOTNULL) AND ("
            + Animals.COLUMN_NAME_NAME + " != '' ))";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_animals);

        String[] fromColumns = { Animals.COLUMN_NAME_NAME };
        int[] toViews = { android.R.id.text1 };

        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Uri.parse(SCHEME + AUTHORITY + "/" +  BASE_PATH), PROJECTION, SELECTION, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        while (!data.isAfterLast()) {
            long itemId = data.getLong(data.getColumnIndexOrThrow(Animals._ID));
            String displayName = data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_NAME));
            data.moveToNext();
        }
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
