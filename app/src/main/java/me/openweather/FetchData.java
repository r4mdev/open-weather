package me.openwhetaher;

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


public class FetchData {

    String temperature;
    String windspeed;
    String winddirection;
    Boolean is_day;

    public String fetchJsonData(Context context, String url) {
        final String[] JsonData = new String[1];
        RequestQueue queue = Volley.newRequestQueue(context);
        int duration = Toast.LENGTH_SHORT;
        // request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JsonData[0] = response.toString();
                        try {
                            JSONObject Weather_Json = new JSONObject(JsonData[0]);
                            JSONObject Current_weather = Weather_Json.getJSONObject("current_weather");

                            temperature = String.valueOf(Current_weather.getDouble("temperature"));
                            windspeed = String.valueOf(Current_weather.getDouble("windspeed"));
                            winddirection = String.valueOf(Current_weather.getDouble("winddirection"));

                            System.out.println(temperature);
                            System.out.println(windspeed);
                            System.out.println(winddirection);

                            // is_day = Current_weather.getBoolean("is_day");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                },
                error -> {
                    Toast toast = Toast.makeText(context, "Failed to fetch data", duration);
                    toast.show();
                });
        queue.add(stringRequest);
        return JsonData[0];
    }
}
