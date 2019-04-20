package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import android.view.KeyEvent;
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
public class Mainpage extends androidx.fragment.app.Fragment {


    public Mainpage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainpage, container, false);
        view.findViewById(R.id.Referal_Button).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainpage_to_referals));
        view.findViewById(R.id.OrderHistory_button).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainpage_to_order_History));
        view.findViewById(R.id.Account_Setting_Button).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainpage_to_accountsetting));
        view.findViewById(R.id.Contact_button).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainpage_to_contact_us));


//        ToolbarManager toolbarManager = ToolbarManager.getInstance();
//        toolbarManager.setTitle("Clean Label");
//        toolbarManager.hide_backbutton();

        disable_back_button(view);

        return view;

    }

    public void disable_back_button(View view){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        return true;
                    }
                }
                return false;
            }
        });
    }

}
