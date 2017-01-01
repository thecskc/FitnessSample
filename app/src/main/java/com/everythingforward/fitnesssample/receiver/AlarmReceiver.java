package com.everythingforward.fitnesssample.receiver;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.everythingforward.fitnesssample.R;
import com.everythingforward.fitnesssample.ui.MainActivity;


/**
 * Created by Santhoshkrishnachait on 10/19/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("AlarmReceiver", "Entered Broadcast Receiver");
       NotificationCompat.Builder myBuilder=  new NotificationCompat.Builder(context);
        myBuilder.setSmallIcon(R.drawable.notification_icon);
        myBuilder.setContentTitle("Fitness Sample");
        myBuilder.setContentText("Time To Walk!");

        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(MainActivity.NOTIFICATION1_ID,myBuilder.build());






    }
}
