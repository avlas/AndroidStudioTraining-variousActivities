package com.formation.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.formation.data.AnimalContract.Animals;

public class AnimalProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase db;
    private AnimalDbHelper dbHelper;
    private Cursor result;

    private static final int ANIMALS = 0;
    private static final int ANIMAL = 1;
    static {
        uriMatcher.addURI(Animals.AUTHORITY, Animals.TABLE_NAME, ANIMALS);
        uriMatcher.addURI(Animals.AUTHORITY, Animals.TABLE_NAME + "/#", ANIMAL);
    }

    public AnimalProvider() {
    }

    @Override
    public boolean onCreate() {
        dbHelper = new AnimalDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case ANIMALS: {
                result = dbHelper.getReadableDatabase().query(Animals.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case ANIMAL: {
                String[] id = {uri.getLastPathSegment()};
                result = dbHelper.getReadableDatabase().query(Animals.TABLE_NAME, projection, "_ID = ?", id, null, null, sortOrder);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        }
        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = dbHelper.getWritableDatabase();

        db.beginTransaction();

        long rowID = 0;
        try {
            rowID = db.insert(Animals.TABLE_NAME, null, values);
            db.setTransactionSuccessful(); // commit
        } finally {
            db.endTransaction(); // rollback
        }

        Uri _uri = null;
        if (rowID > 0) {
            _uri = ContentUris.withAppendedId(AnimalContract.Animals.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            Log.e("URI",_uri.toString());
        }
        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
/*        int count = 0;
        switch (uriMatcher.match(uri)) {
            case ANIMAL:
                count = db.delete(Animals.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;*/

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
/*        int count = 0;
        switch (uriMatcher.match(uri)) {
            case ANIMAL:
                count = db.update(Animals.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;*/
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
