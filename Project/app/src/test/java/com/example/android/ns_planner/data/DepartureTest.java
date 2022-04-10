package com.example.android.ns_planner.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartureTest {
    Departure departure = new Departure("name", "direction",
            "categoryName", "plannedDateTime", 10,
            "track", false);

    @Test
    public void getName() {
        assertTrue(departure.getName()=="name");
    }

    @Test
    public void getDirection() {
        assertTrue(departure.getDirection()=="direction");
    }

    @Test
    public void getCategoryName() {
        assertTrue(departure.getCategoryName()=="categoryName");
    }

    @Test
    public void getPlannedDateTime() {
        assertTrue(departure.getPlannedDateTime()=="plannedDateTime");
    }

    @Test
    public void getTimeZoneOffset() {
        assertTrue(departure.getTimeZoneOffset()==10);
    }

    @Test
    public void getTrack() {
        assertTrue(departure.getTrack()=="track");
    }

    @Test
    public void isCancelled() {
        assertFalse(departure.isCancelled());
    }
}