package com.example.eslam.startingapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Calendar;


public class Account extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private authinticator auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        auth=new authinticator();
        recyclerView=(RecyclerView)findViewById(R.id.Rview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference(auth.getUserId());
        scheduleAlarm();
    }

    private void scheduleAlarm() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReciever.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmMgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 60, pIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.aciton_add){
            startActivity(new Intent(Account.this,AddWakeUp.class) );
        }else if(item.getItemId()==R.id.action_logout){
            auth.logout();
            startActivity(new Intent(Account.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    public void setadapter(){
        FirebaseRecyclerAdapter<WakeUp,MyViewholder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<WakeUp, MyViewholder>(
                WakeUp.class,
                R.layout.item,
                MyViewholder.class,
                databaseReference) {
            @Override
            protected void populateViewHolder(MyViewholder viewHolder, final WakeUp model, int position) {
                viewHolder.setData(model);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Account.this,AddWakeUp.class);
                        intent.putExtra("wakeup",model);
                        startActivity(intent);
                    }
                });
            }

        };
        Log.d("url ",databaseReference.toString());
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        setadapter();
    }
}
//compile fileTree(include: ['*.jar'], dir: 'libs')