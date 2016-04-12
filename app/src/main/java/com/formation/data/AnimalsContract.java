package com.formation.data;

import android.provider.BaseColumns;

public final class AnimalsContract {

    private AnimalsContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Animals implements BaseColumns {
        public static final String TABLE_NAME = "animals";
        public static final String COLUMN_NAME_DIET = "diet";
        public static final String COLUMN_NAME_FAMILY = "family";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_AGE = "age";
    }
}
