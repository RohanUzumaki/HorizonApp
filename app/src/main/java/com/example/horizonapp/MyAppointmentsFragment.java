package com.example.horizonapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;



public class MyAppointmentsFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private AppointmentsAdapter mAdapter;
    private ArrayList<Appointment> mAppointmentList;

    // ...

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_appointments, container, false);

        mRecyclerView = v.findViewById(R.id.nav_my_appointments);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AppointmentsAdapter(mAppointmentList);
        mRecyclerView.setAdapter(mAdapter);


        // Get the appointments from Firebase and add them to the adapter
        mAppointmentList = new ArrayList<Appointment>();
        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments");


        appointmentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot appointmentSnapshot : snapshot.getChildren()) {
                    Appointment appointment = appointmentSnapshot.getValue(Appointment.class);
                    // Add the appointment to the list
                    mAppointmentList.add(appointment);
                }
                // Create the adapter and set it to the RecyclerView
                mAdapter = new AppointmentsAdapter(mAppointmentList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });

        return v;

    }
}