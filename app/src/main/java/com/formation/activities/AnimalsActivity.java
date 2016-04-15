package com.formation.activities;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.formation.data.AnimalContract.Animals;

public class AnimalsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;

    static final String[] PROJECTION = new String[]{Animals._ID, Animals.COLUMN_NAME_NAME};

    static final String SELECTION = "((" + Animals.COLUMN_NAME_NAME + " NOTNULL) AND ("
            + Animals.COLUMN_NAME_NAME + " != '' ))";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] fromColumns = { Animals.COLUMN_NAME_NAME };
        int[] toViews = { android.R.id.text1 };

        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //Uri uri = Uri.parse(Animals.SCHEME + Animals.AUTHORITY + Animals.SEPARATOR + Animals.TABLE_NAME);
        return new CursorLoader(this, Animals.CONTENT_URI, PROJECTION, SELECTION, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        int idColumnIndex = cursor.getColumnIndexOrThrow(Animals._ID);
        int nameColumnIndex = cursor.getColumnIndexOrThrow(Animals.COLUMN_NAME_NAME);
        while (!cursor.isAfterLast()) {
            cursor.getLong(idColumnIndex);
            cursor.getString(nameColumnIndex);
            cursor.moveToNext();
        }
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        super.onListItemClick(listView, v, position, id);

        Intent animalIntent = new Intent(AnimalsActivity.this, AnimalDetailActivity.class);
        animalIntent.putExtra("animalId", id);
        startActivity(animalIntent);
    }
}
