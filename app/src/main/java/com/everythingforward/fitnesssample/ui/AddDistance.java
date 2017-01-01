package com.everythingforward.fitnesssample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.everythingforward.fitnesssample.R;
import com.everythingforward.fitnesssample.Utility;

public class AddDistance extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_distance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String distanceTraveled = editText.getText().toString();
                Utility.EnterValuesInDB(getApplicationContext(),Utility.CURRENTUSER_USERID,Utility.CURRENTUSER_PASSWORD,
                        distanceTraveled,Utility.getTimeStamp());
                Intent intent = new Intent(AddDistance.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }


}
