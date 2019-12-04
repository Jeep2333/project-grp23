package com.example.group23debug;

public class line {
    private int line=0;

    public line(){

    }
    public line(int line){
        this.line=line;
    }

    public int getLine(){
        return line;
    }
    public void setLine(int line){
        this.line=line;
    }

    public int getTime(){
        return line*15;
    }
}
