package com.example.medicheckapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button viewDetails, registerGp, viewGPDetails, registerInsurance, viewInsuranceDetails;

        viewDetails = findViewById(R.id.viewDetails);
        viewDetails.setOnClickListener(this);
        registerGp = findViewById(R.id.registerGP);
        registerGp.setOnClickListener(this);
        viewGPDetails = findViewById(R.id.viewGPDetails);
        viewGPDetails.setOnClickListener(this);
        registerInsurance = findViewById(R.id.registerInsurance);
        registerInsurance.setOnClickListener(this);
        viewInsuranceDetails = findViewById(R.id.viewInsuranceDetails);
        viewInsuranceDetails.setOnClickListener(this);

        logout = (Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.signOut) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu.this, MainActivity.class));
            }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.viewDetails) {
            startActivity(new Intent(MainMenu.this, ViewDetails.class));
        }
        if (view.getId() == R.id.registerGP) {
            startActivity(new Intent(MainMenu.this, RegisterGP.class));
        }
        if (view.getId() == R.id.viewGPDetails) {
            startActivity(new Intent(MainMenu.this, ViewGPDetails.class));
        }
        if (view.getId() == R.id.registerInsurance) {
            startActivity(new Intent(MainMenu.this, RegisterInsurance.class));
        }
        if (view.getId() == R.id.viewInsuranceDetails) {
            startActivity(new Intent(MainMenu.this, ViewInsuranceDetails.class));
        }
    }
}