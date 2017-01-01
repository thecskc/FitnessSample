package com.everythingforward.fitnesssample.data;

import android.provider.BaseColumns;

/**
 * Created by Santhoshkrishnachait on 10/17/2016.
 */
public class LocalDatabaseContracts implements BaseColumns {
    public static final String TABLE_NAME="userinfo";
    public static final String USER_ID="userid";
    public static final String USER_PASSWORD="password";
    public static final String USER_DISTANCE="distance";
    public static final String USER_ENTRYDATE="entrydate";

    //Assumptions that will reflect throughout the app are that the userid and userpassword are always unique

}
