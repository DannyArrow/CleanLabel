package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class Verify extends androidx.fragment.app.Fragment {

TextView next;
    public Verify() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        next = view.findViewById(R.id.txtview);
        next.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_verify_to_verifyCode));
        return view;
    }

}
