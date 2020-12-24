package com.example.rezerviranjeparking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

public class ParkingActivity extends AppCompatActivity {
    ArrayList<Parking> arrayList;
    RecyclerView recyclerView;
    DBHelper databaseHelper;
    String city,date;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Intent intent = getIntent();
        city=intent.getStringExtra("Grad");
        date=intent.getStringExtra("Datum");
        String time = intent.getStringExtra("Vreme");
        androidx.appcompat.widget.Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Паркинзи во градот " + city);
        recyclerView = (RecyclerView) findViewById(R.id.list1);
        databaseHelper = new DBHelper(this);
        displayParking();
    }


    private void displayParking() {
        arrayList = new ArrayList<>(databaseHelper.getAllParkingPlaces(city));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MyParkingAdapter adapter = new MyParkingAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
    }
}