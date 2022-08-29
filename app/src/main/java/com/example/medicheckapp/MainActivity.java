package com.example.medicheckapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login, register;


        login = findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        register = findViewById(R.id.registerButton);
        register.setOnClickListener(this);
    }

    @Override
    //onClick method
    public void onClick(View v) {
        //Switch statement to determine what action to take depending on what is clicked
        switch (v.getId()) {
            //If register is clicked, redirect to register activity
            case R.id.loginButton:
                startActivity(new Intent(this, Login.class));
                break;
            //If login is clicked, call the userLogin function
            case R.id.registerButton:
                startActivity(new Intent(this, RegisterUser.class));
                break;
        }
        }
    }