package com.example.medicheckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewInsuranceDetails extends AppCompatActivity implements View.OnClickListener {

    private FirebaseUser insurance;
    private DatabaseReference reference;
    private String insuranceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insurance_details);

        Button goBackButton;

        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(this);

        insurance = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Insurance");
        insuranceID = insurance.getUid();

        final TextView textViewName = findViewById(R.id.insuranceName);
        final TextView textViewEmail = findViewById(R.id.emailAddress);
        final TextView textViewPhone = findViewById(R.id.insurancePhone);
        final TextView textViewAddress = findViewById(R.id.insuranceAddress);


        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = textViewPhone.getText().toString().trim();
                Uri uri = Uri.parse(num);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                intent.setData(Uri.parse("tel:" + num));
                startActivity(intent);
            }
        });

        reference.child(insuranceID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Insurance insurance = snapshot.getValue(Insurance.class);

                if(insurance != null){
                    String name = insurance.name;
                    String email = insurance.email;
                    String number = insurance.number;
                    String address = insurance.address;

                    textViewName.setText(name);
                    textViewEmail.setText(email);
                    textViewPhone.setText(number);
                    textViewAddress.setText(address);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewInsuranceDetails.this, "Error!", Toast.LENGTH_LONG).show();
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