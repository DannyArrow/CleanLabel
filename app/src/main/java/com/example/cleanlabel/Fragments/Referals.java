package com.example.cleanlabel.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class Referals extends androidx.fragment.app.Fragment {


    public Referals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ToolbarManager toolbarManager = ToolbarManager.getInstance();
//        toolbarManager.setTitle("Referals");
//        toolbarManager.show_backbutton();
        return inflater.inflate(R.layout.fragment_referals, container, false);
    }

}
