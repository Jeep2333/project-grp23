package com.example.group23debug;

public class Service {
    private String name;
    private String role;

    public Service(String name , String role){
        this.name = name;
        this.role = role;
    }

   public  Service(){}

   public String getName(){
        return this.name;
   }
   public void setName(String name){
        this.name = name;
   }
   public String getRole(){
        return this.role;
   }
   public void setRole(String role){
        this.role = role;
   }


}
