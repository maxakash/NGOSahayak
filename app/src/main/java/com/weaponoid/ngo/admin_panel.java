package com.weaponoid.ngo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;

public class admin_panel extends AppCompatActivity {
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        sharedPreferences =getSharedPreferences("com.weaponoid.ngoapp",MODE_PRIVATE);
    }


    public void verify(View view){
        startActivity(new Intent(getApplicationContext(),verify_ngo.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);


        ParseUser.logOut();
        sharedPreferences.edit().putBoolean("loggedin",false).apply();
        startActivity(new Intent(this,MainActivity.class));

        return true;





    }
}
