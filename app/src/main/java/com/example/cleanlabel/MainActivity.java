package com.example.cleanlabel;

import android.content.Context;
import android.os.Bundle;
import android.os.*;

import android.util.*;
import android.view.*;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDestination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    public  Menu menu;
    private MenuItem menuItem;
    //private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToolbarManager toolbarManager = new ToolbarManager(this);

        NavController navController = (NavController) Navigation.findNavController(this,R.id.my_nav_host_fragment);
        navController.addOnNavigatedListener(new NavController.OnNavigatedListener() {
            @Override
            public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {

                switch (destination.getId()){
                    case R.id.blankFragment:
                        toolbarManager.onboarding_showbar();
                        break;

                    case R.id.zipCode:
                       toolbarManager.no_title_backbtn();

                        break;

                    case R.id.registerbtn:
                        toolbarManager.no_title_backbtn();
                        toolbarManager.enablebackfunctionality();

                        break;

                    case R.id.verify:
                      toolbarManager.backbtn_with_brandlogo();
                      toolbarManager.disable_backfunctionality();

                        break;

                    case R.id.verifyCode:
                        toolbarManager.backbtn_with_brandlogo();
                        toolbarManager.enablebackfunctionality();
                        break;

                    case R.id.prefrence:
                        toolbarManager.set_title("Prefrence");
                        toolbarManager.disable_backfunctionality();
                        break;

                    case R.id.scheduleDelivery:
                        toolbarManager.set_title("Schedule Delivery");
                        break;

                    case R.id.orderSummary:
                        toolbarManager.set_title("Order Summary");
                        break;

                    case R.id.payment:
                        toolbarManager.set_title("Payment");
                        break;
                }
            }
        });
    }





}