package com.example.eslam.startingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class WakeUpsNotificationed extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<WakeUp> wakeUps;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_ups_notificationed);
        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wakeUps = (List<WakeUp>) getIntent().getSerializableExtra("wakeups");
        if (wakeUps != null) {
            myAdapter = new MyAdapter(wakeUps);
            recyclerView.setAdapter(myAdapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.setAdapter(myAdapter);
    }
}
