package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;

public class ObjectAdd extends AppCompatActivity {


    private EditText editTextObject;
    private EditText editTextImage;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_add);


        editTextObject = findViewById(R.id.editTextName);
        editTextImage = findViewById(R.id.editTextImage);
        addButton = findViewById(R.id.add_object_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveObject();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void saveObject() throws ParseException {
        String nombre = editTextObject.getText().toString();
        String phone = editTextImage.getText().toString();

        if (nombre.trim().isEmpty() || phone.trim().isEmpty()) {
            Toast.makeText(this, "Inserte valores válidos", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference ObjectRef = FirebaseFirestore.getInstance()
                .collection("circles").document("EnNobcysLalgjr5OCehC").collection("objects");

        ObjectRef.add(new Object(nombre, phone));
        Toast.makeText(this, "Objeto agregado", Toast.LENGTH_SHORT).show();
        finish();
    }


}

