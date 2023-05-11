package com.example.horizonapp;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder> {

    private ArrayList<Appointment> mAppointmentList;

    public static class AppointmentsViewHolder extends RecyclerView.ViewHolder {
        public TextView mDateTextView;
        public TextView mTimeSlotTextView;
        public TextView mDepartmentTextView;
        public TextView mDoctorTextView;
        public TextView mExperienceTextView;
        public TextView mPatientTextView;

        public TextView mPatientAgeTextView;

        public AppointmentsViewHolder(View itemView) {
            super(itemView);
            mDateTextView = itemView.findViewById(R.id.date_text_view);
            mTimeSlotTextView = itemView.findViewById(R.id.time_slot_text_view);
            mDepartmentTextView = itemView.findViewById(R.id.department_text_view);
            mDoctorTextView = itemView.findViewById(R.id.doctor_name_text_view);
            mExperienceTextView = itemView.findViewById(R.id.experience_text_view);
            mPatientAgeTextView = itemView.findViewById(R.id.patient_age_text_view);
            mPatientTextView = itemView.findViewById(R.id.patient_name_text_view);
        }
    }

    public AppointmentsAdapter(ArrayList<Appointment> appointmentList) {
        mAppointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointments, parent, false);
        AppointmentsViewHolder viewHolder = new AppointmentsViewHolder(v);
        return new AppointmentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsViewHolder holder, int position) {
        Appointment currentItem = mAppointmentList.get(position);

        holder.mDateTextView.setText(currentItem.getAppointmentDate());
        holder.mTimeSlotTextView.setText(currentItem.getAppointmentTimeSlot());
        holder.mDepartmentTextView.setText(currentItem.getDepartment());
        holder.mDoctorTextView.setText(currentItem.getDoctorName());
        holder.mExperienceTextView.setText(currentItem.getDoctorExperience());
        holder.mPatientTextView.setText(currentItem.getPatientName() + ", " + currentItem.getPatientAge());
    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }
}

