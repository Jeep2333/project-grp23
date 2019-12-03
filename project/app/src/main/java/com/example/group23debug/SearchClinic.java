package com.example.group23debug;

import android.app.Service;

public class SearchClinic {
    private String clinicName;
    private String phoneNumber;
    private String address;
    private String Insurance;
    private Service service;
    private String payment;


    public SearchClinic(String clinicName , String address, String phoneNumber, String Insurance,String payment){
        this.clinicName = clinicName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.Insurance = Insurance;
        this.payment = payment;

    }

    public SearchClinic() {};

    public String getClinicName() {return clinicName; }
    public void setClinicName(String firstName) {this.clinicName = firstName;}

    public String getAddress() {return address;}
    public void setAddress(String address){this.address = address; }

    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public String getPayment() {return payment;}
    public void setPayment(String payment){this.payment = payment;}

    public Service getService() {return service;}
    public void setService(Service service){this.service = service;}

    public String getInsurance(){return Insurance;}
    public void setInsurance(String Insurance){this.Insurance=Insurance;}
}
