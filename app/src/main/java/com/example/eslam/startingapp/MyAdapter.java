package com.example.eslam.startingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by islam on 02/02/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewholder> {
    private List<WakeUp> wakeUps;

    public MyAdapter(List<WakeUp> wakeUps) {
        this.wakeUps = wakeUps;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        WakeUp wakeUp = wakeUps.get(position);
        holder.setData(wakeUp);
    }


    @Override
    public int getItemCount() {
        return wakeUps.size();
    }
}
