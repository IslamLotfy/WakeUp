package com.example.eslam.startingapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by islam on 25/01/17.
 */

public class MyViewholder extends RecyclerView.ViewHolder {

    View mView;
    public MyViewholder(View itemView) {
        super(itemView);
        mView=itemView;
    }
    public void setData(WakeUp wakeUp){
        TextView titView=(TextView) mView.findViewById(R.id.titview);
        TextView descView=(TextView) mView.findViewById(R.id.descview);
        TextView pirView=(TextView) mView.findViewById(R.id.pirview);
        TextView dateView=(TextView) mView.findViewById(R.id.dateview);
        TextView time=(TextView) mView.findViewById(R.id.timeview);
        titView.setText(wakeUp.getWakeUptitle());
        descView.setText(wakeUp.getWakeUpdescription());
        pirView.setText(wakeUp.getPiriority());
        dateView.setText(wakeUp.getDate());
        time.setText(wakeUp.getTime());
    }
}
