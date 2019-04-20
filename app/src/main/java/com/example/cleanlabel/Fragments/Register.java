package com.example.cleanlabel.Fragments;


import android.os.Bundle;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import android.widget.Button;
import android.widget.ImageView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;



public class Register extends androidx.fragment.app.Fragment {
ImageView register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ToolbarManager toolbarManager = ToolbarManager.getInstance();
//        toolbarManager.setToolBarGone();
        View view = inflater.inflate(R.layout.fragment_register, container, false);
       register = view.findViewById(R.id.view4);
         register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_register_to_verify));
        return view;
    }


}
