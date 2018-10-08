package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ObjectList extends AppCompatActivity {

    MyApplication myApplication;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("circles").document("EnNobcysLalgjr5OCehC").collection("objects");

    private ObjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_list);

        myApplication = (MyApplication)getApplicationContext();



        FloatingActionButton buttonAddObject = findViewById(R.id.button_add);
        buttonAddObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ObjectList.this, ObjectAdd.class));
            }
        });


        setUpRecyclerView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10 && grantResults.length>0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                myApplication.callPermission = true;
            }
        }
    }

    private void setUpRecyclerView() {
        Query query = notebookRef;

        Context context = this.getApplicationContext();

        FirestoreRecyclerOptions<Object> options = new FirestoreRecyclerOptions.Builder<Object>()
                .setQuery(query, Object.class)
                .build();

        adapter = new ObjectAdapter(options, context);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
