package com.example.medicheckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSupport extends AppCompatActivity implements View.OnClickListener  {

    private FirebaseUser gp, insurance;
    private DatabaseReference reference, reference2;
    private String gpID, insuranceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_support);

        Button goBackButton;

        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(this);

        gp = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("GP");
        gpID = gp.getUid();

        insurance = FirebaseAuth.getInstance().getCurrentUser();
        reference2 = FirebaseDatabase.getInstance().getReference("Insurance");
        insuranceID = insurance.getUid();


        final TextView textGPPhone = findViewById(R.id.gpPhone);
        final TextView textInsurancePhone = findViewById(R.id.insurancePhone);

        reference.child(gpID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GP gp = snapshot.getValue(GP.class);

                if(gp != null){
                    String gpnumber = gp.number;
                    textGPPhone.setText(gpnumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewSupport.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });


        reference2.child(insuranceID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Insurance insurance = snapshot.getValue(Insurance.class);

                if(insurance != null){
                    String insurancenumber = insurance.number;
                    textInsurancePhone.setText(insurancenumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewSupport.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    //onClick method
    public void onClick(View v) {
        if (v.getId() == R.id.goBackButton) {
            startActivity(new Intent(this, MainMenu.class));
        }
    }
}