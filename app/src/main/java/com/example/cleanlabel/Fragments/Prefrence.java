package com.example.cleanlabel.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cleanlabel.Adapter.CustomAdapter;
import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;


/**
 * A simple {@link Fragment} subclass.
 */
public class Prefrence extends Fragment {
    ImageView imageView4;
    RecyclerView recyclerview;
    TextView txtview22;
    ImageView imageView45;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prefrence, container, false);
        //view.findViewById(R.id.viewnext).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_prefrence_to_scheduleDelivery));

        setHasOptionsMenu(true);


        imageView45= view.findViewById(R.id.imageView45);

        txtview22= view.findViewById(R.id.txtview22);



        recyclerview= view.findViewById(R.id.recyclerview);
        JSONArray data=new JSONArray();
        CustomAdapter horizontalAdapter=new CustomAdapter(data);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(horizontalLayoutManagaer);
        recyclerview.setAdapter(horizontalAdapter);

        txtview22.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_prefrence_to_scheduleDelivery));


        // Get reference of widgets from XML layout
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "No Softner",
                "Softner"
        };

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getContext(),R.layout.spinner_item,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);


        return view;
    }



//    @Override
//    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.menu, menu);
//    }

}
