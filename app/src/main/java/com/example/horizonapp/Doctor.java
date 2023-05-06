package com.example.horizonapp;

public class Doctor {
    private String name;
    private String gender;
    private int experience;

    public Doctor() {}

    public Doctor(String name, String gender, int experience) {
        this.name = name;
        this.gender = gender;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getExperience() {
        return experience;
    }
}
