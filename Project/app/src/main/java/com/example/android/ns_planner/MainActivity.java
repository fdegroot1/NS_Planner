package com.example.android.ns_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.ns_planner.api.NSApiListener;
import com.example.android.ns_planner.api.NSApiManager;
import com.example.android.ns_planner.data.Station;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NSApiListener {

    private String LOGTAG = MainActivity.class.getName();
    private ArrayList<Station> stations;
    private NSApiManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stations = new ArrayList<>();
        manager = new NSApiManager(this,this);
        manager.getStations();
    }

    @Override
    public void onStation(Station station) {
        Log.d(LOGTAG, "New station added name = "+station.getName());
        stations.add(station);
    }

    @Override
    public void onDeparture() {

    }

    public void onClick(View view) {
        manager.getDepartures(stations.get(10));
    }
}