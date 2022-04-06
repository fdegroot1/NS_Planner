package com.example.android.ns_planner.api;

import com.example.android.ns_planner.data.Station;

public interface NSApiListener {
    void onStation(Station station);
    void onDeparture();
}
