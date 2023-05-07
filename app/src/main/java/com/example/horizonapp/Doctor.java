package com.example.horizonapp;

public class Doctor {
    private String name;
    private String gender;
    private String experience;

    public Doctor() {}

    public Doctor(String name, String gender, String experience) {
        this.experience = experience;
        this.gender = gender;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getExperience() {
        return experience;
    }
}
