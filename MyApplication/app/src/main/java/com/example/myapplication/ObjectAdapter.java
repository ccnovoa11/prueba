package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ObjectAdapter extends FirestoreRecyclerAdapter<Object, ObjectAdapter.ObjectHolder> {

    Context context;


    public ObjectAdapter(@NonNull FirestoreRecyclerOptions<Object> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull ObjectHolder holder, int position, @NonNull Object model) {
        holder.textViewName.setText(model.getName());
    }

    @NonNull
    @Override
    public ObjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.object_item, viewGroup, false);

        return new ObjectHolder(v);
    }


    class ObjectHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewImage;

        public ObjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.object_name);

        }
    }
}