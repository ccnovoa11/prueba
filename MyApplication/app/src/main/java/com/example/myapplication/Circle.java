package com.example.myapplication;

import com.google.firebase.firestore.GeoPoint;

import java.text.ParseException;

public class Circle {
    private GeoPoint location;
    private String name;
    private double lat;
    private double lon;

    public Circle() {
        //Vacio :v
    }

    public Circle(String name, double lat, double lon) throws ParseException {
        this.location = new GeoPoint(lat, lon);
        this.name = name;

    }

    public String getName() {

        return name;
    }


    public GeoPoint getLocation() {
        return location;
    }
}
