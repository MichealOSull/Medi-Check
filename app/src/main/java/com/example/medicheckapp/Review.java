package com.example.medicheckapp;

public class Review {

    String email, message;

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public Review(String email, String message){

        this.email = email;
        this.message = message;
    }
}
