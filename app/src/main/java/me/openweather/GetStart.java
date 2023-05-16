package me.openweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GetStart extends AppCompatActivity {
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.get_start);
        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean("activity_executed", Boolean.TRUE);
        ed.apply();
        
        Button btn = (Button) findViewById(R.id.get_start_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(), PlacesScreen.class);
                startActivity(i);
                finish();
            }
        });
    }
}