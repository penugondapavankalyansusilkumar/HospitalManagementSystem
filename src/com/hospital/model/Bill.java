package com.hospital.model;

import java.time.LocalDate;

public class Bill {

    private int billId;
    private int patientId;
    private int doctorId;

    private double consultationFee;
    private double medicineCharges;
    private double roomCharges;
    private double otherCharges;
    private double totalAmount;

    private LocalDate billDate;
    private String paymentStatus;


    // Empty Constructor
    public Bill() {
    }


    // Constructor for ADD
    public Bill(
            int patientId,
            int doctorId,
            double consultationFee,
            double medicineCharges,
            double roomCharges,
            double otherCharges,
            double totalAmount,
            LocalDate billDate,
            String paymentStatus
    ) {

        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationFee = consultationFee;
        this.medicineCharges = medicineCharges;
        this.roomCharges = roomCharges;
        this.otherCharges = otherCharges;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
        this.paymentStatus = paymentStatus;
    }


    // Constructor for displaying
    public Bill(
            int billId,
            int patientId,
            int doctorId,
            double consultationFee,
            double medicineCharges,
            double roomCharges,
            double otherCharges,
            double totalAmount,
            LocalDate billDate,
            String paymentStatus
    ) {

        this.billId = billId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationFee = consultationFee;
        this.medicineCharges = medicineCharges;
        this.roomCharges = roomCharges;
        this.otherCharges = otherCharges;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
        this.paymentStatus = paymentStatus;
    }


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }


    public double getMedicineCharges() {
        return medicineCharges;
    }

    public void setMedicineCharges(double medicineCharges) {
        this.medicineCharges = medicineCharges;
    }


    public double getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }


    public double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }


    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
