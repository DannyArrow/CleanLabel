package com.example.cleanlabel;

import android.app.Activity;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ToolbarManager extends AppCompatActivity {
    private Toolbar toolbar;
    private static ToolbarManager toolbarManager;
    ActionBar actionBar;
    public AppCompatActivity mactivity;

    public ToolbarManager(AppCompatActivity activity){
        mactivity = activity;
        actionBar = activity.getSupportActionBar();
//        activity.setSupportActionBar();
//        activity.getSupportActionBar().setCustomView(R.layout.toolbar_one);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setElevation(0);
        //enablebackfunctionality(activity);

    }






   public void onboarding_showbar(){
      // mactivity.getActionBar().setCustomView(R.layout.toolbar_one);
       actionBar.setCustomView(R.layout.toolbar_one);

   }

   public void no_title_backbtn(){
       actionBar.setCustomView(R.layout.toolbar_two);
       actionBar.getCustomView().findViewById(R.id.linearLayout6).setVisibility(View.INVISIBLE);
   }

   public void backbtn_with_brandlogo() {
       actionBar.setCustomView(R.layout.toolbar_two);
       if (actionBar.getCustomView().findViewById(R.id.linearLayout6 ) != null) {
           actionBar.getCustomView().findViewById(R.id.linearLayout6).setVisibility(View.VISIBLE);
       }
   }

   public void set_title(String string){
       actionBar.setCustomView(R.layout.toolbar_three);
       TextView txtview = actionBar.getCustomView().findViewById(R.id.title);
      // ImageView img = actionBar.getCustomView().findViewById(R.id.imageView13);
      // showPopup(img);
       txtview.setText(string);
   }

   public void disable_backfunctionality(){
       actionBar.getCustomView().findViewById(R.id.imageView4).setVisibility(View.INVISIBLE);
   }

    public void enablebackfunctionality(){
        if (actionBar.getCustomView().findViewById(R.id.imageView4 ) != null) {
            actionBar.getCustomView().findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
        }
        ImageView img  = actionBar.getCustomView().findViewById(R.id.imageView4);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mactivity,R.id.my_nav_host_fragment).navigateUp();
            }
        });
    }

    public void enable_back_to_Onboarding(){
        if (actionBar.getCustomView().findViewById(R.id.imageView4 ) != null) {
            actionBar.getCustomView().findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
        }
        ImageView img  = actionBar.getCustomView().findViewById(R.id.imageView4);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mactivity,R.id.my_nav_host_fragment).navigate(R.id.blankFragment);
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(mactivity, v);
        popup.inflate(R.menu.menu);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();

            }
        });
    }


    public void disapper_tooolbar() {
        actionBar.setCustomView(null);
    }
}
