package com.example.android.ns_planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.ns_planner.data.Departure;

import java.util.ArrayList;

public class StationActivity extends AppCompatActivity {

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
        Log.d(LOGTAG, departures.toString());
        stationRecyclerViewAdapter = new StationRecyclerViewAdapter(departures);
        RecyclerView recyclerView = findViewById(R.id.station_recyclerview);
        recyclerView.setAdapter(stationRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}