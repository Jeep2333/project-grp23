package com.example.group23debug;

public class Service {
    private static String name;
    private static int rank;
    public Service(String name,int rank){
        this.name=name;
        this.rank=rank;
    }

    public String getName(){
        return this.name;
    }
    public void setName(){
        this.name=name;
    }
    public int getRank(){
        return this.rank;
    }
    public void setRank(int rank){
        this.rank=rank;
    }
}

