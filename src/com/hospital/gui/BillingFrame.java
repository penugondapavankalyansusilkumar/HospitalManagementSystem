package com.hospital.gui;

import com.hospital.dao.BillingDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.model.Bill;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class BillingFrame extends JFrame {

    private JComboBox<String> patientBox;
    private JComboBox<String> doctorBox;

    private JTextField consultationField;
    private JTextField medicineField;
    private JTextField roomField;
    private JTextField otherField;

    private JComboBox<String> paymentStatusBox;

    private JTable billTable;
    private DefaultTableModel tableModel;

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private BillingDAO billingDAO;


    public BillingFrame() {

        patientDAO =
                new PatientDAO();

        doctorDAO =
                new DoctorDAO();

        billingDAO =
                new BillingDAO();


        setTitle(
                "Hospital Management System - Billing"
        );

        setSize(
                1100,
                650
        );

        setLocationRelativeTo(
                null
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );


        createGUI();

        loadPatients();

        loadDoctors();

        loadBills();


        setVisible(
                true
        );
    }


    private void createGUI() {

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                7,
                                2,
                                10,
                                10
                        )
                );


        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Billing Details"
                )
        );


        patientBox =
                new JComboBox<>();

        doctorBox =
                new JComboBox<>();


        consultationField =
                new JTextField();

        medicineField =
                new JTextField();

        roomField =
                new JTextField();

        otherField =
                new JTextField();


        paymentStatusBox =
                new JComboBox<>(
                        new String[]{
                                "Pending",
                                "Paid"
                        }
                );


        formPanel.add(
                new JLabel(
                        "Patient:"
                )
        );

        formPanel.add(
                patientBox
        );


        formPanel.add(
                new JLabel(
                        "Doctor:"
                )
        );

        formPanel.add(
                doctorBox
        );


        formPanel.add(
                new JLabel(
                        "Consultation Fee:"
                )
        );

        formPanel.add(
                consultationField
        );


        formPanel.add(
                new JLabel(
                        "Medicine Charges:"
                )
        );

        formPanel.add(
                medicineField
        );


        formPanel.add(
                new JLabel(
                        "Room Charges:"
                )
        );

        formPanel.add(
                roomField
        );


        formPanel.add(
                new JLabel(
                        "Other Charges:"
                )
        );

        formPanel.add(
                otherField
        );


        formPanel.add(
                new JLabel(
                        "Payment Status:"
                )
        );

        formPanel.add(
                paymentStatusBox
        );


        JPanel buttonPanel =
                new JPanel();


        JButton generateButton =
                new JButton(
                        "GENERATE BILL"
                );

        JButton paidButton =
                new JButton(
                        "MARK AS PAID"
                );

        JButton clearButton =
                new JButton(
                        "CLEAR"
                );


        buttonPanel.add(
                generateButton
        );

        buttonPanel.add(
                paidButton
        );

        buttonPanel.add(
                clearButton
        );


        tableModel =
                new DefaultTableModel(

                        new String[]{
                                "Bill ID",
                                "Patient ID",
                                "Doctor ID",
                                "Consultation",
                                "Medicine",
                                "Room",
                                "Other",
                                "Total",
                                "Date",
                                "Status"
                        },

                        0
                );


        billTable =
                new JTable(
                        tableModel
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        billTable
                );


        generateButton.addActionListener(
                e ->
                        generateBill()
        );


        paidButton.addActionListener(
                e ->
                        markAsPaid()
        );


        clearButton.addActionListener(
                e ->
                        clearFields()
        );


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


    private void loadBills() {

        tableModel.setRowCount(
                0
        );


        List<Bill> bills =
                billingDAO
                        .getAllBills();


        for (
                Bill bill :
                bills
        ) {

            tableModel.addRow(

                    new Object[]{

                            bill.getBillId(),

                            bill.getPatientId(),

                            bill.getDoctorId(),

                            bill.getConsultationFee(),

                            bill.getMedicineCharges(),

                            bill.getRoomCharges(),

                            bill.getOtherCharges(),

                            bill.getTotalAmount(),

                            bill.getBillDate(),

                            bill.getPaymentStatus()
                    }
            );
        }
    }


    private void generateBill() {

        try {

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


            int patientId =
                    Integer.parseInt(

                            patientBox
                                    .getSelectedItem()
                                    .toString()
                                    .split(
                                            " - "
                                    )[0]
                    );


            int doctorId =
                    Integer.parseInt(

                            doctorBox
                                    .getSelectedItem()
                                    .toString()
                                    .split(
                                            " - "
                                    )[0]
                    );


            double consultation =
                    Double.parseDouble(
                            consultationField
                                    .getText()
                    );


            double medicine =
                    Double.parseDouble(
                            medicineField
                                    .getText()
                    );


            double room =
                    Double.parseDouble(
                            roomField
                                    .getText()
                    );


            double other =
                    Double.parseDouble(
                            otherField
                                    .getText()
                    );


            double total =
                    consultation
                            + medicine
                            + room
                            + other;


            String status =
                    paymentStatusBox
                            .getSelectedItem()
                            .toString();


            Bill bill =
                    new Bill(

                            patientId,

                            doctorId,

                            consultation,

                            medicine,

                            room,

                            other,

                            total,

                            LocalDate.now(),

                            status
                    );


            boolean result =
                    billingDAO
                            .addBill(
                                    bill
                            );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,

                        "Bill Generated Successfully!\n"
                                +
                                "Total Amount: ₹"
                                +
                                total
                );


                loadBills();

                clearFields();
            }

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Enter valid numbers for all charges!"
            );
        }
    }


    private void markAsPaid() {

        int row =
                billTable
                        .getSelectedRow();


        if (
                row
                        ==
                        -1
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Select a bill first!"
            );

            return;
        }


        int billId =
                Integer.parseInt(

                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );


        boolean result =
                billingDAO
                        .markAsPaid(
                                billId
                        );


        if (result) {

            JOptionPane.showMessageDialog(
                    this,

                    "Payment Status Updated!"
            );

            loadBills();
        }
    }


    private void clearFields() {

        consultationField.setText(
                ""
        );

        medicineField.setText(
                ""
        );

        roomField.setText(
                ""
        );

        otherField.setText(
                ""
        );

        paymentStatusBox.setSelectedIndex(
                0
        );
    }


    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () ->
                        new BillingFrame()
        );
    }
}
