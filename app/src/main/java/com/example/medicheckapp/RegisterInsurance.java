package com.example.medicheckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterInsurance extends AppCompatActivity implements View.OnClickListener{

    private TextView registerInsurance;
    private EditText editTextNameInsurance, editTextEmailInsurance, editTextNumberInsurance, editTextAddressInsurance;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_insurance);

        mAuth = FirebaseAuth.getInstance();

        registerInsurance = (Button) findViewById(R.id.buttonRegisterInsurance);
        registerInsurance.setOnClickListener(this);

        Button cancelButton;

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        editTextNameInsurance = findViewById(R.id.insuranceName);
        editTextEmailInsurance = findViewById(R.id.insuranceEmail);
        editTextNumberInsurance = findViewById(R.id.insuranceNumber);
        editTextAddressInsurance = findViewById(R.id.insuranceAddress);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRegisterInsurance) {
            registerInsurance();
        }
        if (v.getId() == R.id.cancelButton) {
            startActivity(new Intent(this, MainMenu.class));
        }
    }

    private void registerInsurance() {
        String name = editTextNameInsurance.getText().toString().trim();
        String email = editTextEmailInsurance.getText().toString().trim();
        String number = editTextNumberInsurance.getText().toString().trim();
        String address = editTextAddressInsurance.getText().toString().trim();

        if (name.isEmpty()){
            editTextNameInsurance.setError("You must enter GP full name!");
            editTextNameInsurance.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmailInsurance.setError("You must enter GP email address!");
            editTextEmailInsurance.requestFocus();
            return;
        }

        if(number.isEmpty()){
            editTextNumberInsurance.setError("You must enter GP Phone Number!");
            editTextNumberInsurance.requestFocus();
            return;
        }

        if (address.length() > 60) {
            editTextAddressInsurance.setError("Address is too big");
            editTextAddressInsurance.requestFocus();
        }

        Insurance insurance = new Insurance(name, email, number, address);

        FirebaseDatabase.getInstance().getReference("Insurance")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(insurance).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterInsurance.this, "Insurance Details Saved", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterInsurance.this, ViewInsuranceDetails.class));
                }
                else{
                    Toast.makeText(RegisterInsurance.this, "Failed to Save Details, try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}