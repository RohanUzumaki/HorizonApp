package com.example.horizonapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class BookingSuccessActivity extends AppCompatActivity {

    private static final String EMAIL_ADDRESS = "horizon.telemedicine.app0@gmail.com";
    private static final String EMAIL_PASSWORD = "Horizon@01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(BookingSuccessActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // 3 seconds
    }

}


