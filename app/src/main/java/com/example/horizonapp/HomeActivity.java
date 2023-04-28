package com.example.horizonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {
    Button btnBook;
    Button btnOnlineCons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavigationView);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.profile) {
                    selectedFragment = new ProfileFragment();
                } else {
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedFragment);
                transaction.commit();

                return true;
            }
        });

        btnBook = findViewById(R.id.bookBtn);
        btnOnlineCons = findViewById(R.id.consBtn);

        btnBook.setOnClickListener(task -> startActivity(new Intent(HomeActivity.this, DocListActivity.class)));
        btnOnlineCons.setOnClickListener(task -> startActivity(new Intent(HomeActivity.this, OnlineConsActivity.class)));
    }

}