package com.example.rezerviranjeparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyReservationAdapter extends RecyclerView.Adapter<MyReservationAdapter.viewHolder>  {
    Context context;
    Activity activity;
    List<Rezervacii> arrayList;
    DBHelper database_helper;
    //Intent intent;

    public MyReservationAdapter(Context context, Activity activity, List<Rezervacii> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
        //this.intent = intent;
    }

    @Override
    public MyReservationAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item3, parent, false);
        return new MyReservationAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReservationAdapter.viewHolder holder, int position) {
        Rezervacii entry=arrayList.get(position);
        holder.date.setText(entry.getDate());
        holder.time.setText(entry.getTime());
        holder.grad.setText(entry.getCityName());
        holder.parking.setText(entry.getParkingName());
        holder.mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float lat=entry.getLat();
                Float lon=entry.getLon();
                Intent intent =new Intent(v.getContext(),MapsActivity.class);
                intent.putExtra("Lat",lat);
                intent.putExtra("Lon",lon);
                activity.startActivityForResult(intent,1);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView date;
        Button time;
        Button parking;
        TextView grad;
        Button mapa;
        public viewHolder(View itemView) {
            super(itemView);
            time=(Button) itemView.findViewById(R.id.zafateni1);
            parking=(Button) itemView.findViewById(R.id.slobodni1);
            date= itemView.findViewById(R.id.daterez);
            grad=(TextView) itemView.findViewById(R.id.proba);
            mapa=(Button) itemView.findViewById(R.id.mapa);
        }
    }
}
