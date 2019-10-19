package com.example.group23debug;

public class Student{
    private String firstName;
    private String lastName;
    private String studentNo;
    private String email;
    private String password;

    public Student(String firstName , String lastName, String studentNo, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNo = studentNo;
        this.email = email;
        this.password = password;
    }

    public Student() {};

    public String getFirstName() {return firstName; }
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName){this.lastName = lastName; }

    public  String getStudentNo() {return studentNo;}
    public void  setStudentNo(String studentNo){this.studentNo = studentNo;}

    public String getEmail() {return email;}
    public void setEmail(String email){this.email = email;}

    public String getPassword() {return password;}
    public  void setPassword(String password) {this.password = password;}
}