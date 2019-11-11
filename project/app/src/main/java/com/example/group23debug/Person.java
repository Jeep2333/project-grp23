package com.example.group23debug;


public class Person{
    private String firstName;
    private String lastName;
    private String userName;
    private String personType;
    private String email;
    private String password;

    public Person(String firstName , String lastName, String personType, String email, String password ,String userName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.personType = personType;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Person() {};

    public String getFirstName() {return firstName; }
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName){this.lastName = lastName; }

    public  String getPersonType() {return personType;}
    public void  setPersonType(String personType){this.personType = personType;}

    public String getEmail() {return email;}
    public void setEmail(String email){this.email = email;}

    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName=userName;}

    public String getPassword() {return password;}
    public  void setPassword(String password) {this.password = password;}
}