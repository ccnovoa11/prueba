package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class HistorialList extends AppCompatActivity {

    String idActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_list);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            idActual = extra.getString("key");
        }

        ImageButton circulo = findViewById(R.id.circulo);
        ImageButton objeto = findViewById(R.id.objetos);
        ImageButton inicio = findViewById(R.id.inicio);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistorialList.this, CircleList.class));
            }
        });

        circulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialList.this, UserList.class);
                intent.putExtra("key", idActual);
                startActivity(intent);
            }
        });

        objeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialList.this, ObjectList.class);
                intent.putExtra("key", idActual);
                startActivity(intent);
            }
        });

    }
}
