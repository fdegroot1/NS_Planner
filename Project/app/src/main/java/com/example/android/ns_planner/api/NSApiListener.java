package com.example.android.ns_planner.api;

import com.example.android.ns_planner.data.Departure;
import com.example.android.ns_planner.data.Station;

import java.util.ArrayList;

public interface NSApiListener {
    void onStation(Station station);
    void onDeparture(Station station);
}
