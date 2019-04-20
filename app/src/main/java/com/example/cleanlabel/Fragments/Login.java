package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.cleanlabel.R;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class Login extends androidx.fragment.app.Fragment {


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
//        toolbar.setVisibility(View.VISIBLE);

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
