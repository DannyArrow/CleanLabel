package com.example.cleanlabel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanlabel.R;

import org.json.JSONArray;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private JSONArray dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if (getPosition()==0)
                    {
                        Toast.makeText(v.getContext(), " On CLick one", Toast.LENGTH_SHORT).show();

                    } if (getPosition()==1)
                {
                    Toast.makeText(v.getContext(), " On CLick Two", Toast.LENGTH_SHORT).show();

                } if (getPosition()==2)
                {
                    Toast.makeText(v.getContext(), " On CLick Three", Toast.LENGTH_SHORT).show();

                } if (getPosition()==3)
                {
                    Toast.makeText(v.getContext(), " On CLick Fore", Toast.LENGTH_SHORT).show();

                }

                }
            });
        }
    }

    public CustomAdapter(JSONArray data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_preferences, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


    }

    @Override
    public int getItemCount() {
        return 8;
    }}
