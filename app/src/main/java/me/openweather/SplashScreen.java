package me.openweather;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
                if(pref.getBoolean("activity_executed",false)){
                    i = new Intent(getApplicationContext(), GetStart.class);
                } else {
                    i = new Intent(getApplicationContext(), HomeActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 3500);

    };
}
