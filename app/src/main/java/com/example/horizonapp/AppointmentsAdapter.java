package com.example.horizonapp;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.util.Log;


public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder> {

    private final ArrayList<Appointment> mAppointmentList;

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
       this.mAppointmentList = appointmentList;
    }



    @NonNull
    @Override
    public AppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointments, parent, false);
        return new AppointmentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsViewHolder holder, int position) {
        Appointment appointment = mAppointmentList.get(position);

        holder.mDateTextView.setText(appointment.getAppointmentDate());
        holder.mTimeSlotTextView.setText(appointment.getAppointmentTimeSlot());
        holder.mDepartmentTextView.setText(appointment.getDepartment());
        holder.mDoctorTextView.setText(appointment.getDoctorName());
        holder.mExperienceTextView.setText(appointment.getDoctorExperience());
        holder.mPatientTextView.setText(appointment.getPatientName() + ", " + appointment.getPatientAge());
        Log.d("AppointmentsAdapter", "Binding appointment data at position " + position);

    }

    @Override
    public int getItemCount() {
        if(mAppointmentList == null){
            return  0;
        }

        return mAppointmentList.size();
    }
}
