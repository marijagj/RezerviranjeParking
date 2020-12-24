package com.example.rezerviranjeparking;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TimeFragment extends Fragment {
    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 2468;
    public TimeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView=(TextView)getActivity().findViewById(R.id.text2) ;
        //if (getResources().getConfiguration().orientation ==
                //onfiguration.ORIENTATION_LANDSCAPE) {
           //ReservationFragment frag = (ReservationFragment) getFragmentManager().findFragmentById(R.id.fragment1);
        //} else {
            Intent intent=getActivity().getIntent();
        ImageView imageView=(ImageView)getActivity().findViewById(R.id.image) ;
        imageView.setImageResource(R.drawable.thankyou);
}}