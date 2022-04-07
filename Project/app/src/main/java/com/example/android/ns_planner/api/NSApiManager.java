package com.example.android.ns_planner.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.ns_planner.data.Departure;
import com.example.android.ns_planner.data.Station;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NSApiManager {

    private Context context;
    private NSApiListener listener;
    private RequestQueue queue;
    private String LOGTAG = NSApiManager.class.getName();
    private String key =  "a477187c39e64c06803f8a613c8f620a";

    public NSApiManager(Context context, NSApiListener listener) {
        this.listener = listener;
        this.context = context;
        this.queue = Volley.newRequestQueue(context);

    }

    public void getStations(){
        //For now we are only interested in the Breda station and Rotterdam Lombardijen
        String url = "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v2/stations";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "STATIONS "+response.toString());

                        try {
                            JSONArray jsonArray = response.getJSONArray("payload");

                            //Test Code
//                            Log.d(LOGTAG, jsonArray.toString());
//                            Log.d(LOGTAG, jsonArray.get(0).toString());
//                            Log.d(LOGTAG, ((JSONObject)jsonArray.get(0)).getString("UICCode"));
//                            Log.d(LOGTAG, ((JSONObject)jsonArray.get(0)).getString("namen"));
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                String UICCode = jsonObject.getString("UICCode");
                                double lng = jsonObject.getDouble("lng");
                                double lat = jsonObject.getDouble("lat");
                                JSONObject names = jsonObject.getJSONObject("namen");
                                String name = names.getString("lang");

                                Station station = new Station(UICCode, lat, lng, name);
                                listener.onStation(station);
                                //Log.d(LOGTAG, UICCode+lng+lat+name);
                            }
                            //Log.d(LOGTAG, "Hello");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Ocp-Apim-Subscription-Key", key);

                return params;
            }
        };

        queue.add(request);


    }

    public void getDepartures(Station station){

        ArrayList<Departure> departuresArrayList = new ArrayList<>();

        //Requesting departures from given station
        String url = "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v2/departures?uicCode="+station.getUICCode();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(LOGTAG, "DEPARTURES "+response.toString());

                        //TODO Handle response
                        try {
                            JSONObject payload = response.getJSONObject("payload");
                            JSONArray departures = payload.getJSONArray("departures");
                            Log.d(LOGTAG, "DEPARTURES "+departures.get(0));
                            Log.d(LOGTAG, "DEPARTURES "+((JSONObject)departures.get(0)).getString("plannedTrack"));
                            for(int i=0; i<departures.length(); i++){
                                JSONObject departureJSON = (JSONObject) departures.get(i);
                                String name = departureJSON.getString("name");
                                String direction = departureJSON.getString("direction");
                                JSONObject product = departureJSON.getJSONObject("product");
                                String categoryName = product.getString("shortCategoryName");
                                String plannedDateTime = departureJSON.getString("plannedDateTime");
                                int offset = departureJSON.getInt("plannedTimeZoneOffset");
                                String track = departureJSON.getString("plannedTrack");
                                boolean cancelled = departureJSON.getBoolean("cancelled");

                                Departure departure = new Departure(name, direction, categoryName, plannedDateTime, offset, track, cancelled);
                                departuresArrayList.add(departure);
                            }
                            station.setDepartures(departuresArrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Ocp-Apim-Subscription-Key", key);

                return params;
            }
        };

        queue.add(request);

    }
}
