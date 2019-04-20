package com.example.cleanlabel.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class Prefrence extends Fragment {


    public Prefrence() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prefrence, container, false);
        view.findViewById(R.id.viewnext).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_prefrence_to_scheduleDelivery));



        return view;
    }

}
