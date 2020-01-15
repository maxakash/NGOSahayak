package com.weaponoid.ngo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class register extends AppCompatActivity {

    EditText name,email,phone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name= findViewById(R.id.ngoname);
        email= findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address= findViewById(R.id.address);
    }



    public void register(View view){


        try{
            if(name.getText().toString().equals("") || email.getText().toString().equals("") || phone.getText().toString().equals("") || address.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            else{

                ParseObject parseObject = new ParseObject("NGO_registrations");
            parseObject.put("NGO_name", name.getText().toString());
            parseObject.put("phone", phone.getText().toString());
            parseObject.put("email",email.getText().toString());
            parseObject.put("address", address.getText().toString());
            parseObject.put("status","unverified");



            parseObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    // e.printStackTrace();

                    if (e == null) {
                        Toast.makeText(getApplicationContext(),"Successfully registered. You will receive a email with login details",Toast.LENGTH_LONG).show();

                        Log.i("saved", "s");
                        finish();
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

    public void login(View view){
        finish();

    }
}
