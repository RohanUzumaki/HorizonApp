package com.example.horizonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class OnlineConsActivity extends AppCompatActivity {

    Button btnVideoCons;
    Button btnChatDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_cons);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) item -> {
            int id = item.getItemId();
            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (id) {
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_notifications:
                    replaceFragment(new NotificationsFragment());
                    break;
                case R.id.nav_settings:
                    replaceFragment(new SettingsFragment());
                    break;
                case R.id.change_password:
                    replaceFragment(new ChangePasswordFragment());
                    break;
                case R.id.nav_logout:
                    Toast.makeText(this, "Logout is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return true;
            }
            return true;
        });

        btnVideoCons = findViewById(R.id.btnVideo);
        btnChatDoc = findViewById(R.id.btnChat);

        btnVideoCons.setOnClickListener(task -> startActivity(new Intent(OnlineConsActivity.this, video_consultation.class)));
        btnChatDoc.setOnClickListener(task -> startActivity(new Intent(OnlineConsActivity.this, ChatWithDoc.class)));

    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}