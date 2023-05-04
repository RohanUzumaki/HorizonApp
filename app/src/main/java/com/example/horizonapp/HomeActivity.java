package com.example.horizonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity  {

  Button bookbtn,consbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialToolbar toolbar= findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) item -> {
            int id= item.getItemId();
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (id){
                case R.id.nav_home:
                    Toast.makeText(this, "Home is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_message:
                    Toast.makeText(this, "Message is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_settings:
                    Toast.makeText(this, "Settings is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.change_password:
                    Toast.makeText(this, "Change Password is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_logout:
                    Toast.makeText(this, "Logout is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return true;
            }
            return true;
        });

        bookbtn = findViewById(R.id.bookBtn);
        consbtn = findViewById(R.id.consBtn);
       bookbtn.setOnClickListener(task -> startActivity(new Intent(HomeActivity.this, DocListActivity.class)));
        consbtn.setOnClickListener(task -> startActivity(new Intent(HomeActivity.this,OnlineConsActivity.class)));
    }
}


