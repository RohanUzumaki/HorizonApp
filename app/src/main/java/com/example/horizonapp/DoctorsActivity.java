package com.example.horizonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_doc);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDoctors = new ArrayList<>();
        mDoctorAdapter = new DoctorAdapter(DoctorsActivity.this, mDoctors);
        mRecyclerView.setAdapter(mDoctorAdapter);

        // Get the department name from the intent extra
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
}
