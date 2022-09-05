package com.example.medicheckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterMedHistory extends AppCompatActivity implements View.OnClickListener {

    private TextView registerMedDetails;
    private EditText editTextWeight, editTextHeight;
    private FirebaseAuth mAuth;
    RadioGroup genderOption, diabeticOption, asthmaticOption;
    RadioButton genderButton, diabeticButton, asthmaticButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_med_history);

        mAuth = FirebaseAuth.getInstance();

        Button cancelButton, registerMedHistory;


        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        registerMedHistory = findViewById(R.id.registerMedHistory);
        registerMedHistory.setOnClickListener(this);

        genderOption = findViewById(R.id.genderOption);
        editTextWeight = findViewById(R.id.weight);
        editTextHeight = findViewById(R.id.height);
        diabeticOption = findViewById(R.id.diabeticOption);
        asthmaticOption = findViewById(R.id.asthmaticOption);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerMedHistory) {
            registerMedDetails();
        }
        if (v.getId() == R.id.cancelButton) {
            startActivity(new Intent(this, MainMenu.class));
        }
    }

    private void registerMedDetails() {
        int genderChoice = genderOption.getCheckedRadioButtonId();
        genderButton = findViewById(genderChoice);
        String gender = genderButton.getText().toString();
        String weight = editTextWeight.getText().toString().trim();
        String height = editTextHeight.getText().toString().trim();
        int diabeticChoice = diabeticOption.getCheckedRadioButtonId();
        diabeticButton = findViewById(diabeticChoice);
        String diabetic = diabeticButton.getText().toString();
        int asthmaticChoice = asthmaticOption.getCheckedRadioButtonId();
        asthmaticButton = findViewById(asthmaticChoice);
        String asthmatic = asthmaticButton.getText().toString();


        if(weight.isEmpty()){
            editTextWeight.setError("Please enter your weight");
            editTextWeight.requestFocus();
            return;
        }

        if(weight.length() > 4){
            editTextWeight.setError("Please enter your correct weight");
            editTextWeight.requestFocus();
            return;
        }

        if(height.isEmpty()){
            editTextHeight.setError("Please enter your height");
            editTextHeight.requestFocus();
            return;
        }
        if(height.length() > 4 ){
            editTextHeight.setError("Please enter your correct height");
            editTextHeight.requestFocus();
            return;
        }


        MedicalHistory medicalhistory = new MedicalHistory(gender, weight, height, diabetic, asthmatic);
        FirebaseDatabase.getInstance().getReference("MedicalHistory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(medicalhistory).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterMedHistory.this, "Medical History Stored", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterMedHistory.this, AddedMedHistory.class));
                }
                else{
                    Toast.makeText(RegisterMedHistory.this, "Failed to Store Details, Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}