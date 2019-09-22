package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class ZipCode extends androidx.fragment.app.Fragment {


    public ZipCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_zip_code, container, false);
        view.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_zipCode_to_prefrence));


        return view;

    }

}
