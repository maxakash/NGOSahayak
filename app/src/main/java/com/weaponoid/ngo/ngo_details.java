package com.weaponoid.ngo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Random;

public class ngo_details extends AppCompatActivity {

    TextView name,email,phone,address;

    String ngoemail,objectid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_details);

        name=findViewById(R.id.aname);
        email=findViewById(R.id.aemail);
        phone=findViewById(R.id.aphone);
        address=findViewById(R.id.aaddress);

        Intent intent = getIntent();

        name.setText("NGO Name - " + intent.getStringExtra("name"));
        email.setText("Email - " + intent.getStringExtra("email"));
        phone.setText("Phone - " + intent.getStringExtra("phone"));
        address.setText("Address - " + intent.getStringExtra("address"));

        ngoemail=intent.getStringExtra("email");
        objectid= intent.getStringExtra("objectid");






    }

    public void verify(View view){

        final String password ="1234@ngosahayak";
        String body= "Your account has been verified successfully.\n Your login details are \n username - " + ngoemail + "\npassword - " + password ;



        BackgroundMail.newBuilder(this)
                .withUsername("weaponoid7799")
                .withPassword("Inspiron15@")
                .withMailto(ngoemail)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Account verification successful")
                .withBody(body)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //Log.i("message sent","successfully");
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                    }
                })
                // .withProcessVisibility(false)
                .send();


        ParseUser user = new ParseUser();
        user.setUsername(ngoemail);
        user.setPassword(password);

        System.out.println("reached here");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    System.out.println("userregistered");



                    ParseQuery<ParseObject> query = ParseQuery.getQuery("NGO_registrations");
                    query.getInBackground(objectid, new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.put("status","verified");
                                object.saveInBackground();

                            } else {
                                // something went wrong
                            }
                        }
                    });


                }

                else{
                    e.printStackTrace();
                }
            }
        });








    }

   public String geek_Password(int len)
    {
        System.out.println("Generating password using random() : ");
        System.out.print("Your new password is : ");

        // A strong password has Cap_chars, Lower_chars,
        // numeric value and symbols. So we are using all of
        // them to generate our password
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";



        String values = Capital_chars + Small_chars +
                numbers ;

        // Using random method
        Random rndm_method = new Random();

        char[] password = new char[len];

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            password[i] =
                    values.charAt(rndm_method.nextInt(values.length()));

        }
        return password.toString();
    }




}
