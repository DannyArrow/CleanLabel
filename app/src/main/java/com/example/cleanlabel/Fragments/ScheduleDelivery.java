package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class ScheduleDelivery extends androidx.fragment.app.Fragment {


    public ScheduleDelivery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_delivery, container, false);
        view.findViewById(R.id.viewnext).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_scheduleDelivery_to_orderSummary));


//        ToolbarManager toolbarManager = ToolbarManager.getInstance();
//        toolbarManager.setTitle("Schedule Delivery");
//        toolbarManager.show_backbutton();

        return view;
    }

}
