package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    boolean existe = false;

    public void mirarSiexiste(){

        final boolean ex = false;

        db.collection("circles")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> document = task.getResult().getDocuments();
                            if (document.size()>0){
                                existe = true;
                            }
                        }
                    }
                });
    }


    Button ingresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mirarSiexiste();


        ingresar = findViewById(R.id.button_iniciar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mirarSiexiste();


                if (existe == true){
                    //Intent intentIngresar = new Intent(MainActivity.this, NuevoCirculoInicio.class);
                    //MainActivity.this.startActivity(intentIngresar);
                    Intent intent = new Intent(MainActivity.this, CircleList.class);
                    MainActivity.this.startActivity(intent);


                }
                else {
                    Intent intent = new Intent(MainActivity.this, CircleAdd.class);
                    MainActivity.this.startActivity(intent);

                }

            }
        });
    }


}
