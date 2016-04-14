package com.formation.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.formation.data.AnimalContract.Animals;

public class AnimalProvider extends ContentProvider {
    private static final int ANIMALS_LIST = 0;
    private static final int ANIMAL_DETAILS = 1;
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(Animals.AUTHORITY, Animals.TABLE_NAME, ANIMALS_LIST);
        matcher.addURI(Animals.AUTHORITY, Animals.TABLE_NAME + "/#", ANIMAL_DETAILS);
    }

    SQLiteDatabase db;
    AnimalDbHelper dbHelper;
    Cursor result;

    public AnimalProvider() {
    }

    @Override
    public boolean onCreate() {
        dbHelper = new AnimalDbHelper(getContext());
        db = dbHelper.getWritableDatabase();

        ContentValues lion = buildLion();
        ContentValues giraffe = buildGiraffe();

        db.beginTransaction();
        try {
            db.insert(Animals.TABLE_NAME, null, lion);
            db.insert(Animals.TABLE_NAME, null, giraffe);
            db.setTransactionSuccessful(); // commit
        } finally {
            db.endTransaction(); // rollback
        }
        return false;
    }

    @NonNull
    private ContentValues buildLion() {
        ContentValues animal = new ContentValues();
        animal.put(Animals.COLUMN_NAME_DIET, "carnivore");
        animal.put(Animals.COLUMN_NAME_FAMILY, "lion");
        animal.put(Animals.COLUMN_NAME_NAME, "Mara");
        animal.put(Animals.COLUMN_NAME_SEX, "F");
        animal.put(Animals.COLUMN_NAME_AGE, 2);
        return animal;
    }

    @NonNull
    private ContentValues buildGiraffe() {
        ContentValues animal = new ContentValues();
        animal.put(Animals.COLUMN_NAME_DIET, "herbivore");
        animal.put(Animals.COLUMN_NAME_FAMILY, "giraffe");
        animal.put(Animals.COLUMN_NAME_NAME, "Gigi");
        animal.put(Animals.COLUMN_NAME_SEX, "M");
        animal.put(Animals.COLUMN_NAME_AGE, 5);
        return animal;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = matcher.match(uri);
        switch (uriType) {
            case ANIMALS_LIST: {
                result = dbHelper.getReadableDatabase().query(Animals.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case ANIMAL_DETAILS: {
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
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
