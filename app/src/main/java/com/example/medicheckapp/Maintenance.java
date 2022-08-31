package com.example.medicheckapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Maintenance extends AppCompatActivity implements View.OnClickListener {

    //This class is used to implement a page which Payment(MediCoin) and MediPrdict can redirect to
    //as these functions were not implemented into the project

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        Button mainMenuButton;


        mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(this);

    }

    @Override
    //onClick method
    public void onClick(View v) {
        //Switch statement to determine what action to take depending on what is clicked
        if (v.getId() == R.id.mainMenuButton) {
            startActivity(new Intent(this, MainMenu.class));
        }

    }

}