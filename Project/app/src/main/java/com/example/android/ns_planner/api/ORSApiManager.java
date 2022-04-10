package com.example.android.ns_planner.api;

import android.content.Context;
import android.location.Geocoder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ORSApiManager {

    private Context context;
    private ORSApiListener listener;
    private RequestQueue queue;
    private String LOGTAG = ORSApiManager.class.getName();
    private String key = "5b3ce3597851110001cf6248face8730c8bf46ef8ce52c211f720703";

    public ORSApiManager(Context context, ORSApiListener listener) {
        this.context = context;
        this.listener = listener;
        this.queue = Volley.newRequestQueue(context);
    }

    public void getRoute(double lngPoint1, double latPoint1, double lngPoint2, double latPoint2){
        String url = "https://api.openrouteservice.org/v2/directions/foot-walking?api_key="+key+"&start="+lngPoint1+","+latPoint1+"&end="+lngPoint2+","+latPoint2;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(LOGTAG, jsonObject.toString());
                try {
                    //Log.d(LOGTAG, jsonObject.getJSONArray("features").toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("features");
                    JSONObject summary = (JSONObject) jsonArray.get(0);
                    //Log.d(LOGTAG, summary.toString());
                    JSONObject properties = summary.getJSONObject("properties");
                    //Log.d(LOGTAG, properties.toString());
                    JSONArray segments = properties.getJSONArray("segments");
                    //Log.d(LOGTAG, segments.toString());
                    JSONObject info = (JSONObject) segments.get(0);
                    double travelTime = info.getDouble("duration");
                    Log.d(LOGTAG, "Travel time in seconds: "+travelTime);
                    Log.d(LOGTAG, "Travel time in minutes: "+travelTime/60);
                    listener.onRoute(travelTime);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });

        queue.add(request);
    }



}
