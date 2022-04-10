package com.example.android.ns_planner;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.ns_planner.data.Departure;

import org.osmdroid.util.GeoPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class StationRecyclerViewAdapter extends RecyclerView.Adapter<StationRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Departure> departures;
    private double time;

    public StationRecyclerViewAdapter(ArrayList<Departure> departures, double time) {
        this.departures = departures;
        this.time = time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_recyclerview_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get element from your dataset at this position and replace the contents of the view with that element
        String timeString = departures.get(position).getPlannedDateTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = null;
        try {
            date = format.parse(timeString);
            Log.d("Adapter", date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar now = Calendar.getInstance();
        Log.d("Adapter", "NOWBEFOREADDEDTIME"+now.getTime().toString());
        now.add(Calendar.SECOND, (int)time);
        now.add(Calendar.HOUR,2);
        Log.d("Adapter", "NOW"+now.getTime().toString());
        if(now.getTime().after(date)){
            holder.trainName.setTextColor(Color.RED);
            holder.direction.setTextColor(Color.RED);
            holder.categoryName.setTextColor(Color.RED);
            holder.track.setTextColor(Color.RED);
            holder.leaveTime.setTextColor(Color.RED);
        }
        holder.trainName.setText(departures.get(position).getName());
        holder.direction.setText(departures.get(position).getDirection());
        holder.categoryName.setText(departures.get(position).getCategoryName());
        holder.track.setText(departures.get(position).getTrack());
        holder.leaveTime.setText(departures.get(position).getPlannedDateTime());

    }

    @Override
    public int getItemCount() {
        return departures.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView trainName;
        private TextView direction;
        private TextView categoryName;
        private TextView track;
        private TextView leaveTime;

        public ViewHolder(View view){
            super(view);

            trainName = view.findViewById(R.id.train_name);
            direction = view.findViewById(R.id.direction);
            categoryName = view.findViewById(R.id.category_name);
            track = view.findViewById(R.id.track);
            leaveTime = view.findViewById(R.id.leave_time);
        }

    }
}
