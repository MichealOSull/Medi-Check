package com.example.medicheckapp;

public class Review {

    public String username, message;

    public Review(){

    }

    public String getUsername() {

        return username;
    }

    public String getMessage() {

        return message;
    }

    public Review(String username, String message){

        this.username = username;
        this.message = message;
    }

}
