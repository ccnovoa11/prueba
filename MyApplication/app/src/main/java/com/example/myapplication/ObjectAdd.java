package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
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


        if(isConnected() && isOnlineNet()) {

            CollectionReference ObjectRef = FirebaseFirestore.getInstance()
                    .collection("circles").document("EnNobcysLalgjr5OCehC").collection("objects");

            ObjectRef.add(new Object(nombre, phone));
            Toast.makeText(this, "Objeto agregado", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            buildDialog(ObjectAdd.this).show();
        }
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

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Error");
        builder.setMessage("La conexión a internet se ha perdido, comprueba que tu celular tenga acceso a internet ya sea por datos o por wifi");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


}

