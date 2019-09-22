package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class Mainpage extends androidx.fragment.app.Fragment implements View.OnClickListener {
    private RelativeLayout invite_friends;
    private RelativeLayout frequency;
    private RelativeLayout cleanwashbasic;

    public Mainpage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.order_activity, container, false);
        invite_friends = view.findViewById(R.id.invite_friends);
        frequency = view.findViewById(R.id.frequency);
        cleanwashbasic = view.findViewById(R.id.wash_basic);

        invite_friends.setOnClickListener(this);
        frequency.setOnClickListener(this);
        cleanwashbasic.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.invite_friends:
                Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment).navigate(R.id.referals);
                break;


            case R.id.frequency:
                Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment).navigate(R.id.Frequency);

                break;

            case R.id.wash_basic:
                Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment).navigate(R.id.subscription);
                break;
        }

    }
}
