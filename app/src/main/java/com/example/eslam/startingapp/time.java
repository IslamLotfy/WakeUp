package com.example.eslam.startingapp;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

/**
 * Created by islam on 24/01/17.
 */

public class time implements TimePickerDialog.OnTimeSetListener {
    private int Hour;
    private int Minute;
    private TimePickerDialog timePickerDialog;

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Hour=hourOfDay;
        Minute=minute;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMinute() {
        return Minute;
    }

    public void setMinute(int minute) {
        Minute = minute;
    }

    public TimePickerDialog getTimePickerDialog() {
        timePickerDialog=TimePickerDialog.newInstance(this,Hour,Minute,true);
        return timePickerDialog;
    }

    public void setTimePickerDialog(TimePickerDialog timePickerDialog) {
        this.timePickerDialog = timePickerDialog;
    }
}
