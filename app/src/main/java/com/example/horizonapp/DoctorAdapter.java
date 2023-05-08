package com.example.horizonapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private final List<Doctor> doctorList;
    private final Context context;

    private final String departmentName;

    public DoctorAdapter(Context context, List<Doctor> doctorList, String departmentName) {
        this.context = context;
        this.doctorList = doctorList;
        this.departmentName = departmentName;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.nameTextView.setText(doctor.getName());
        holder.genderTextView.setText(doctor.getGender());
        holder.experienceTextView.setText(doctor.getExperience());

        holder.bookNowButton.setOnClickListener(view -> {
            // Add the logic for booking a doctor appointment here
            Intent intent = new Intent(context, DoctorProfileActivity.class);

            // Put any data you need to pass to the DoctorProfileActivity using intent.putExtra()
            intent.putExtra("DEPARTMENT_NAME", departmentName);
            intent.putExtra("DOCTOR_NAME", doctor.getName());
            intent.putExtra("DOCTOR_EXPERIENCE", doctor.getExperience());
            // Start the DoctorProfileActivity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, genderTextView, experienceTextView;
        Button bookNowButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            genderTextView = itemView.findViewById(R.id.gender);
            experienceTextView = itemView.findViewById(R.id.experience);
            bookNowButton = itemView.findViewById(R.id.buttonBookNow);
        }
    }
}

