package com.example.medicheckapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword, editTextPPS, editTextAddress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        Button cancel, registerUser;


        registerUser = findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);
        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextPPS = (EditText) findViewById(R.id.PPSNumber);
        editTextAddress = (EditText) findViewById(R.id.address);

    }

    @Override
    //onClick method
    public void onClick(View v) {

        if (v.getId() == R.id.registerUser) {
            registerUser();
        }
        if (v.getId() == R.id.cancelButton) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String pps = editTextPPS.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if (fullName.isEmpty()) {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 7) {
            editTextPassword.setError("Password needs min of 7 characters");
            editTextPassword.requestFocus();
            return;
        }

        if (pps.length() <9) {
            editTextPPS.setError("PPS needs to be 9 characters");
            editTextPPS.requestFocus();
            return;
        }

        if (pps.length() > 9) {
            editTextPPS.setError("PPS needs to be 9 characters");
            editTextPPS.requestFocus();
            return;
        }

        if (address.length() > 60) {
            editTextAddress.setError("Address is too big");
            editTextAddress.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        User user = new User(fullName, age, email, pps, address);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterUser.this, "User has been registered!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RegisterUser.this, Login.class));

                                } else {
                                    Toast.makeText(RegisterUser.this, "Failed to register, try again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
        });
}}