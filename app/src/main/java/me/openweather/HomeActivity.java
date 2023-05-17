package me.openweather;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import me.openweather.*;
import me.openweather.FetchData;

import java.util.ArrayList;
import java.util.concurrent.*;


public class HomeActivity extends AppCompatActivity {

    String JsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        Spinner spinner = (Spinner) findViewById(R.id.city_dropdown_menu);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        try {
            initialLogic();
            weatherLogic();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void initialLogic() throws InterruptedException {

        LinearLayout weather_linear_back = findViewById(R.id.weather_linear_back);
        LinearLayout wind_linear = findViewById(R.id.wind_linear);
        LinearLayout humidity_linear = findViewById(R.id.humidity_linear);
        LinearLayout sunrise_linear = findViewById(R.id.sunrise_linear);
        LinearLayout sunset_linear = findViewById(R.id.sunset_linear);


        System.out.println("Hello, Open Weather!");
        Log.d("APP", "Hello Open Weather!");

        weather_linear_back.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        wind_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        humidity_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        sunrise_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        sunset_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));

    }

    void weatherLogic() {

        TextView weather_text = findViewById(R.id.weather_text);
        TextView humidity_text = findViewById(R.id.humidity_text);
        TextView wind_speed_text = findViewById(R.id.wind_speed_text);
        TextView sunrise_text = findViewById(R.id.sunrise_text);
        TextView sunset_text = findViewById(R.id.sunset_text);

        String temperature;
        String windspeed;
        String humidity;
        String sunrise;
        String sunset;
        int weather_code;
        int is_day;

        ArrayList<String> nxt_10_weather = new ArrayList<>();

        FetchData fetch = new FetchData(HomeActivity.this.getApplicationContext(), (float) 30.74, (float) 76.64);
        this.JsonData = fetch.fetchJsonData(getApplicationContext());

        try {
            this.JsonData = "{\"latitude\":30.75,\"longitude\":76.625,\"generationtime_ms\":0.2429485321044922,\"utc_offset_seconds\":19800,\"timezone\":\"Asia/Kolkata\",\"timezone_abbreviation\":\"IST\",\"elevation\":312.0,\"current_weather\":{\"temperature\":28.8,\"windspeed\":2.3,\"winddirection\":108.0,\"weathercode\":3,\"is_day\":0,\"time\":\"2023-05-17T23:30\"},\"daily_units\":{\"time\":\"iso8601\",\"apparent_temperature_max\":\"°C\",\"weathercode\":\"wmo code\",\"sunrise\":\"iso8601\",\"sunset\":\"iso8601\"},\"daily\":{\"time\":[\"2023-05-17\",\"2023-05-18\",\"2023-05-19\",\"2023-05-20\",\"2023-05-21\",\"2023-05-22\",\"2023-05-23\"],\"apparent_temperature_max\":[40.5,39.1,40.4,41.3,42.7,45.1,43.4],\"weathercode\":[3,80,1,2,1,1,80],\"sunrise\":[\"2023-05-17T05:26\",\"2023-05-18T05:25\",\"2023-05-19T05:25\",\"2023-05-20T05:24\",\"2023-05-21T05:24\",\"2023-05-22T05:23\",\"2023-05-23T05:23\"],\"sunset\":[\"2023-05-17T19:13\",\"2023-05-18T19:14\",\"2023-05-19T19:14\",\"2023-05-20T19:15\",\"2023-05-21T19:15\",\"2023-05-22T19:16\",\"2023-05-23T19:17\"]}}\n";
            JSONObject Weather_Json = new JSONObject(this.JsonData);
            JSONObject Current_weather = Weather_Json.getJSONObject("current_weather");

            temperature = String.valueOf(Current_weather.getDouble("temperature"));
            windspeed = String.valueOf(Current_weather.getDouble("windspeed"));
            humidity = String.valueOf(Current_weather.getDouble("winddirection"));
            is_day = Current_weather.getInt("is_day");
            weather_code = Current_weather.getInt("weathercode");


            weather_text.setText(temperature + " °C");
            wind_speed_text.setText(windspeed);
            humidity_text.setText(humidity);

            // is_day = Current_weather.getBoolean("is_day");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    
    void weatherIMG(int code) {
        ImageView weather_image = findViewById(R.id.weather_image);
        if (code == 0) {
            weather_image.setImageDrawable(R.drawable.day_clear);
        } else if (code == ) {
            
        }
    }

}
