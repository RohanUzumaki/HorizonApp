package com.example.horizonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {
    Button btnBook;
    Button btnOnlineCons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnBook = findViewById(R.id.bookBtn);
        btnOnlineCons = findViewById(R.id.consBtn);

        btnBook.setOnClickListener(task -> startActivity(new Intent(HomeActivity.this, DocListActivity.class)));
        btnOnlineCons.setOnClickListener(task -> startActivity(new Intent(HomeActivity.this, OnlineConsActivity.class)));
    }

}