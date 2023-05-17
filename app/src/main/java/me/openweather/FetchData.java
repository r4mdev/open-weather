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
    String URL = "https://api.open-meteo.com/v1/forecast?latitude=52.52437&longitude=13.41053&current_weather=true&daily=apparent_temperature_max,weathercode,sunrise,sunset&timezone=Europe/Berlin";

  /*
    FetchData( Context context, String city ) {
        this.context = context;
        RequestQueue queue = Volley.newRequestQueue(context);

        String cityURL = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

        StringRequest strReq = new StringRequest(Request.Method.GET, cityURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String CityData = response.toString();
                try {
                    Log.i("MYTAG", response.toString());
                    // Parsing the response
                    JSONObject result = new JSONObject(CityData);
                    JSONArray cities = result.getJSONArray("results");
                    JSONObject city = cities.getJSONObject(0);

                    float lat = (float) city.getDouble("latitude");
                    float lng = (float) city.getDouble("longitude");
                    String timezone = city.getString("timezone");
                    URL = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lng + "&current_weather=true&daily=apparent_temperature_max,weathercode,sunrise,sunset&timezone=" + timezone;

                    Log.i("MYTAG", "CITY: " + city.getString("name"));
                    Log.i("MYTAG", URL);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to fetch city data", Toast.LENGTH_SHORT);
                    toast.show();
                });
        queue.add(strReq);
    }


    FetchData(Context context ,Float lat, Float lng) {
        this.URL = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lng + "&current_weather=true&daily=apparent_temperature_max,weathercode,sunrise,sunset";
    }
    */



    void fetchJsonData() {
        final String[] JsonData = new String[1];
        RequestQueue queue = Volley.newRequestQueue(this.context);
        Log.i("MYTAG", "I am running...");

        // request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JsonData[0] = response.toString();

                        Log.i("MYTAG", JsonData[0]);
/*

                        try {
                            JSONObject Weather_Json = new JSONObject(JsonData[0]);
                            JSONObject Current_weather = Weather_Json.getJSONObject("current_weather");

                            temperature = String.valueOf(Current_weather.getDouble("temperature"));
                            windspeed = String.valueOf(Current_weather.getDouble("windspeed"));
                            humidity = String.valueOf(Current_weather.getDouble("winddirection"));
                            is_day = Current_weather.getBoolean("is_day");
                            weather_code = Current_weather.getInt("weathercode");


                            Log.i("MYTAG", "Temprature: " + temperature);
                            Log.i("MYTAG", "WindSpeed: " + windspeed);
                            Log.i("MYTAG", "HUmidity: " + humidity);
                            Log.i("MYTAG", "weathercode:" + weather_code);
                            Log.i("MYTAG", "Is day: " + is_day);



                            // is_day = Current_weather.getBoolean("is_day");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            */

                    }

                },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to fetch weather data", Toast.LENGTH_SHORT);
                    toast.show();
                });
        queue.add(stringRequest);
    }

}
