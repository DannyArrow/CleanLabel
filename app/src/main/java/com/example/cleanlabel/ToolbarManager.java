package com.example.cleanlabel;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    public Activity mactivity;

    public ToolbarManager(AppCompatActivity activity){
        mactivity = activity;
        actionBar = activity.getSupportActionBar();
        actionBar.setCustomView(R.layout.toolbar_one);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setElevation(0);
        //enablebackfunctionality(activity);
    }






   public void onboarding_showbar(){
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



}
