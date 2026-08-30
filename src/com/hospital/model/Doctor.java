package com.hospital.model;

public class Doctor {

    private int doctorId;
    private String name;
    private String specialization;
    private String phone;
    private int experience;

    // Empty constructor
    public Doctor() {
    }

    // Constructor for ADD
    public Doctor(String name,
                  String specialization,
                  String phone,
                  int experience) {

        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.experience = experience;
    }

    // Constructor for UPDATE
    public Doctor(int doctorId,
                  String name,
                  String specialization,
                  String phone,
                  int experience) {

        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.experience = experience;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
