package com.example.rezerviranjeparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTownAdapter extends RecyclerView.Adapter<MyTownAdapter.viewHolder> {

    Context context;
    Activity activity;
    ArrayList<City> arrayList;
    DBHelper database_helper;

    public MyTownAdapter(Context context, Activity activity, ArrayList<City> arrayList) {
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
    }


    @Override
    public MyTownAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyTownAdapter.viewHolder holder, final int position) {
        holder.cname.setText(arrayList.get(position).getCityName());
        String grad=arrayList.get(position).getCityName();
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ReservationActivity.class);
                intent.putExtra("Grad",grad);
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView cname;
        RelativeLayout mainLayout;
        public viewHolder(View view) {
            super(view);
         cname = (TextView) view.findViewById(R.id.textName);
         mainLayout=(RelativeLayout) view.findViewById(R.id.rlayout);
        }
    }
}
