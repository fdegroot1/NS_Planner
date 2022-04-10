package com.example.android.ns_planner.data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Departure implements Serializable {
    String name;
    String direction;
    String categoryName;
    String plannedDateTime;
    int timeZoneOffset;
    String track;
    boolean cancelled;

    public Departure(String name, String direction, String categoryName, String plannedDateTime, int timeZoneOffset, String track, boolean cancelled) {
        this.name = name;
        this.direction = direction;
        this.categoryName = categoryName;
        this.plannedDateTime = plannedDateTime;
        this.timeZoneOffset = timeZoneOffset;
        this.track = track;
        this.cancelled = cancelled;
    }

    public String getName() {

        return name;
    }

    public String getDirection() {
        return direction;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getPlannedDateTime() {
        return plannedDateTime;
    }

    public int getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public String getTrack() {
        return track;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
