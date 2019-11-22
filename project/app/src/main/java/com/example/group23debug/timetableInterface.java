package com.example.group23debug;

public class timetableInterface {

    private String month,day,hour;

    public timetableInterface(String month, String day, String hour){

        this.month =month;
        this.day=day;
        this.hour=hour;
    }

    public timetableInterface(){};


    public String getMonth(){return this.month;}
    public void setMonth(String month){this.month= month;}

    public String getDay(){return this.day;}
    public void setDay(String day){this.day= day;}

    public String getHour(){return this.hour;}
    public void setHour(String hour){this.hour= hour;}




}
