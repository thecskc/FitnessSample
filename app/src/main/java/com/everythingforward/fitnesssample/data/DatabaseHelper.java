package com.everythingforward.fitnesssample.data;

import android.app.LoaderManager;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Santhoshkrishnachait on 10/17/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG="DatabaseHelper";

    static final String DATABASE_NAME="fitness.db";
    static final int DATABASE_VERSION=1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+LocalDatabaseContracts.TABLE_NAME+" ("+LocalDatabaseContracts._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +LocalDatabaseContracts.USER_ID+" TEXT NOT NULL, "+LocalDatabaseContracts.USER_PASSWORD+" TEXT NOT NULL, "
                +LocalDatabaseContracts.USER_DISTANCE+" TEXT NOT NULL, "+LocalDatabaseContracts.USER_ENTRYDATE+" TEXT NOT NULL);";
        Log.e(LOG_TAG,query);
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query="DROP TABLE IF EXISTS"+ LocalDatabaseContracts.TABLE_NAME+";";
        Log.e(LOG_TAG,query);
        db.execSQL(query);


    }
}
