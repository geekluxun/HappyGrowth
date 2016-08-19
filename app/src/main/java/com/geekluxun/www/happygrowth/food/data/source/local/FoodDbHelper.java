package com.geekluxun.www.happygrowth.food.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DB帮助类
 */
public class FoodDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "growth.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FoodPersistenceContract.FoodEntry.TABLE_NAME + " (" +
                    FoodPersistenceContract.FoodEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    FoodPersistenceContract.FoodEntry.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP +
                    FoodPersistenceContract.FoodEntry.COLUMN_NAME_AMOUNT + TEXT_TYPE + COMMA_SEP +
                    FoodPersistenceContract.FoodEntry.COLUMN_NAME_TYPE + TEXT_TYPE +
            " )";

    public FoodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
