package com.example.rezerviranjeparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ArrayList<City> arrayList;
    RecyclerView recyclerView;
    DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        androidx.appcompat.widget.Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Листа од градови");

        recyclerView = (RecyclerView) findViewById(R.id.list);
        databaseHelper=new DBHelper(this);

            databaseHelper.addCities("Велес");
            databaseHelper.addCities("Скопје");
            databaseHelper.addCities("Куманово");
            databaseHelper.addCities("Валандово");
            databaseHelper.addCities("Прилеп");
            displayCities();

    }


    private void displayCities() {
        arrayList = new ArrayList<>(databaseHelper.getCities());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MyTownAdapter adapter = new MyTownAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
    }

}