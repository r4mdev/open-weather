package me.openweather;

import android.content.Context;
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


public class FetchData {

    String temperature;
    String windspeed;
    String humidity;
    String sunrise;
    String sunset;
    Integer weather_code;
    Boolean is_day;

    ArrayList<String> nxt_10_weather = new ArrayList<>();

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
                System.out.println(CityData);
            }
        },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT);
                    toast.show();
                });
        queue.add(strReq);
    }

    FetchData(Context context ,Float lat, Float lng) {
        String URL = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lng + "&hourly=temperature_2m";
    }

    public String fetchJsonData() {
        final String[] JsonData = new String[1];
        RequestQueue queue = Volley.newRequestQueue(this.context);

        // request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JsonData[0] = response.toString();
                        try {
                            JSONObject Weather_Json = new JSONObject(JsonData[0]);
                            JSONObject Current_weather = Weather_Json.getJSONObject("current_weather");

                            temperature = String.valueOf(Current_weather.getDouble("temperature"));
                            windspeed = String.valueOf(Current_weather.getDouble("windspeed"));
                            humidity = String.valueOf(Current_weather.getDouble("winddirection"));
                            is_day = Current_weather.getBoolean("is_day");
                            weather_code = Current_weather.getInt("weathercode");


                            System.out.println(temperature);
                            System.out.println(windspeed);
                            System.out.println(humidity);

                            // is_day = Current_weather.getBoolean("is_day");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT);
                    toast.show();
                });
        queue.add(stringRequest);
        return JsonData[0];
    }
}
