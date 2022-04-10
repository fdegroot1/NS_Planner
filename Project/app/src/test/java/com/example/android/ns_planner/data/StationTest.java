package com.example.android.ns_planner.data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StationTest {
    Station station = new Station("84005306", 51.9243, 4.4690, "Rotterdam Centraal");


    @Test
    public void getDepartures() {
        assertNull(station.getDepartures());
        ArrayList<Departure> departuresArrayList = new ArrayList<>();
        Departure departure = new Departure("name", "direction",
                "categoryName", "plannedDateTime", 10,
                "track", false);
        departuresArrayList.add(departure);
        station.setDepartures(departuresArrayList);
        assertNotNull(station.getDepartures());
    }

    @Test
    public void setDepartures() {
        assertNull(station.getDepartures());
        ArrayList<Departure> departuresArrayList = new ArrayList<>();
        Departure departure = new Departure("name", "direction",
                "categoryName", "plannedDateTime", 10,
                "track", false);
        departuresArrayList.add(departure);
        station.setDepartures(departuresArrayList);
        assertNotNull(station.getDepartures());
    }

    @Test
    public void getUICCode() {
        assertTrue(station.getUICCode()=="84005306");
    }

    @Test
    public void getLat() {
        assertTrue(station.getLat()==51.9243);
    }

    @Test
    public void getLng() {
        assertTrue(station.getLng()==4.4690);
    }

    @Test
    public void getName() {
        assertTrue(station.getName()=="Rotterdam Centraal");
    }
}