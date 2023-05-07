package com.example.horizonapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private final List<Doctor> doctorList;
    private final Context context;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
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
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, genderTextView, experienceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            genderTextView = itemView.findViewById(R.id.gender);
            experienceTextView = itemView.findViewById(R.id.experience);
        }
    }
}

