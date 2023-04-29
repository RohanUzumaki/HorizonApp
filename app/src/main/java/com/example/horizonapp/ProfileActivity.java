package com.example.horizonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Create an instance of the ProfileFragment
        ProfileFragment profileFragment = new ProfileFragment();

        // Begin a FragmentTransaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace the FrameLayout with the ProfileFragment instance
        transaction.replace(R.id.fragment_container, profileFragment);

        // Commit the transaction
        transaction.commit();

        backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> onBackPressed());
    }
}
