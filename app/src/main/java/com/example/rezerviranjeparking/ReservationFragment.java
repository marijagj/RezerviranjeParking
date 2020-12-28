package com.example.rezerviranjeparking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReservationFragment extends Fragment{
    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;
    private Context mContext;
    String text;
    String vreme;
    public ReservationFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_reservation, container, false);

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getActivity().findViewById(R.id.text);
        Intent intent = getActivity().getIntent();
        String grad = intent.getStringExtra("Grad");
        String user =intent.getStringExtra("User");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Вие го избравте градот: "+grad);
        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = (EditText) getActivity().findViewById(R.id.datum);
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
                text = edittext.getEditableText().toString();
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), dialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Успешно резервирање: Датум: "+text+" Време: " + spinner.getSelectedItem(), Toast.LENGTH_SHORT);
                vreme= (String) spinner.getSelectedItem();
               // toast.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            TimeFragment frag = (TimeFragment) getFragmentManager().findFragmentById(R.id.fragment2);}
        /*Button button = (Button) getActivity().findViewById(R.id.button);
         button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_LANDSCAPE) {
                    TimeFragment frag = (TimeFragment) getFragmentManager().findFragmentById(R.id.fragment2);
                } else {
                    Intent intent=new Intent(v.getContext(),TimeFragment.class);
                    startActivityForResult(intent, REQUEST_CODE_DETAILS_ACTIVITY);

                }
            }
        });*/
        Button button=(Button) getActivity().findViewById(R.id.parkinzi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt= new Intent(v.getContext(),ParkingActivity.class);
                intentt.putExtra("User",user);
                intentt.putExtra("Datum",text);
                intentt.putExtra("Vreme",vreme);
                intentt.putExtra("Grad",grad);
                startActivityForResult(intentt,REQUEST_CODE_DETAILS_ACTIVITY);
            }
        });

    }}
