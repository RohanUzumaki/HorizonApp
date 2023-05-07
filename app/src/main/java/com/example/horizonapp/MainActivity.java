package com.example.horizonapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 3000; // 3 seconds
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        new Handler().postDelayed(() -> {
            if (sharedPreferences.getBoolean("rememberMe", false)) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            finish();
        }, SPLASH_TIME_OUT);
    }
}
