package com.example.group23debug;

public class Service {
    private static String name;
    private static String dname;
    public Service(String name,String dname){
        this.name=name;
        this.dname=dname;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getdName(){
        return this.dname;
    }
    public void setdName(String dname){
        this.dname=dname;
    }

}

