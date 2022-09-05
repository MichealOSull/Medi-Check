package com.example.medicheckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterReview extends AppCompatActivity implements View.OnClickListener {

    private TextView registerMessage;
    private EditText usernameR, messageR;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_review);

        mAuth = FirebaseAuth.getInstance();

        Button cancel;

        registerMessage = (Button) findViewById(R.id.registerMessage);
        registerMessage.setOnClickListener(this);
        cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(this);

        usernameR = findViewById(R.id.username);
        messageR = findViewById(R.id.message);
    }


    @Override
    //onClick method
    public void onClick(View v) {

        if (v.getId() == R.id.registerMessage) {
            registerMessage();
        }
        if (v.getId() == R.id.cancelButton) {
            startActivity(new Intent(this, MainMenu.class));
        }
    }


    public void registerMessage(){
        String username = usernameR.getText().toString().trim();
        String message = messageR.getText().toString().trim();

        if(username.isEmpty()){
            usernameR.setError("Please enter a username");
            usernameR.requestFocus();
            return;
        }

        if(message.isEmpty()){
            usernameR.setError("Please enter a review");
            usernameR.requestFocus();
            return;
        }

        Review review = new Review(username, message);
        FirebaseDatabase.getInstance().getReference("Review")
                .child(username)
                .setValue(review).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterReview.this, "Thanks for your feedback!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterReview.this, ReviewList.class));
                }
                else{
                    Toast.makeText(RegisterReview.this, "Failed to Register", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}