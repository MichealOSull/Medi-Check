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

public class ViewInsuranceDetails extends AppCompatActivity {

    private FirebaseUser insurance;
    private DatabaseReference reference;
    private String insuranceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insurance_details);

        insurance = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Insurance");
        insuranceID = insurance.getUid();

        final TextView textViewName = findViewById(R.id.textViewName);
        final TextView textViewEmail = findViewById(R.id.textViewEmail);
        final TextView textViewPhone = findViewById(R.id.textViewPhone);


        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = textViewPhone.getText().toString().trim();
                Uri uri = Uri.parse(num);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                intent.setData(Uri.parse("Phone:" + num));
                startActivity(intent);
            }
        });

        reference.child(insuranceID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Insurance ins = snapshot.getValue(Insurance.class);

                if(ins != null){
                    String name = ins.name;
                    String email = ins.email;
                    String number = ins.number;

                    textViewName.setText(name);
                    textViewEmail.setText(email);
                    textViewPhone.setText(number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewInsuranceDetails.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });
    }
}