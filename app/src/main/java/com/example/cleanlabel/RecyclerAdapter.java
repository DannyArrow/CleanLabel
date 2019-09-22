package com.example.cleanlabel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {
    Context context;
    List<String> mData;

    public RecyclerAdapter(Context context, List<String> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public RecyclerAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drawer, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.myViewHolder holder, int position) {
        holder.nav.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView nav;

        public myViewHolder(View itemView) {
            super(itemView);
            nav = (TextView) itemView.findViewById(R.id.nav);
        }
    }
}
