package com.example.medicheckapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
        if (v.getId() == R.id.loginButton) {
            startActivity(new Intent(this, Login.class));
        }
        if (v.getId() == R.id.registerButton) {
            startActivity(new Intent(this, RegisterUser.class));
        }
    }

}