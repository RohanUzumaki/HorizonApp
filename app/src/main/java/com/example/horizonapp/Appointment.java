package com.example.horizonapp;

public class Appointment {
    private String doctorName;
    private String department;
    private String doctorExperience;
    private String patientName;
    private int patientAge;
    private String appointmentDate;
    private String appointmentTimeSlot;
    public Appointment() {
        // Required empty public constructor
    }

    public Appointment(String doctorName, String department, String doctorExperience,
                       String patientName, int patientAge, String appointmentDate, String appointmentTimeSlot) {
        this.doctorName = doctorName;
        this.department = department;
        this.doctorExperience = doctorExperience;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.appointmentDate = appointmentDate;
        this.appointmentTimeSlot = appointmentTimeSlot;
    }

    // getters and setters for all fields
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctorExperience() {
        return doctorExperience;
    }

    public void setDoctorExperience(String doctorExperience) {
        this.doctorExperience = doctorExperience;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTimeSlot() {
        return appointmentTimeSlot;
    }

    public void setAppointmentTimeSlot(String appointmentTimeSlot) {
        this.appointmentTimeSlot = appointmentTimeSlot;
    }
}
