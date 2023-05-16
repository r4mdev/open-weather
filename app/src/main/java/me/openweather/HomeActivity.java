package me.openweather;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import me.openweather.*;


public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        initialLogin();
    }

    void initialLogin() {
        System.out.println("Hello, World!");
        System.out.println("@string/app_name");
    }

}
