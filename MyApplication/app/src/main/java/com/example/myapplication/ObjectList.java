package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ObjectList extends AppCompatActivity {

    MyApplication myApplication;
    String idColec;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef;

    private ObjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            idColec = extra.getString("key");
        }

        notebookRef = db.collection("circles").document(idColec).collection("objects");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_list);

        myApplication = (MyApplication)getApplicationContext();


        FloatingActionButton buttonAddObject = findViewById(R.id.button_add);
        buttonAddObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ObjectList.this, ObjectAdd.class);
                intent.putExtra("key2", idColec);
                startActivity(intent);
            }
        });


        setUpRecyclerView();

        ImageButton circulo = findViewById(R.id.circulo);

        ImageButton historial = findViewById(R.id.historial);
        ImageButton objeto = findViewById(R.id.objetos);
        circulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObjectList.this, UserList.class);
                intent.putExtra("key", idColec);
                startActivity(intent);
            }
        });

        ImageButton inicio = findViewById(R.id.inicio);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ObjectList.this, CircleList.class));

            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObjectList.this, HistorialList.class);
                intent.putExtra("key", idColec);
                startActivity(intent);
            }
        });
    }

    public boolean isConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    public boolean isOnlineNet()
    {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
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
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
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
