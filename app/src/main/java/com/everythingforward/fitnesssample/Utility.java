package com.everythingforward.fitnesssample;

import android.content.ContentValues;
import android.content.Context;

import com.everythingforward.fitnesssample.data.DatabseProvider;
import com.everythingforward.fitnesssample.data.LocalDatabaseContracts;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Santhoshkrishnachait on 10/19/2016.
 */
public class Utility {

    public static String CURRENTUSER_USERID;
    public static String CURRENTUSER_PASSWORD;

    public static void EnterValuesInDB(Context context,String userid,String userpassword, String distance, String timestamp)
    {
        ContentValues values = new ContentValues();
        values.put(LocalDatabaseContracts.USER_ENTRYDATE,timestamp);
        values.put(LocalDatabaseContracts.USER_ID,userid);
        values.put(LocalDatabaseContracts.USER_PASSWORD,userpassword);
        values.put(LocalDatabaseContracts.USER_DISTANCE,distance);
        context.getContentResolver().insert(DatabseProvider.CONTENT_URI,values);


    }

    public static String getTimeStamp()
    {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

}
