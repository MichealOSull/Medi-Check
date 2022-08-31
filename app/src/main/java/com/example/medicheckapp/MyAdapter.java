package com.example.medicheckapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Review> list;

    public MyAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reviewitem,parent,false);
        return new MyViewHolder(v);
}

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Review review = list.get(position);
        holder.email.setText(review.getEmail());
        holder.message.setText(review.getMessage());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView email,message;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);


            email = itemView.findViewById(R.id.reviewEmail);
            message = itemView.findViewById(R.id.reviewMessage);
        }
    }
}
