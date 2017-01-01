package com.everythingforward.fitnesssample.ui;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.everythingforward.fitnesssample.R;
import com.everythingforward.fitnesssample.Utility;
import com.everythingforward.fitnesssample.data.DatabseProvider;
import com.everythingforward.fitnesssample.receiver.AlarmReceiver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    TextView textView;
    ToggleButton toggleButton;
    AlarmManager alarmManager=null;
    PendingIntent pendingIntent=null;
    TextView textView2;

    public static final int NOTIFICATION1_ID=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(MainActivity.this, AddDistance.class);
                startActivity(inten);


            }
        });

        listView = (ListView)findViewById(R.id.listView);
                arrayList = new ArrayList<String>();
        textView = (TextView)findViewById(R.id.textView2);
        textView.setVisibility(View.GONE);
        textView2=(TextView)findViewById(R.id.textView3);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"Turned On",Toast.LENGTH_SHORT).show();
                    //Handle Alarm Manager and make it fire every hour
                    Intent receiverIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                     pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),NOTIFICATION1_ID,receiverIntent,0);

                   alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,AlarmManager.ELAPSED_REALTIME_WAKEUP,AlarmManager.INTERVAL_HOUR,pendingIntent);




                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Turned Off",Toast.LENGTH_SHORT).show();
                    //Cancel the Alarm Manager
                    if(alarmManager!=null)
                    {
                        alarmManager.cancel(pendingIntent);
                        Log.e("MainActivity","Alarm Manager Cancelled");


                    }

                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        populateArrayList(arrayList);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listitem,R.id.listitemvalue,arrayList);


    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(),"Session Over",Toast.LENGTH_SHORT).show();

    }

    void populateArrayList(ArrayList<String> arrayList)
    {
        arrayList.clear();
        double totalDistance=0;
        Cursor cursor = getContentResolver().query(DatabseProvider.CONTENT_URI,null, Utility.CURRENTUSER_USERID,null,null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                while(cursor.moveToNext())
                {
                    String distanceTraveled = cursor.getString(1);
                    totalDistance+=Double.valueOf(distanceTraveled);
                    String totalDistanceText = "Total Distance Traveled - "+String.valueOf(totalDistance);
                    textView2.setTextColor(Color.RED);
                    textView2.setText(totalDistanceText);

                    if(totalDistance%1000==0)
                    {
                        textView.setText("MILESTONE - "+String.valueOf(totalDistance));
                        textView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        textView.setVisibility(View.GONE);
                    }
                    String putThis = Utility.CURRENTUSER_USERID+": "+distanceTraveled+": "+cursor.getString(2);
                    arrayList.add(putThis);

                }
            }
        }
        cursor.close();
    }



}


