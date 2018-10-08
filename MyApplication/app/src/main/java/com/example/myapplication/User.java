package com.example.myapplication;

import com.google.firebase.firestore.FieldValue;


import java.text.ParseException;


public class User {
    private com.google.firebase.Timestamp creationDate;
    private String name;
    private String phone;
    private String email;

    public User() {
        //Vacio :v
    }

    public User(String name, String phone, String email) throws ParseException {


        this.creationDate = com.google.firebase.Timestamp.now();


        this.name = name;
        this.phone = phone;
        this.email = email;

    }

    public String getName() {

        return name;
    }


    public String getPhone() {

        return phone;
    }

    public String getEmail() {
        return email;
    }

    public com.google.firebase.Timestamp getCreationDate() {
        return creationDate;
    }
}
