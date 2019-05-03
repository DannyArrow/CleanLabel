package com.example.cleanlabel.Fragments;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;
import com.example.cleanlabel.Utility.Firebaseauthimpl;
import com.example.cleanlabel.Utility.OnProgressEventListener;
import com.example.cleanlabel.Utility.Validatepassword;
import com.example.cleanlabel.Utility.Validatepasswordimpl;
import com.example.cleanlabel.Utility.Validation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;


public class Register extends androidx.fragment.app.Fragment implements View.OnClickListener, OnProgressEventListener {
    View register, googlebtn,facebookbtn;
    TextInputEditText edit_email, edit_password, edit_name, edit_passwordconfirm;
    TextInputLayout namelayout_text, emailLayout_text, passwordLayout_text, confirmpasswordLayout_text;
    Firebaseauthimpl firebaseauthimpl;
    ProgressBar progressBar;
    ConstraintLayout linearLayout;
    ConstraintLayout constraintLayout;
    Validatepassword validatepassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        edit_email =  view.findViewById(R.id.editemail);
        edit_password = view.findViewById(R.id.editpasseord);
        edit_name = view.findViewById(R.id.editname);
        edit_passwordconfirm = view.findViewById(R.id.editconfirmpass);
        googlebtn = view.findViewById(R.id.view2);
        facebookbtn = view.findViewById(R.id.view3);
        register = view.findViewById(R.id.view4);
        namelayout_text = view.findViewById(R.id.namelayout_text);
        emailLayout_text = view.findViewById(R.id.emailLayout_text);
        passwordLayout_text = view.findViewById(R.id.passwordlayout_text);
        confirmpasswordLayout_text = view.findViewById(R.id.confirmpasswordLayout_text);
        progressBar.setVisibility(View.INVISIBLE);
        linearLayout = view.findViewById(R.id.linearLayout6);
        constraintLayout = view.findViewById(R.id.relativeLayout);
        googlebtn.setOnClickListener(this);
        facebookbtn.setOnClickListener(this);
        register.setOnClickListener(this);


        firebaseauthimpl = Firebaseauthimpl.getInstance(this);
        firebaseauthimpl.register_listener(this);
        if(firebaseauthimpl.currently_signed_user()){
            Navigation.createNavigateOnClickListener(R.id.prefrence);
        }

//        edit_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                                    ConstraintSet constraintSet = new ConstraintSet();
//
//                    //linearLayout.setVisibility(View.INVISIBLE);
//            }
//        });
//
//        edit_passwordconfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//
//                    confirmpasswordLayout_text.setPadding(0, 0, 0, 80);
//
//                }
//
//                if(!hasFocus){
//
//
//                }
//            }
//        });
        validatepassword = new Validatepasswordimpl(edit_password,edit_passwordconfirm,passwordLayout_text,confirmpasswordLayout_text);


        edit_name.addTextChangedListener(new Validation(edit_name,this,namelayout_text));
        edit_email.addTextChangedListener(new Validation(edit_email,this,emailLayout_text));


        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view2:
                firebaseauthimpl.configured_google_signin(); //sign into google
                show_progress();



                break;
            case R.id.view4:
                firebaseauthimpl.createUserWithEmailAndPassword(edit_email.getText().toString().trim(),edit_password.getText().toString().trim());
                show_progress();

                break;
        }

    }

    public void show_progress(){
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void dismiss_progress() {
        progressBar.setVisibility(View.INVISIBLE);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


    }

    @Override
    public void Toast_Message_Error(String msg) {
        Toasty.error(getContext(),msg,Toasty.LENGTH_LONG).setGravity(Gravity.CENTER, 0, 0);
    }


}
