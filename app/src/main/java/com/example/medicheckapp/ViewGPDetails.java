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

public class ViewGPDetails extends AppCompatActivity {

    private FirebaseUser gp;
    private DatabaseReference reference;
    private String gpID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gpdetails);

        gp = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("GP");
        gpID = gp.getUid();

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


        reference.child(gpID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GP gp = snapshot.getValue(GP.class);

                if (gp != null) {
                    String name = gp.name;
                    String email = gp.email;
                    String number = gp.number;

                    textViewName.setText(name);
                    textViewEmail.setText(email);
                    textViewPhone.setText(number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewGPDetails.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });
    }
}