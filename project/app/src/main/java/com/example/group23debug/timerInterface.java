package com.example.group23debug;

public class timerInterface {
    private int month,day,hour;


    public timerInterface(int month , int day, int hour){
        this.month =month;
        this.day = day;
        this.hour =hour;
    }

    public  timerInterface(){}

    public int getMonth(){
        return this.month;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public int getDay(){
        return this.day;
    }
    public void setDay(int day){
        this.day = day;
    }
    public int getHour(){
        return this.hour;
    }
    public void setHour(int hour){
        this.hour = hour;
    }

}
