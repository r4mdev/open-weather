package me.openweather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import kotlin.jvm.Synchronized;


public class FetchData {

    final String[] JsonData = new String[1];
    Context context;
    String URL;


    FetchData( Context context, String city ) {
        this.context = context;
        RequestQueue queue = Volley.newRequestQueue(context);
        this.URL = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        StringRequest strReq = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String CityData = response.toString();
                try {
                    JSONArray cities = new JSONArray(CityData);
                    JSONObject city = new JSONObject((Map) cities.getJSONObject(0));
                    float lat = (float) city.getDouble("latitude");
                    float lng = (float) city.getDouble("longitude");
                    URL = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lng + "&hourly=temperature_2m";
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to  fetch city data", Toast.LENGTH_SHORT);
                    toast.show();
                });
        queue.add(strReq);
    }

    FetchData(Context context, Float lat, Float lng) {
        this.context = context;
        this.URL = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lng + "&current_weather=true&daily=apparent_temperature_max,weathercode,sunrise,sunset&timezone=Asia/Kolkata";
        Log.d("APP", URL);
    }

    RequestQueue queue;

    public void fetchJsonData(Context context) {
        queue = Volley.newRequestQueue(context);
        final String[] JsonData = {new String()};

        // request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        JsonData[0] = response.toString();
                        Log.d("APP", "Response: " + response.toString());
                        FetchData.this.onResponse(response.toString());
                    }
                },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT);
                    toast.show();
                });
        queue.add(stringRequest);
    }
    public void onResponse(String response) {}
}
