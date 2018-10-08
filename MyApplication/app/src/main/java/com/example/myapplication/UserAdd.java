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

public class UserAdd extends AppCompatActivity {

    private ImageView imageProfile;
    private EditText editTextUser;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        imageProfile = findViewById(R.id.imageViewProfile);
        editTextUser = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        addButton = findViewById(R.id.add_user_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveUser();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void saveUser() throws ParseException {
        String nombre = editTextUser.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();

        if(nombre.trim().isEmpty() || phone.trim().isEmpty() || email.trim().isEmpty()){
            Toast.makeText(this,"Inserte valores válidos",Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference userRef = FirebaseFirestore.getInstance()
                .collection("users");

        userRef.add(new User(nombre, phone, email));
        Toast.makeText(this,"Persona agregada",Toast.LENGTH_SHORT).show();
        finish();
    }
}
