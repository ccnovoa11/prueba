package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CircleList extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("circles");
    //PopupWindow popupWindowCircles;

    String idActual;

    final List<String> circles = new ArrayList<String>();
    final List<String> identificadores = new ArrayList<String>();



    //Button button = findViewById(R.id.buttonShow);




    public void loadNotes() {
        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            identificadores.add(documentSnapshot.getId());
                            Circle circle = documentSnapshot.toObject(Circle.class);


                            circles.add(circle.getName());

                            Spinner spinnerCircles = findViewById(R.id.spinnerCircles);
                            ArrayAdapter<String> circlesAdapter = new ArrayAdapter<String>(CircleList.this, android.R.layout.simple_spinner_item, circles);



                            circlesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerCircles.setAdapter(circlesAdapter);

                            spinnerCircles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idActual = identificadores.get(position);
                                    Log.d("tag ","ESTE ES EL ID ACTUAL: "+idActual);
                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                            Log.d("tag","ESTA ES LA POSICION"+spinnerCircles.getSelectedItemPosition());

                            /*Circle circle = documentSnapshot.toObject(Circle.class);

                            String name = circle.getName();

                            list.add(name);*/
                        }

                        //textViewData.setText(data);
                    }
                });


    }

    /*public PopupWindow popupWindowCircles(){
        PopupWindow popupWindow = new PopupWindow(this);
        ListView listViewCircles = new ListView(this);
        listViewCircles.setAdapter(dogsAdapter(content));

        listViewCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = view.getContext();
                CircleList circleList = ((CircleList)context);

                circleList.popupWindowCircles.dismiss();
                String selected = ((TextView)view).getTag().toString();
                circleList.button.setText(selected);
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(listViewCircles);

        return popupWindow;


    }*/

    /*private ArrayAdapter<String> dogsAdapter (String circlesArray[]){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, circlesArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String text = item;

                // visual settings for the list item
                TextView listItem = new TextView(CircleList.this);

                listItem.setText(text);
                listItem.setTextSize(22);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.GREEN);

                return listItem;
            }
        };

        return adapter;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_list);
        loadNotes();

        String id = collectionReference.getId();
        Log.d("tag","THIS IS THE ID: " + id);




        ImageButton circulo = findViewById(R.id.circulo);

        ImageButton historial = findViewById(R.id.historial);
        ImageButton objeto = findViewById(R.id.objetos);
        final ImageButton inicio = findViewById(R.id.inicio);

        circulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CircleList.this, UserList.class);
                intent.putExtra("key", idActual);
                startActivity(intent);

            }
        });

        objeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CircleList.this, ObjectList.class);
                intent.putExtra("key", idActual);
                startActivity(intent);
            }
        });

        FloatingActionButton buttonAddUser = findViewById(R.id.button_add);
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CircleList.this, CircleAdd.class));
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CircleList.this, HistorialList.class);
                intent.putExtra("key", idActual);
                startActivity(intent);
            }
        });
    }
}
