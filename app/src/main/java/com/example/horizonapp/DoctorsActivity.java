package com.example.horizonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorsActivity extends AppCompatActivity {

    private DoctorAdapter mDoctorAdapter;
    private List<Doctor> mDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));


        navigationView.setNavigationItemSelectedListener(item -> {
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
                case R.id.nav_logout:
                    Toast.makeText(this, "Logout is Clicked", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return true;
            }
            return true;
        });

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        DatabaseReference userRef = databaseRef.child("Patient").child(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the user's information and update the navigation header
                String firstName = dataSnapshot.child("firstName").getValue(String.class);
                String lastName = dataSnapshot.child("lastName").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String phone = dataSnapshot.child("phone").getValue(String.class);

                // Update the navigation header with the retrieved information
                View headerView = navigationView.getHeaderView(0);
                TextView nameTextView = headerView.findViewById(R.id.name);
                nameTextView.setText(firstName + " " + lastName);

                TextView emailTextView = headerView.findViewById(R.id.emailAddress);
                emailTextView.setText(email);

                TextView phoneTextView = headerView.findViewById(R.id.phoneNumber);
                phoneTextView.setText("+91-" + phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(DoctorsActivity.this, "Error Retrieving info", Toast.LENGTH_SHORT).show();
            }
        });


        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_doc);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDoctors = new ArrayList<>();
        mDoctorAdapter = new DoctorAdapter(DoctorsActivity.this, mDoctors);
        mRecyclerView.setAdapter(mDoctorAdapter);

//         Get the department name from the intent extra
        Intent intent = getIntent();
        String departmentName;
        if (intent != null && intent.hasExtra("departmentName")) {
            departmentName = intent.getStringExtra("departmentName");
        } else {
            // Show an error message or go back to the previous activity
            Toast.makeText(this, "Department not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        setSupportActionBar(findViewById(R.id.topAppBar));

        // Set the title of the action bar to the department name
        Objects.requireNonNull(getSupportActionBar()).setTitle(departmentName);

        // Get the doctors for the department from the database
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doctor").child(departmentName);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mDoctors.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = postSnapshot.getValue(Doctor.class);
                    mDoctors.add(doctor);
                }
                mDoctorAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DoctorsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        if (mDoctors == null) {
            mDoctors = new ArrayList<>();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
