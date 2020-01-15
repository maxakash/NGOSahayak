package com.weaponoid.ngo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class event_request extends AppCompatActivity {

    EditText name,venue,date,timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_request);
        name= findViewById(R.id.ename);
        venue=findViewById(R.id.venue);
        date=findViewById(R.id.date);
        timing = findViewById(R.id.timing);
    }

    public void request(View view){


        try{
            if(name.getText().toString().equals("") || venue.getText().toString().equals("") || timing.getText().toString().equals("") || date.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            else{

                ParseObject parseObject = new ParseObject("Event_permission");
                parseObject.put("NGO_name", name.getText().toString());
                parseObject.put("venue",venue.getText().toString());
                parseObject.put("date",date.getText().toString());
                parseObject.put("timings", timing.getText().toString());
                parseObject.put("status","ungranted");




                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        // e.printStackTrace();

                        if (e == null) {
                            Toast.makeText(getApplicationContext(),"Permission Requested.",Toast.LENGTH_LONG).show();
                            finish();

                            Log.i("saved", "s");
                        }
                        else{

                            e.printStackTrace();


                        }
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
