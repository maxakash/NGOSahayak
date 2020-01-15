package com.weaponoid.ngo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {


    EditText username;
    EditText password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeparse();
        sharedPreferences =getSharedPreferences("com.weaponoid.ngoapp",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("loggedin",false)){


            if(ParseUser.getCurrentUser().getUsername().equals("ngosahayak")){
                startActivity(new Intent(getApplicationContext(),admin_panel.class));
            }

            else{
                startActivity(new Intent(getApplicationContext(),ngo_panel.class));
            }



        }

        else{
            setContentView(R.layout.activity_main);
        }




        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }




    public void initializeparse() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("959131c8426635b5c27cc1a46c0604237d689b09")
                .clientKey("2d939b881ec8ec6920f3a806e15add7a09a44b1a")
                .server("http://18.225.10.69:80/parse")
                .build()
        );

    }



    public void login(View view){

       ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
           @Override
           public void done(ParseUser user, ParseException e) {

               if(e!=null){
                   Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_SHORT).show();
                   ParseUser currentUser = ParseUser.getCurrentUser();
                   if (currentUser.getUsername().equals("ngosahayak")) {

                       sharedPreferences.edit().putBoolean("loggedin",true).apply();

                       startActivity(new Intent(getApplicationContext(),admin_panel.class));

                       // do stuff with the user
                   } else {
                       sharedPreferences.edit().putBoolean("loggedin",true).apply();
                       startActivity(new Intent(getApplicationContext(),ngo_panel.class));
                   }


               }

           }
       });

    }

    public void register(View view){


        Intent intent = new Intent(getApplicationContext(),register.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            System.exit(1);
        }

    }
}
