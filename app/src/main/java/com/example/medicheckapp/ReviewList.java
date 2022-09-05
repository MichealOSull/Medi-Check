package com.example.medicheckapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewList extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    DatabaseReference reference;
    MyAdapter myAdapter;
    ArrayList<Review> list;
    Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        recyclerView = findViewById(R.id.reviewList);
        reference = FirebaseDatabase.getInstance().getReference("Review");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        mainMenu = findViewById(R.id.mainMenuButton);
        mainMenu.setOnClickListener(this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Review review = dataSnapshot.getValue(Review.class);
                    list.add(review);
                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    //onClick method
    public void onClick(View v) {

        if (v.getId() == R.id.mainMenuButton) {
            startActivity(new Intent(this, MainMenu.class));
        }
    }

}