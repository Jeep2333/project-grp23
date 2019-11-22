package com.example.group23debug;

public class ratedservice {
    private String name;
    private String role;
    private String rate;


    public ratedservice(String name , String role, String rate){
        this.name = name;
        this.role = role;
        this.rate = rate;

    }

    public ratedservice() {};

    public String getName() {return name; }
    public void setName(String name) {this.name = name;}

    public String getRole() {return role;}
    public void setRole(String role){this.role = role; }

    public  String getRate() {return rate;}
    public void  setRate(String rate){this.rate = rate;}



}
