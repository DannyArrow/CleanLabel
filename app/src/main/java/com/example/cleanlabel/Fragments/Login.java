package com.example.cleanlabel.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import com.example.cleanlabel.R;
import com.example.cleanlabel.Utility.Firebaseauthimpl;
import com.example.cleanlabel.Utility.OnProgressEventListener;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class Login extends androidx.fragment.app.Fragment implements OnProgressEventListener {

private View loginbtn;
private EditText email;
private EditText password;
private ProgressBar progressBar;
private View facebookbtn;
private View googlebtn;
private ConstraintLayout linearLayout;
    private Toast toast;
    private View view;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        loginbtn = view.findViewById(R.id.view4);
        email = view.findViewById(R.id.editemail);
        password = view.findViewById(R.id.editpassword);
        progressBar = view.findViewById(R.id.progressBar2);
        facebookbtn = view.findViewById(R.id.view3);
        googlebtn = view.findViewById(R.id.view2);
        linearLayout = view.findViewById(R.id.linearLayout7);
        Firebaseauthimpl firebaseAuth = Firebaseauthimpl.getInstance(getActivity());
        firebaseAuth.register_listener(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_toast();
                show_progress();
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString());
            }
        });

        facebookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_toast();
                show_progress();
                firebaseAuth.login_facebook();
            }
        });

        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_toast();
                show_progress();
                firebaseAuth.sign_in_google();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            go_to_register_screen();
            }
        });


        return view;

    }

    private void cancel_toast(){
        if(toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    public void freeze_ui() {
        final Handler handler;
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        },30000);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    private void show_progress(){
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
        toast = Toasty.error(getContext(),msg, 1000);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
       // freeze_ui();

    }

    @Override
    public void sucessfully_login() {

    }

    private void go_to_register_screen(){
        Navigation.findNavController(view).navigate(R.id.registerbtn);

    }
}
