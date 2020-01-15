package com.weaponoid.ngo;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;


public class RecyclerviewAdapter extends  RecyclerView.Adapter<RecyclerviewAdapter.Viewholder>  {



    List<ParseObject> objects ;


    private Context context;



    public RecyclerviewAdapter(Context Context, List<ParseObject> object) {
        this.context=Context;
        this.objects=object;
    }


    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {


       holder.ngo_name.setText(objects.get(position).get("NGO_name").toString());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

              Intent intent = new Intent(view.getContext(),ngo_details.class);
               intent.putExtra("name",objects.get(position).get("NGO_name").toString());
                intent.putExtra("email",objects.get(position).get("email").toString());
                intent.putExtra("phone",objects.get(position).get("phone").toString());
                intent.putExtra("address",objects.get(position).get("address").toString());
                intent.putExtra("objectid",objects.get(position).getObjectId());

               view.getContext().startActivity(intent);













            }
        });



    }










    @Override
    public int getItemCount() {

        return objects.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{


       TextView ngo_name;







        public Viewholder(@NonNull View itemView) {
            super(itemView);


         ngo_name= itemView.findViewById(R.id.ngo_name);



        }

    }
}

