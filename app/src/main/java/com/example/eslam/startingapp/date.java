package com.example.eslam.startingapp;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

/**
 * Created by islam on 24/01/17.
 */

public class date implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    private int Year;
    private int Month;
    private int Day;
    private DatePickerDialog datePickerDialog;
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Year=year;
        Month=monthOfYear;
        Day=dayOfMonth;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        this.Day = day;
    }

    public DatePickerDialog getDatePickerDialog() {
        datePickerDialog=DatePickerDialog.newInstance(
                this,Year,Month,Day);
        return datePickerDialog;
    }

    public void setDatePickerDialog(DatePickerDialog datePickerDialog) {
        this.datePickerDialog = datePickerDialog;
    }
}
