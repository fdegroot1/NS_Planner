package com.example.android.ns_planner.data;

import java.util.ArrayList;

public class Station {
    private String UICCode;
    private double lat;
    private double lng;
    private String name;
    private ArrayList<Departure> departures;

    public ArrayList<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(ArrayList<Departure> departures) {
        this.departures = departures;
    }

    public Station(String UICCode, double lat, double lng, String name) {
        this.UICCode = UICCode;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public String getUICCode() {
        return UICCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }
}
