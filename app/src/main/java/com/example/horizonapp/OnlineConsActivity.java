package com.example.horizonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class OnlineConsActivity extends AppCompatActivity {

    Button btnVideoCons;
    Button btnChatDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_cons);
        btnVideoCons = findViewById(R.id.btnVideo);
        btnChatDoc = findViewById(R.id.btnChat);

        btnVideoCons.setOnClickListener(task -> startActivity(new Intent(OnlineConsActivity.this, VideoConsultation.class)));
        btnChatDoc.setOnClickListener(task -> startActivity(new Intent(OnlineConsActivity.this, ChatWithDoc.class)));

    }
}