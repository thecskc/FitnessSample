package com.everythingforward.fitnesssample.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.URI;

/**
 * Created by Santhoshkrishnachait on 10/17/2016.
 */
public class DatabseProvider extends ContentProvider {

    SQLiteDatabase database;
    public static final String PROVIDER_AUTHORITY="com.everythingforward.fitnesssample";
    public static final String CONTENT_URISTRING="content://"+PROVIDER_AUTHORITY+"/"+LocalDatabaseContracts.TABLE_NAME;
    public static  final Uri CONTENT_URI= Uri.parse(CONTENT_URISTRING);

    public static final int USERNAMES=1;

    public static final int USERS_DETAILS=2;



    static UriMatcher uriMatcher;
    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_AUTHORITY,LocalDatabaseContracts.TABLE_NAME+"/#",USERNAMES);
        uriMatcher.addURI(PROVIDER_AUTHORITY,LocalDatabaseContracts.TABLE_NAME+"/*",USERS_DETAILS);


    }


    @Override
    public boolean onCreate() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        database = databaseHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        switch(uriMatcher.match(uri))
        {
            case USERNAMES:
            {
                Log.e("DatabaseProvider","USER ID AND PASSWORDS QUERIED");
               return database.query(LocalDatabaseContracts.TABLE_NAME,new String[]{LocalDatabaseContracts.USER_ID,LocalDatabaseContracts.USER_PASSWORD}
                        ,null,null,null,null,null);


            }
            default:
            {
                String selectionClause=LocalDatabaseContracts.USER_ID+"="+"'"+String.valueOf(selection)+"'";
               return database.query(LocalDatabaseContracts.TABLE_NAME,
                        new String[]{LocalDatabaseContracts.USER_ID,
                                LocalDatabaseContracts.USER_DISTANCE,LocalDatabaseContracts.USER_ENTRYDATE},
                        selectionClause,null,null,null,null);


                

                //Queries all rows for that specific user

            }

        }





    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long insertID=database.insert(LocalDatabaseContracts.TABLE_NAME,null,values);
        Log.e(this.getClass().getSimpleName(), "Record Inserted " + insertID);
        getContext().getContentResolver().notifyChange(uri,null);


        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //Assuming user doesn't delete entries

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        //Assuming user doesn't have to update entries
        return 0;
    }
}
