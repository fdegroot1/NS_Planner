package com.example.android.ns_planner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.ns_planner.data.Departure;

import java.util.ArrayList;

public class StationRecyclerViewAdapter extends RecyclerView.Adapter<StationRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Departure> departures;

    public StationRecyclerViewAdapter(ArrayList<Departure> departures) {
        this.departures = departures;
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
