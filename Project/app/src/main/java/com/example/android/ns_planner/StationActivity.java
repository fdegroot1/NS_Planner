package com.example.android.ns_planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.ns_planner.api.ORSApiListener;
import com.example.android.ns_planner.api.ORSApiManager;
import com.example.android.ns_planner.data.Departure;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Calendar;

public class StationActivity extends AppCompatActivity implements ORSApiListener {

    private StationRecyclerViewAdapter stationRecyclerViewAdapter;
    private ArrayList<Departure> departures;
    private String LOGTAG = StationActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        Intent intent = getIntent();
        departures = (ArrayList<Departure>) intent.getSerializableExtra("departures");
        TextView stationName = findViewById(R.id.station_name);
        stationName.setText(intent.getStringExtra("station_name"));
        double myLocationLng = intent.getDoubleExtra("my_location_lng",0.0);
        double myLocationLat = intent.getDoubleExtra("my_location_lat",0.0);
        double stationLng = intent.getDoubleExtra("station_lng",0.0);
        double stationLat = intent.getDoubleExtra("station_lat",0.0);

        Log.d(LOGTAG, departures.toString());
        ORSApiManager apiManager = new ORSApiManager(this,this);
        apiManager.getRoute(myLocationLng,myLocationLat,stationLng,stationLat);
//        stationRecyclerViewAdapter = new StationRecyclerViewAdapter(departures, 0);
//        RecyclerView recyclerView = findViewById(R.id.station_recyclerview);
//        recyclerView.setAdapter(stationRecyclerViewAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onRoute(double travelTime) {
        Log.d(LOGTAG, Calendar.getInstance().getTime().toString());
        stationRecyclerViewAdapter = new StationRecyclerViewAdapter(departures, travelTime);
        RecyclerView recyclerView = findViewById(R.id.station_recyclerview);
        recyclerView.setAdapter(stationRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}