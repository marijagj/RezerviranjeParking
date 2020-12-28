package com.example.rezerviranjeparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Intent intent = getIntent();
        String parking = intent.getStringExtra("Parking");
        String grad = intent.getStringExtra("Grad");
        String user=intent.getStringExtra("User");
        String date=intent.getStringExtra("Datum");
        String time=intent.getStringExtra("Vreme");
        Float lat=intent.getFloatExtra("Lat",0);
        Float lon=intent.getFloatExtra("Lon",0);
        String Lat= lat.toString();
        String Lon= lon.toString();
        Button button=(Button) findViewById(R.id.moirezervacii);
        button.setText(parking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(v.getContext(),ReservableActivity.class);
                intent1.putExtra("User",user);
                intent1.putExtra("Parking",parking);
                intent1.putExtra("Grad",grad);
                intent1.putExtra("Datum",date);
                intent1.putExtra("Vreme",time);
                startActivity(intent1);
            }
        });
        Button button1=(Button)findViewById(R.id.nav);
        //String location="google.navigate:q=41.7096937798,21.760735385";
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(v.getContext(),MapsActivity.class);
                intent1.putExtra("Lat",lat);
                intent1.putExtra("Lon",lon);
                intent1.putExtra("Grad",parking);
                startActivity(intent1);

            }
        });
        /*Button button2=(Button) findViewById(R.id.nazad);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(v.getContext(),HomeActivity.class);
                startActivity(intent2);
            }
        });*/
    }
}