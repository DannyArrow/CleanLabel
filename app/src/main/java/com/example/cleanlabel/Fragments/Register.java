package com.example.cleanlabel.Fragments;


import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.KeyEventDispatcher;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

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


public class Register extends androidx.fragment.app.Fragment implements View.OnClickListener, OnProgressEventListener,View.OnTouchListener {
    View register, googlebtn,facebookbtn;
    TextInputEditText edit_email, edit_password, edit_name, edit_passwordconfirm;
    TextInputLayout namelayout_text, emailLayout_text, passwordLayout_text, confirmpasswordLayout_text;
    Firebaseauthimpl firebaseauthimpl;
    ProgressBar progressBar;

    ConstraintLayout linearLayout;
    ConstraintLayout constraintLayout;
    Validatepassword validatepassword;
    RelativeLayout rels;
    ConstraintLayout btm_lay;
    ScrollView scrollview;
    NavController navController;
    Toast toast;
    NavHostFragment navHostFragment;
    View view;
    LinearLayout linear;



    RelativeLayout relativelayout;
    RelativeLayout relativeLayout2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        relativelayout=view.findViewById(R.id.relativeLayout);
        rels = view.findViewById(R.id.rels);
        progressBar = view.findViewById(R.id.progressBar);
        edit_email =  view.findViewById(R.id.editemail);
        edit_password = view.findViewById(R.id.editpasseord);
        edit_name = view.findViewById(R.id.editname);
        edit_passwordconfirm = view.findViewById(R.id.editconfirmpass);
         btm_lay=view.findViewById(R.id.btm_lay);
        scrollview = view.findViewById(R.id.scrollview);
        googlebtn = view.findViewById(R.id.view2);
        facebookbtn = view.findViewById(R.id.view3);
        register = view.findViewById(R.id.view4);
        namelayout_text = view.findViewById(R.id.namelayout_text);
        emailLayout_text = view.findViewById(R.id.emailLayout_text);
        passwordLayout_text = view.findViewById(R.id.passwordlayout_text);
        confirmpasswordLayout_text = view.findViewById(R.id.confirmpasswordLayout_text);
        linear = view.findViewById(R.id.lin2);


        progressBar.setVisibility(View.INVISIBLE);
        googlebtn.setOnClickListener(this);
        facebookbtn.setOnClickListener(this);
        register.setOnClickListener(this);








        firebaseauthimpl = Firebaseauthimpl.getInstance(getActivity());
        firebaseauthimpl.register_listener(this);
        if(firebaseauthimpl.currently_signed_user()){
            Navigation.createNavigateOnClickListener(R.id.prefrence);
        }




//        LinearLayout.setOnClickListener(v -> {
//
//           // Toast_Message_Error("JIII");
//            //Log.i("testing","test........");
//
//        });

        relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t11= Toast.makeText(getActivity(),"relative clicked",Toast.LENGTH_SHORT);
                cancel_toast();


            }
        });

        rels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t11= Toast.makeText(getActivity(),"relative clicked",Toast.LENGTH_SHORT);
                cancel_toast();
            }
        });



        scrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t11= Toast.makeText(getActivity(),"relative clicked",Toast.LENGTH_SHORT);
               cancel_toast();
            }
        });

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t11= Toast.makeText(getActivity(),"relative clicked",Toast.LENGTH_SHORT);
                cancel_toast();
            }
        });





        rels.getViewTreeObserver().addOnGlobalLayoutListener(() -> {

            if(iskeyboardopen(rels.getRootView()))
              {

             Log.e("MyActivity", "keyboard opened");
             btm_lay.setVisibility(View.GONE);
            }
            else
            {
                Log.e("MyActivity", "keyboard closed");
                btm_lay.setVisibility(View.VISIBLE);
            }


        });

       edit_name.setOnFocusChangeListener((v, hasFocus) -> {
           if (hasFocus){
               final Handler handler;
               handler = new Handler();
               final Runnable r = new Runnable()
               {
                   public void run() {
                       scrollview.smoothScrollTo(0, edit_name.getBottom()+50);
               }    };    handler.postDelayed(r, 200);}

       });

       edit_email.setOnFocusChangeListener((v, hasFocus) -> {
           if (hasFocus){
               final Handler handler;
               handler = new Handler();
               final Runnable r = new Runnable()
               {        public void run() {
                   scrollview.smoothScrollTo(0, edit_email.getBottom()+50);
               }    };    handler.postDelayed(r, 200);}

       });

        edit_password.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                final Handler handler;
                handler = new Handler();
                final Runnable r = new Runnable()
                {        public void run()
                {            scrollview.smoothScrollTo(0, edit_password.getBottom()+50);        }    };
                handler.postDelayed(r, 200);}

        });

        edit_passwordconfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
            {
                final Handler handler;
                handler = new Handler();
                final Runnable r = new Runnable()
                {
                    public void run()
                    {
                        scrollview.smoothScrollTo(0, edit_passwordconfirm.getBottom()+50);

                    }
                };    handler.postDelayed(r, 200);
            }

        });




        validatepassword = new Validatepasswordimpl(edit_password,edit_passwordconfirm,passwordLayout_text,confirmpasswordLayout_text);


        edit_name.addTextChangedListener(new Validation(edit_name,this,namelayout_text));
        edit_email.addTextChangedListener(new Validation(edit_email,this,emailLayout_text));


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view2:
                cancel_toast();
                firebaseauthimpl.sign_in_google(); //sign into google
                show_progress();
                break;
            case R.id.view4:
               CheckFormValidation();
                break;

            case R.id.view3:
                cancel_toast();
                firebaseauthimpl.login_facebook();
                show_progress();
                break;
        }
        }




    private void CheckFormValidation() {
        cancel_toast();
        if (Validation.validateName()) { // name is not valid
            // is empty
            Toast_Message_Error("Please enter your name");
            return;
        }

        if (Validation.isValidEmail()) { // email is not valid
            Toast_Message_Error("Please enter a valid email");
            return;
        }

        if(!validatepassword.validate_password()){ // password is not valid
            Toast_Message_Error("please check passwords form");
            // password is not valid
            return;
        }

        show_progress();
        firebaseauthimpl.createUserWithEmailAndPassword(edit_email.getText().toString().trim(), edit_password.getText().toString().trim());
    }

    private boolean iskeyboardopen(View rootView)
    {
        final int SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();

        int heightDiff = rootView.getBottom() - r.bottom;

        boolean isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density;
        return isKeyboardShown;
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
        Log.i("toast",msg);
        toast = Toasty.error(getActivity(),msg, 20000);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        //cancel_toast();
    }

    private void cancel_toast(){
        if(toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    @Override
    public void sucessfully_login() {
        Navigation.findNavController(view).navigate(R.id.verify);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
