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
    private EditText editTextNameIns, editTextEmailIns, editTextNumberIns;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_insurance);

        mAuth = FirebaseAuth.getInstance();

        registerInsurance = (Button) findViewById(R.id.buttonRegisterIns);
        registerInsurance.setOnClickListener(this);

        editTextNameIns = findViewById(R.id.editTextNameIns);
        editTextEmailIns = findViewById(R.id.editTextEmailIns);
        editTextNumberIns = findViewById(R.id.editTextNumberIns);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonRegisterIns) {
            registerInsurance();
        }
    }

    private void registerInsurance() {
        String name = editTextNameIns.getText().toString().trim();
        String email = editTextEmailIns.getText().toString().trim();
        String number = editTextNumberIns.getText().toString().trim();

        if (name.isEmpty()){
            editTextNameIns.setError("You must enter GP full name!");
            editTextNameIns.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmailIns.setError("You must enter GP email address!");
            editTextEmailIns.requestFocus();
            return;
        }

        if(number.isEmpty()){
            editTextNumberIns.setError("You must enter GP Phone Number!");
            editTextNumberIns.requestFocus();
            return;
        }

        Insurance insurance = new Insurance(name, email, number);

        FirebaseDatabase.getInstance().getReference("Insurance")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(insurance).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterInsurance.this, "Provider Successfully Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterInsurance.this, ViewInsuranceDetails.class));
                }
                else{
                    Toast.makeText(RegisterInsurance.this, "Failed to Register", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}