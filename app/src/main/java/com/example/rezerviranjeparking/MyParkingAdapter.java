package com.example.rezerviranjeparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyParkingAdapter extends RecyclerView.Adapter<MyParkingAdapter.viewHolder> {

    Context context;
    Activity activity;
    List<Parking> arrayList;
    DBHelper database_helper;
    String username,city,date,time;

    public MyParkingAdapter(Context context, Activity activity, List<Parking> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
    }


    @Override
    public MyParkingAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item2, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyParkingAdapter.viewHolder holder, final int position) {
        Parking entry=arrayList.get(position);
        holder.parkingname.setText(entry.getParkingName());
        holder.zafateno.setText(entry.getTaken());
        holder.slobodno.setText(entry.getFree());
        String grad=entry.getCityName();
        String parking=entry.getParkingName();
       holder.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(v.getContext(),ConfirmActivity.class);
               intent.putExtra("Grad",grad);
               intent.putExtra("Parking",parking);
               activity.startActivityForResult(intent,1);
               database_helper.insertReservationDetails(null,city,date,time,entry.getParkingName());
           }
       });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        Button zafateno;
        Button slobodno;
        TextView parkingname;
        Button button;

        public viewHolder(View view) {
            super(view);
             parkingname = (TextView) view.findViewById(R.id.parkingname);
             zafateno= (Button) view.findViewById(R.id.zafateni);
             slobodno=(Button) view.findViewById(R.id.slobodni);
             button=(Button) view.findViewById(R.id.rezerviraj);
        }
    }
}
