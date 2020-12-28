package com.example.rezerviranjeparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ArrayList<City> arrayList;
    RecyclerView recyclerView;
    DBHelper databaseHelper;
    Intent intent;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent=getIntent();
        user = intent.getStringExtra("User");
        androidx.appcompat.widget.Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Листа од градови");

        recyclerView = (RecyclerView) findViewById(R.id.list);
        databaseHelper=new DBHelper(this);

            databaseHelper.addCities("Велес");
            databaseHelper.addCities("Скопје");
            databaseHelper.addCities("Охрид");
            databaseHelper.addCities("Кавадарци");
            databaseHelper.addCities("Прилеп");
            displayCities();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId())
        {
            case R.id.action_favorite:
                intent = new Intent(this, ReservableActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayCities() {
        arrayList = new ArrayList<>(databaseHelper.getCities());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MyTownAdapter adapter = new MyTownAdapter(getApplicationContext(), this, arrayList,intent);
        recyclerView.setAdapter(adapter);
    }

}