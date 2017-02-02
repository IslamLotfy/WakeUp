package com.example.eslam.startingapp;


import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.*;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

public class AddWakeUp extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private Spinner spinner;
    private com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebtn;
    private Button timebtn;
    private WakeUp wakeUp;
    private Button submit;
    private time t;
    private date d;
    private CustomOnItemSelectedListener customOnItemSelectedListener;
    private DatabaseReference databaseReference;
    private authinticator auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wake_up);
        title=(EditText)findViewById(R.id.wakeUptitle);
        description=(EditText)findViewById(R.id.wakeUpdetail);
        spinner=(Spinner)findViewById(R.id.spinner);
        datebtn=(Button)findViewById(R.id.datebtn);
        timebtn=(Button)findViewById(R.id.timebtn);
        submit=(Button)findViewById(R.id.submitbtn);
        t=new time();
        d=new date();
        auth=new authinticator();
        databaseReference= FirebaseDatabase.getInstance().getReference(auth.getUserId());

        wakeUp= (WakeUp) getIntent().getSerializableExtra("wakeup");
        if(wakeUp!=null){
            EditWakeUp(wakeUp);
        }else{
            wakeUp=new WakeUp();
            final Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
            android.text.format.Time now=new android.text.format.Time();
            now.setToNow();
            wakeUp.setYear(now.year);
            wakeUp.setMonth(now.month);
            wakeUp.setDay(now.monthDay);
            wakeUp.setHour(now.hour);
            wakeUp.setMinute(now.minute);
        }


        Log.d("firebase reference ",databaseReference.toString());
        customOnItemSelectedListener=new CustomOnItemSelectedListener(wakeUp.getPiriority(),spinner);
        spinner.setOnItemSelectedListener(customOnItemSelectedListener);
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setYear(wakeUp.getYear());
                d.setMonth(wakeUp.getMonth());
                d.setDay(wakeUp.getDay());
                datePickerDialog= d.getDatePickerDialog();
                datePickerDialog.show(getFragmentManager(),"Date");
            }
        });
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setHour(wakeUp.getHour());
                t.setMinute(wakeUp.getMinute());
                timePickerDialog=t.getTimePickerDialog();
                timePickerDialog.show(getFragmentManager(),"Time");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wakeUp.setWakeUptitle(title.getText().toString());
                wakeUp.setWakeUpdescription(description.getText().toString());
                wakeUp.setPiriority(customOnItemSelectedListener.getPiriority());
                wakeUp.setYear(d.getYear());
                wakeUp.setMonth(d.getMonth());
                wakeUp.setDay(d.getDay());
                wakeUp.setHour(t.getHour());
                wakeUp.setMinute(t.getMinute());
                DatabaseReference child;
                String key=wakeUp.getKey();
                if(key.equals("1")) {
                    child = databaseReference.push();
                    wakeUp.setKey(child.getKey().toString());
                }else{
                    child = databaseReference.child(key);
                }
                child.setValue(wakeUp);
                Log.d("added to database",child.toString());
                AddWakeUp.this.finish();
            }
        });

    }
    public void EditWakeUp(WakeUp wakeUp) {
        title.setText(wakeUp.getWakeUptitle());
        description.setText(wakeUp.getWakeUpdescription());
        spinner.setSelection(wakeUp.getPirindex());
        datebtn.setText(wakeUp.getDate());
        timebtn.setText(wakeUp.getTime());
        submit.setText("Update");
    }
}
