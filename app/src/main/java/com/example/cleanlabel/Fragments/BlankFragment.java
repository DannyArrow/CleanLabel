package com.example.cleanlabel.Fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cleanlabel.Menucallback;
import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.viewpager.widget.ViewPager;

import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class BlankFragment extends androidx.fragment.app.Fragment {
   private Drawable selected_indicator;
   private Drawable unselected_indicator;
   private Button signIn;
   private Button signUp;
    ArrayList<ImageView> imglist;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);
        imglist = new ArrayList<>();
        imglist.clear();

        ImageView indicator_1 = view.findViewById(R.id.imageView8);
        ImageView indicator_2 = view.findViewById(R.id.imageView9);
        ImageView indicator_3 = view.findViewById(R.id.imageView10);
        signIn = view.findViewById(R.id.button2);
        signUp = view.findViewById(R.id.button3);
        imglist.add(indicator_1);
        imglist.add(indicator_2);
        imglist.add(indicator_3);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(customPagerAdapter);
        selected_indicator = ContextCompat.getDrawable(getActivity(), R.drawable.indicator);
        unselected_indicator = ContextCompat.getDrawable(getActivity(), R.drawable.unselectedindicator);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
               updateIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        view.findViewById(R.id.button3).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_blankFragment_to_registerbtn2)); // register button
        view.findViewById(R.id.button2).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.login)); //login button


        return view;

    }


    void updateIndicators(int position) {
        for (int i = 0; i < imglist.size(); i++) {
            imglist.get(i).setImageDrawable(
                    i == position ? selected_indicator : unselected_indicator
            );
        }
    }





}
