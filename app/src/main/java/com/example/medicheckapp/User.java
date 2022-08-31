package com.example.medicheckapp;

public class User {

    public String fullName, age, email, pps, address;

    public User(){

    }

    public User(String fullName, String age, String email, String pps, String address){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.pps = pps;
        this.address = address;
    }
}
