package me.openweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
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
        btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFE0E0E0));

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                SharedPreferences pref = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
                pref.edit().putBoolean("activity_executed", true).commit();
                i = new Intent(getApplicationContext(), PlacesScreen.class);
                startActivity(i);
                finish();
            }
        });
    }
}