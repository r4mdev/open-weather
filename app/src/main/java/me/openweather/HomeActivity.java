package me.openweather;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import me.openweather.*;
import me.openweather.FetchData;


public class HomeActivity extends AppCompatActivity {



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
            initialLogin();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void initialLogin() throws InterruptedException {
        TextView weather_text = findViewById(R.id.weather_text);
        ImageView weather_img = findViewById(R.id.weather_image);
        LinearLayout weather_linear_back = findViewById(R.id.weather_linear_back);
        LinearLayout wind_linear = findViewById(R.id.wind_linear);
        LinearLayout humidity_linear = findViewById(R.id.humidity_linear);
        LinearLayout sunrise_linear = findViewById(R.id.sunrise_linear);
        LinearLayout sunset_linear = findViewById(R.id.sunset_linear);

        weather_linear_back.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        wind_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        humidity_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        sunrise_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));
        sunset_linear.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));

        FetchData fetch = new FetchData();
        fetch.fetchJsonData();
    }

}
class SpinnerActivity extends HomeActivity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Toast t = Toast.makeText(getApplicationContext(), (CharSequence) parent.getItemAtPosition(pos), Toast.LENGTH_LONG);
        t.show();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

