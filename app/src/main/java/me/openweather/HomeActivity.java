package me.openweather;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import me.openweather.*;
import me.openweather.FetchData;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.*;


public class HomeActivity extends AppCompatActivity {

    String JsonData;

    private Dialog prog;
    private SpannableString s;

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

        weather_linear_back.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 15, 0xFFE0E0E0));
        wind_linear.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 15, 0xFFE0E0E0));
        humidity_linear.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 15, 0xFFE0E0E0));
        sunrise_linear.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 15, 0xFFE0E0E0));
        sunset_linear.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 15, 0xFFE0E0E0));

    }

    @SuppressLint("SetTextI18n")
    void weatherIMG(int code, int is_day) {
        ImageView weather_image = findViewById(R.id.weather_image);
        TextView weather_summary = findViewById(R.id.weather_text_summary);
        if (code == 0) {
            weather_image.setImageResource(R.drawable.day_clear);
            weather_summary.setText("clear cky");
        } else if (code >= 1 || code <= 3) {
            weather_image.setImageResource(R.drawable.day_rain_1);
            weather_summary.setText("partly cloudy");
        } else if (code == 45 || code == 48) {
            weather_image.setImageResource(R.drawable.day_rain_20);
            weather_summary.setText("foggy");
        } else if (code == 61 || code == 63 || code == 65) {
            weather_image.setImageResource(R.drawable.day_rain_9);
            weather_summary.setText("rain");
        } else if (code == 66 || code == 67) {
            weather_image.setImageResource(R.drawable.day_rain_8);
            weather_summary.setText("heavy rain");
        } else if (code == 71 || code == 73 || code == 75 || code == 77) {
            weather_image.setImageResource(R.drawable.day_rain_27);
            weather_summary.setText("snow fall");
        } else if (code == 80 || code == 81 || code == 82) {
            weather_image.setImageResource(R.drawable.day_rain_17);
            weather_summary.setText("rain shower");
        } else if (code == 95) {
            weather_image.setImageResource(R.drawable.day_rain_8);
            weather_summary.setText("thunderstroms");
        } else if (code == 96 || code == 99) {
            weather_image.setImageResource(R.drawable.day_rain_6);
            weather_summary.setText("heavy thunderstroms");
        } else if ((code == 61 || code == 63 || code == 65) && is_day == 0) {
            weather_image.setImageResource(R.drawable.day_rain_26);
            weather_summary.setText("rain");
        } else {
            weather_image.setImageResource(R.drawable.day_rain_4);
            weather_summary.setText("unidentified");
        }
    }

    // For progress bar
    public void progressbarShow() {
        prog = new Dialog(HomeActivity.this); prog.requestWindowFeature(Window.FEATURE_NO_TITLE); prog.setContentView(R.layout.view);
        prog.setCancelable(true); prog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
        prog.show();
    }


    public void progresbarDimiss() {
        if(prog != null)
        {
            prog.dismiss();
        }
    }


    void weatherLogic() {



        ArrayList<String> nxt_10_weather = new ArrayList<>();

        FetchData fetch = new FetchData(getApplicationContext(), (float) 12.21, (float) 12.21) {
            @Override
            public void onResponse(String response) {
                HomeActivity.this.JsonData = response;
                onWeatherData();
            }
        };
        fetch.fetchJsonData(getApplicationContext());
        Log.d("APP", "HomeAcivitiy: " + this.JsonData);


    }
    void onWeatherData() {
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

        try {


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

            weatherIMG(weather_code, is_day);

            // is_day = Current_weather.getBoolean("is_day");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

