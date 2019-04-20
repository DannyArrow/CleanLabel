package com.example.cleanlabel.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class VerifyCode extends androidx.fragment.app.Fragment {


    public VerifyCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_code, container, false);
        view.findViewById(R.id.txtverify).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_verifyCode_to_zipCode));
        return view;
    }

}
