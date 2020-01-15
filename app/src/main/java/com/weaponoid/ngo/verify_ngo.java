package com.weaponoid.ngo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class verify_ngo extends AppCompatActivity {

    private RecyclerView recyclerview;
    private RecyclerviewAdapter allDataAdapter;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_ngo);
        recyclerview = findViewById(R.id.recyclerview);
        getngolist();


        builder= new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.loader);
        alertDialog= builder.create();
        alertDialog.show();


    }



    public void getngolist() {

        LinearLayoutManager mLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL,false);

        recyclerview.setLayoutManager(mLayoutManager);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("NGO_registrations");
        query.whereEqualTo("status", "unverified");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null){


                    allDataAdapter = new RecyclerviewAdapter(getApplicationContext(),objects);
                    recyclerview.setAdapter(allDataAdapter);
                    if(alertDialog.isShowing()){
                        alertDialog.dismiss();
                    }


                }
                else {
                    e.printStackTrace();
                }

            }
        });



    }
}
