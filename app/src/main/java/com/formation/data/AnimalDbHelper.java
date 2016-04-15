package com.formation.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.formation.data.AnimalContract.Animals;

public class AnimalDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Animals.TABLE_NAME + ".db";

    private static final String SQL_CREATE_ANIMALS =
            "CREATE TABLE " + Animals.TABLE_NAME + " (" +
                    Animals._ID + " INTEGER PRIMARY KEY," +
                    Animals.COLUMN_NAME_ANIMAL_ID + " INTEGER," +
                    Animals.COLUMN_NAME_DIET + " TEXT," +
                    Animals.COLUMN_NAME_FAMILY + " TEXT," +
                    Animals.COLUMN_NAME_NAME + " TEXT," +
                    Animals.COLUMN_NAME_SEX + " TEXT," +
                    Animals.COLUMN_NAME_AGE + " INTEGER )";

    private static final String SQL_DELETE_ANIMALS = "DROP TABLE IF EXISTS " + Animals.TABLE_NAME;

    public AnimalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ANIMALS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database upgrade policy is to discard the data
        db.execSQL(SQL_DELETE_ANIMALS);
        onCreate(db);
    }
}
