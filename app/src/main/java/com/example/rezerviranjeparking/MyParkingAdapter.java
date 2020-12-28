package com.example.rezerviranjeparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyParkingAdapter extends RecyclerView.Adapter<MyParkingAdapter.viewHolder> {

    Context context;
    Activity activity;
    List<Parking> arrayList;
    DBHelper database_helper;
    Intent intent;
    String user,city,date,time,grad,parking;

    public MyParkingAdapter(Context context, Activity activity, List<Parking> arrayList,Intent intent) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
        this.intent=intent;
    }

    @Override
    public MyParkingAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item2, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyParkingAdapter.viewHolder holder, final int position) {
        database_helper=new DBHelper(context);
        Parking entry=arrayList.get(position);
        user=intent.getStringExtra("User");
        city=intent.getStringExtra("Grad");
        date=intent.getStringExtra("Datum");
        time = intent.getStringExtra("Vreme");
        grad=entry.getCityName();
        parking=entry.getParkingName();
        Float lat=entry.getLat();
        Float lon=entry.getLon();
        int TotalSpaces=database_helper.getTotalSpaces(parking);
        final int freeSpaces=TotalSpaces-database_helper.getNumberOfReservations(date,time,parking);
        int TakenPlaces=TotalSpaces-freeSpaces;
        String takenS=String.valueOf(TakenPlaces);
        String freeS=String.valueOf(freeSpaces);
        holder.zafateno.setText(takenS);
        holder.slobodno.setText(freeS);
        holder.parkingname.setText(parking);

       holder.button.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
                   Boolean insert = database_helper.insertReservation(user, grad, date, time, parking);
                   if (insert == true) {
                       Intent intent = new Intent(v.getContext(), ConfirmActivity.class);
                       intent.putExtra("Grad", grad);
                       intent.putExtra("Parking", parking);
                       intent.putExtra("Datum", date);
                       intent.putExtra("Vreme", time);
                       intent.putExtra("User", user);
                       intent.putExtra("Lat", lat);
                       intent.putExtra("Lon", lon);
                       activity.startActivityForResult(intent, 1);
                   } else {
                       Toast.makeText(context, "Не може да се резервира!", Toast.LENGTH_SHORT).show();
                   }
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
