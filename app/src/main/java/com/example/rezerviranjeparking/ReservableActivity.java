package com.example.rezerviranjeparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ReservableActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper databaseHelper;
    ArrayList<Rezervacii> arrayList;
    String user,grad,parking,date,time;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervacii);
         intent=getIntent();
         user=intent.getStringExtra("User");
         grad=intent.getStringExtra("Grad");
         parking=intent.getStringExtra("Parking");
         date=intent.getStringExtra("Datum");
         time=intent.getStringExtra("Vreme");
        androidx.appcompat.widget.Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Резервации на "+ user );

        recyclerView = (RecyclerView) findViewById(R.id.list2);
        databaseHelper = new DBHelper(this);
        displayReservations();
    }
    private void displayReservations() {
        arrayList = new ArrayList<>(databaseHelper.getAllReservations(user));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MyReservationAdapter adapter = new MyReservationAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
    }
}