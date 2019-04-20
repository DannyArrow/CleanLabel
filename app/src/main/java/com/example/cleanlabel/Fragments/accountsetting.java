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
public class accountsetting extends androidx.fragment.app.Fragment {


    public accountsetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ToolbarManager toolbarManager = ToolbarManager.getInstance();
//        toolbarManager.setTitle("Account Setting");
//        toolbarManager.show_backbutton();
        return inflater.inflate(R.layout.fragment_accountsetting, container, false);

    }

}
