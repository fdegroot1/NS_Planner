package com.example.android.ns_planner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.ns_planner.api.NSApiListener;
import com.example.android.ns_planner.api.NSApiManager;
import com.example.android.ns_planner.api.ORSApiListener;
import com.example.android.ns_planner.api.ORSApiManager;
import com.example.android.ns_planner.data.Station;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NSApiListener, ORSApiListener {

    private String LOGTAG = MainActivity.class.getName();
    private ArrayList<Station> stations;
    private NSApiManager nsApiManager;
    private ORSApiManager orsApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stations = new ArrayList<>();
        //nsApiManager = new NSApiManager(this,this);
        //nsApiManager.getStations();
        orsApiManager = new ORSApiManager(this,this);
        orsApiManager.getRoute(8.681495,49.41461,8.687872,49.420318);
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
        nsApiManager.getDepartures(stations.get(10));
    }

    @Override
    public void onRoute() {

    }
}