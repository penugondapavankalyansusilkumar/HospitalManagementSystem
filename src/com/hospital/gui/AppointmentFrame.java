package com.hospital.gui;

import com.hospital.dao.AppointmentDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AppointmentFrame extends JFrame {

    private JComboBox<String> patientBox;
    private JComboBox<String> doctorBox;

    private JSpinner dateSpinner;
    private JSpinner timeSpinner;

    private JTable appointmentTable;
    private DefaultTableModel tableModel;

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;


    public AppointmentFrame() {

        patientDAO = new PatientDAO();

        doctorDAO = new DoctorDAO();

        appointmentDAO = new AppointmentDAO();


        setTitle(
                "Hospital Management System - Appointments"
        );

        setSize(
                900,
                600
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );


        createGUI();

        loadPatients();

        loadDoctors();

        loadAppointments();


        setVisible(true);
    }


    private void createGUI() {

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                10
                        )
                );


        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Appointment Details"
                )
        );


        // Patient ComboBox
        patientBox =
                new JComboBox<>();


        // Doctor ComboBox
        doctorBox =
                new JComboBox<>();


        // Date Spinner
        dateSpinner =
                new JSpinner(
                        new SpinnerDateModel()
                );


        dateSpinner.setEditor(
                new JSpinner.DateEditor(
                        dateSpinner,
                        "yyyy-MM-dd"
                )
        );


        // Time Spinner
        timeSpinner =
                new JSpinner(
                        new SpinnerDateModel()
                );


        timeSpinner.setEditor(
                new JSpinner.DateEditor(
                        timeSpinner,
                        "HH:mm:ss"
                )
        );


        // Patient
        formPanel.add(
                new JLabel(
                        "Patient:"
                )
        );

        formPanel.add(
                patientBox
        );


        // Doctor
        formPanel.add(
                new JLabel(
                        "Doctor:"
                )
        );

        formPanel.add(
                doctorBox
        );


        // Date
        formPanel.add(
                new JLabel(
                        "Appointment Date:"
                )
        );

        formPanel.add(
                dateSpinner
        );


        // Time
        formPanel.add(
                new JLabel(
                        "Appointment Time:"
                )
        );

        formPanel.add(
                timeSpinner
        );


        // Buttons
        JPanel buttonPanel =
                new JPanel();


        JButton bookButton =
                new JButton(
                        "BOOK APPOINTMENT"
                );


        JButton cancelButton =
                new JButton(
                        "CANCEL APPOINTMENT"
                );


        JButton clearButton =
                new JButton(
                        "CLEAR"
                );


        buttonPanel.add(
                bookButton
        );

        buttonPanel.add(
                cancelButton
        );

        buttonPanel.add(
                clearButton
        );


        // Table
        tableModel =
                new DefaultTableModel(

                        new String[]{
                                "ID",
                                "Patient ID",
                                "Doctor ID",
                                "Date",
                                "Time",
                                "Status"
                        },

                        0
                );


        appointmentTable =
                new JTable(
                        tableModel
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );


        // Button Actions
        bookButton.addActionListener(
                e ->
                        bookAppointment()
        );


        cancelButton.addActionListener(
                e ->
                        cancelAppointment()
        );


        clearButton.addActionListener(
                e ->
                        clearFields()
        );


        // Top Panel
        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );


        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );


        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(
                topPanel,
                BorderLayout.NORTH
        );


        add(
                scrollPane,
                BorderLayout.CENTER
        );
    }


    // LOAD PATIENTS
    private void loadPatients() {

        patientBox.removeAllItems();


        List<Patient> patients =
                patientDAO
                        .getAllPatients();


        for (
                Patient patient :
                patients
        ) {

            patientBox.addItem(

                    patient.getPatientId()
                            + " - "
                            + patient.getName()
            );
        }
    }


    // LOAD DOCTORS
    private void loadDoctors() {

        doctorBox.removeAllItems();


        List<Doctor> doctors =
                doctorDAO
                        .getAllDoctors();


        for (
                Doctor doctor :
                doctors
        ) {

            doctorBox.addItem(

                    doctor.getDoctorId()
                            + " - "
                            + doctor.getName()
            );
        }
    }


    // LOAD APPOINTMENTS
    private void loadAppointments() {

        tableModel.setRowCount(
                0
        );


        List<Appointment> appointments =
                appointmentDAO
                        .getAllAppointments();


        for (
                Appointment appointment :
                appointments
        ) {

            tableModel.addRow(

                    new Object[]{

                            appointment
                                    .getAppointmentId(),

                            appointment
                                    .getPatientId(),

                            appointment
                                    .getDoctorId(),

                            appointment
                                    .getAppointmentDate(),

                            appointment
                                    .getAppointmentTime(),

                            appointment
                                    .getStatus()
                    }
            );
        }
    }


    // BOOK APPOINTMENT
    private void bookAppointment() {

        try {

            // Check Patient
            if (
                    patientBox
                            .getSelectedItem()
                            == null
            ) {

                JOptionPane.showMessageDialog(

                        this,

                        "Please add a patient first!"
                );

                return;
            }


            // Check Doctor
            if (
                    doctorBox
                            .getSelectedItem()
                            == null
            ) {

                JOptionPane.showMessageDialog(

                        this,

                        "Please add a doctor first!"
                );

                return;
            }


            // Get Patient ID
            String patientText =
                    patientBox
                            .getSelectedItem()
                            .toString();


            int patientId =
                    Integer.parseInt(

                            patientText
                                    .split(
                                            " - "
                                    )[0]
                    );


            // Get Doctor ID
            String doctorText =
                    doctorBox
                            .getSelectedItem()
                            .toString();


            int doctorId =
                    Integer.parseInt(

                            doctorText
                                    .split(
                                            " - "
                                    )[0]
                    );


            // Get Selected Date
            Date selectedDate =
                    (Date)
                            dateSpinner
                                    .getValue();


            LocalDate date =
                    selectedDate
                            .toInstant()
                            .atZone(
                                    ZoneId
                                            .systemDefault()
                            )
                            .toLocalDate();


            // Get Selected Time
            Date selectedTime =
                    (Date)
                            timeSpinner
                                    .getValue();


            LocalTime time =
                    selectedTime
                            .toInstant()
                            .atZone(
                                    ZoneId
                                            .systemDefault()
                            )
                            .toLocalTime()
                            .withNano(
                                    0
                            );


            // Create Appointment
            Appointment appointment =
                    new Appointment(

                            patientId,

                            doctorId,

                            date,

                            time,

                            "Booked"
                    );


            // Save Appointment
            boolean result =
                    appointmentDAO
                            .addAppointment(
                                    appointment
                            );


            if (result) {

                JOptionPane.showMessageDialog(

                        this,

                        "Appointment Booked Successfully!"
                );


                loadAppointments();


                clearFields();
            }

        } catch (
                Exception e
        ) {

            e.printStackTrace();


            JOptionPane.showMessageDialog(

                    this,

                    "Error while booking appointment!"
            );
        }
    }


    // CANCEL APPOINTMENT
    private void cancelAppointment() {

        int row =
                appointmentTable
                        .getSelectedRow();


        if (
                row
                        ==
                        -1
        ) {

            JOptionPane.showMessageDialog(

                    this,

                    "Select an appointment first!"
            );

            return;
        }


        int appointmentId =
                Integer.parseInt(

                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );


        boolean result =
                appointmentDAO
                        .cancelAppointment(

                                appointmentId
                        );


        if (result) {

            JOptionPane.showMessageDialog(

                    this,

                    "Appointment Cancelled!"
            );


            loadAppointments();
        }
    }


    // CLEAR FIELDS
    private void clearFields() {

        dateSpinner.setValue(
                new Date()
        );


        timeSpinner.setValue(
                new Date()
        );
    }


    // MAIN METHOD
    public static void main(

            String[] args

    ) {

        SwingUtilities.invokeLater(

                () ->
                        new AppointmentFrame()
        );
    }
}