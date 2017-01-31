package com.example.eslam.startingapp;

import java.io.Serializable;

/**
 * Created by islam on 23/01/17.
 */

public class WakeUp implements Serializable{
    private String wakeUptitle;
    private String wakeUpdescription;
    private String piriority;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int pirindex;
    private String Key;
    WakeUp(){
        wakeUptitle="";
        wakeUpdescription="";
        piriority="";
        year=2017;
        month=10;
        day=12;
        pirindex=0;
        Key="1";
    }
    public String getWakeUptitle() {
        return wakeUptitle;
    }

    public void setWakeUptitle(String wakeUptitle) {
        this.wakeUptitle = wakeUptitle;
    }

    public String getWakeUpdescription() {
        return wakeUpdescription;
    }

    public void setWakeUpdescription(String wakeUpdescription) {
        this.wakeUpdescription = wakeUpdescription;
    }

    public String getPiriority() {
        return piriority;
    }

    public void setPiriority(String piriority) {

        this.piriority = piriority;
        if(piriority.equals("high")){
            this.setPirindex(0);
        }else if(piriority.equals("meduim")){
            this.setPirindex(1);
        }else{
            this.setPirindex(2);
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    public String getDate(){
        String re=day+"/"+(month+1)+"/"+year;
        return re;
    }
    public String getTime(){
        String re=hour+":"+minute;
        return re;
    }

    public int getPirindex() {
        return pirindex;
    }

    public void setPirindex(int pirindex) {
        this.pirindex = pirindex;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
