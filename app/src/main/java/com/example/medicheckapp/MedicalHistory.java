package com.example.medicheckapp;

public class MedicalHistory {

    public String gender, weight, height, diabetic, asthmatic;

    public MedicalHistory() {

    }

    public MedicalHistory(String gender, String weight, String height, String diabetic, String asthmatic) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.diabetic = diabetic;
        this.asthmatic = asthmatic;

    }
}
