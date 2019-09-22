package com.example.cleanlabel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.*;

import android.util.*;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavDestination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanlabel.Fragments.BlankFragment;
import com.example.cleanlabel.Utility.Firebaseauth;
import com.example.cleanlabel.Utility.Firebaseauthimpl;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public  Menu menu;
    private MenuItem menuItem;
    TextView txt;
    private Boolean go_to_onboarding_fragment;
    private Toolbar toolbar, toolbar2, toolbar3, toolbar4;
    private DrawerLayout mDrawerLayout;
    private NavigationView navview;
    private ImageView close;


    //private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // com.google.samples.smartlock.sms_verify.AppSignatureHelper appSignatureHelper = new com.google.samples.smartlock.sms_verify.AppSignatureHelper(this);
       // Log.i("google_hash_code", String.valueOf(appSignatureHelper.getAppSignatures()));

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);



      toolbar = findViewById(R.id.toolbar_one);
      toolbar2 = findViewById(R.id.toolbar_two);
      toolbar3 = findViewById(R.id.toolbar_three);
      toolbar4 = findViewById(R.id.toolbar_four);
     setSupportActionBar(toolbar);
     setSupportActionBar(toolbar2);
     setSupportActionBar(toolbar3);
     setSupportActionBar(toolbar4);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

      //getSupportActionBar().setCustomView(R.layout.toolbar_one);









        NavController navController = Navigation.findNavController(this,R.id.my_nav_host_fragment);
        navController.addOnNavigatedListener(new NavController.OnNavigatedListener() {
            @Override
            public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {
                go_to_onboarding_fragment = false;
                enableBackFunctionality();
                TextView txt;
                ImageView backarrow;
                ImageView logo  = toolbar2.findViewById(R.id.linearLayout6);
                switch (destination.getId()){
                    case R.id.blankFragment:
                        setToolBars_To_Gone();
                        toolbar.setVisibility(View.VISIBLE);
                        break;

                    case R.id.zipCode:
                      setToolBars_To_Gone();
                      toolbar3.setVisibility(View.VISIBLE);
                      set_title("Clean Progress");
                        break;

                    case R.id.registerbtn:
                        setToolBars_To_Gone();
                        toolbar2.setVisibility(View.VISIBLE);
                        logo = toolbar2.findViewById(R.id.linearLayout6);
                        logo.setVisibility(View.GONE);
                        go_to_onboarding_fragment = true;
                        break;

                    case R.id.login:
                        setToolBars_To_Gone();
                        toolbar2.setVisibility(View.VISIBLE);
                        logo.setVisibility(View.GONE);
                        go_to_onboarding_fragment = true;
                        break;

                    case R.id.verify:
                        setToolBars_To_Gone();
                        toolbar2.setVisibility(View.VISIBLE);
                        logo.setVisibility(View.VISIBLE);
                        setBackArrow_To_Gone();


                        break;

                    case R.id.verifyCode:
                        setToolBars_To_Gone();
                        toolbar2.setVisibility(View.VISIBLE);
                        setBackArrow_To_Visible();
                        break;

                    case R.id.prefrence:
                        setToolBars_To_Gone();
                        toolbar3.setVisibility(View.VISIBLE);
                        setBackArrow_To_Gone();
                        set_title("Prefrences");
                        break;

                    case R.id.scheduleDelivery:
                        setBackArrow_To_Visible();
                        set_title("Schedule Delivery");
                       // toolbarManager.set_title("Schedule Delivery");
                        break;

                    case R.id.orderSummary:
                       set_title("Order Summary");
                        break;

                    case R.id.payment:
                      set_title("Payment");
                        break;

                    case R.id.mainpage:
                       setToolBars_To_Gone();
                       toolbar4.setVisibility(View.VISIBLE);
                       set_recylcerview();
                       set_navigation_drawer();
                        break;

                    case R.id.referals:
                      set_title("Refer and earn");
                        break;

                    case R.id.frequency:
                      set_title("Frequency");
                        break;

                    case R.id.subscription:
                      set_title("Subscribe");
                        break;



                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == close.getId())
        {
            DrawerLayout drawer = findViewById(R.id.drawerlayout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
    }

    public void set_navigation_drawer(){
        navview =  findViewById(R.id.navview);
        mDrawerLayout =  findViewById(R.id.drawerlayout);
        close = navview.findViewById(R.id.close);
        close.setOnClickListener(this);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar4, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }


    public void set_recylcerview(){
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        List<String> nav_item = getdrawermenu();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, nav_item);
        list.setAdapter(recyclerAdapter);
    }




    public void enableBackFunctionality(){
        ImageView backarrow;
        ImageView backarrow_two;

            backarrow = toolbar2.findViewById(R.id.imageView4);
            backarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(MainActivity.this,R.id.my_nav_host_fragment).navigateUp();
                }
            });

//            backarrow_two = toolbar3.findViewById(R.id.imageView4);
//            backarrow_two.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("clicked", "backclicked");
//                  Navigation.findNavController(MainActivity.this,R.id.my_nav_host_fragment).navigateUp();
//
//                }
//            });

    }


    public void hide_menu(){
        ImageView imageView = toolbar3.findViewById(R.id.imageView13);
        imageView.setVisibility(View.GONE);
    }

    public void set_title(String title){

        if(txt == null) {
            txt = toolbar3.findViewById(R.id.title);
        }
        txt.setText(title);
    }

    public void setBackArrow_To_Gone(){
        ImageView backarrow;
        if(toolbar2.getVisibility() == View.VISIBLE){
            backarrow = toolbar2.findViewById(R.id.imageView4);
            backarrow.setVisibility(View.GONE);
        }

        if(toolbar3.getVisibility() == View.VISIBLE){
           // backarrow = toolbar3.findViewById(R.id.imageView4);
           // backarrow.setVisibility(View.GONE);
        }
    }

    public void setBackArrow_To_Visible(){
        ImageView backarrow;
        if(toolbar2.getVisibility() == View.VISIBLE){
            backarrow = toolbar2.findViewById(R.id.imageView4);
            backarrow.setVisibility(View.VISIBLE);
        }

        if(toolbar3.getVisibility() == View.VISIBLE){
          //  backarrow = toolbar3.findViewById(R.id.imageView4);
           // backarrow.setVisibility(View.VISIBLE);
        }
    }

    public void setToolBars_To_Gone(){
        toolbar.setVisibility(View.GONE);
        toolbar2.setVisibility(View.GONE);
        toolbar3.setVisibility(View.GONE);
        toolbar4.setVisibility(View.GONE);


    }

    @Override
    public void onBackPressed()
    {
        // if the user is on the register or sign up screen and user press the back button,
        // user is navigated to the onboarding screen
       if(go_to_onboarding_fragment) {
           Navigation.findNavController(this, R.id.my_nav_host_fragment).navigate(R.id.blankFragment);
           go_to_onboarding_fragment = false;
       }else
           super.onBackPressed();

       }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("sign in intent", String.valueOf(requestCode));

        Firebaseauthimpl firebaseaut = Firebaseauthimpl.getInstance();
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 7 || requestCode == 131079 || requestCode == 196615 || requestCode == 262151 || requestCode == 327687) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.w("sign in intent", "start Activity google " + requestCode);
                firebaseaut.firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("exception google", "Google sign in failed", e);
                // ...
            }
        }

        if(requestCode == 64206){
            firebaseaut.callbackManager.onActivityResult(requestCode, resultCode, data);
            Log.i("loginresult","onactivityresult");
        }

        if (requestCode == 2) {
            Log.i("result code", String.valueOf(resultCode));
            if (resultCode == RESULT_OK) {
//                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
//                Log.i("credential number ", credential.getId());
            }


        }

    }

    public List<String> getdrawermenu() {
        List<String> nav_item = new ArrayList<>();
        nav_item.add("Home");
        nav_item.add("Orders");
        nav_item.add("Account Settings");
        nav_item.add("Payment");
        nav_item.add("Pricing");
        nav_item.add("FAQ");
        nav_item.add("Subscription");
        nav_item.add("Automatic Ordering");
        nav_item.add("Contact us");
        return nav_item;
    }


}