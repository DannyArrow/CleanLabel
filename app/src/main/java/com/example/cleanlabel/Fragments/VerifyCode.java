package com.example.cleanlabel.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;
import com.goodiebag.pinview.Pinview;

import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class VerifyCode extends androidx.fragment.app.Fragment {
    Pinview enterphonetxt;


    public VerifyCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_code, container, false);
        enterphonetxt = view.findViewById(R.id.pinview);
        focus_on_textview();
        view.findViewById(R.id.txtverify).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_verifyCode_to_zipCode));
        return view;
    }

    private void focus_on_textview() {
        enterphonetxt.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


}
