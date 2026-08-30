package com.hospital.gui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Hospital Management System - Dashboard");

        setSize(700, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );


        // Main panel
        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );


        // Header
        JLabel titleLabel =
                new JLabel(
                        "HOSPITAL MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // Button panel
        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );


        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        50,
                        80,
                        50,
                        80
                )
        );


        // Buttons
        JButton patientButton =
                new JButton(
                        "PATIENT MANAGEMENT"
                );


        JButton doctorButton =
                new JButton(
                        "DOCTOR MANAGEMENT"
                );


        JButton appointmentButton =
                new JButton(
                        "APPOINTMENT MANAGEMENT"
                );


        JButton billingButton =
                new JButton(
                        "BILLING MANAGEMENT"
                );


        // Add buttons
        buttonPanel.add(
                patientButton
        );

        buttonPanel.add(
                doctorButton
        );

        buttonPanel.add(
                appointmentButton
        );

        buttonPanel.add(
                billingButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );


        // Button actions
        patientButton.addActionListener(
                e ->
                        new PatientFrame()
        );


        doctorButton.addActionListener(
                e ->
                        new DoctorFrame()
        );


        appointmentButton.addActionListener(
                e ->
                        new AppointmentFrame()
        );


        billingButton.addActionListener(
                e ->
                        new BillingFrame()
        );


        add(
                mainPanel
        );


        setVisible(
                true
        );
    }


    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () ->
                        new Dashboard()
        );
    }
}
