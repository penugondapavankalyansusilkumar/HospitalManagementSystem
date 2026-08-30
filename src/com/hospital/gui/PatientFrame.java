package com.hospital.gui;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientFrame extends JFrame {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField addressField;

    private JComboBox<String> genderBox;
    private JComboBox<String> bloodGroupBox;

    private JTable patientTable;
    private DefaultTableModel tableModel;

    private PatientDAO patientDAO;


    public PatientFrame() {

        patientDAO =
                new PatientDAO();

        setTitle(
                "Hospital Management System - Patients"
        );

        setSize(900, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createGUI();

        loadPatients();

        setVisible(true);
    }


    private void createGUI() {

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                10,
                                10
                        )
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Patient Details"
                )
        );


        nameField =
                new JTextField();

        ageField =
                new JTextField();

        phoneField =
                new JTextField();

        addressField =
                new JTextField();


        genderBox =
                new JComboBox<>(
                        new String[]{
                                "Male",
                                "Female",
                                "Other"
                        }
                );


        bloodGroupBox =
                new JComboBox<>(
                        new String[]{
                                "A+",
                                "A-",
                                "B+",
                                "B-",
                                "AB+",
                                "AB-",
                                "O+",
                                "O-"
                        }
                );


        formPanel.add(
                new JLabel("Name:")
        );

        formPanel.add(
                nameField
        );


        formPanel.add(
                new JLabel("Age:")
        );

        formPanel.add(
                ageField
        );


        formPanel.add(
                new JLabel("Gender:")
        );

        formPanel.add(
                genderBox
        );


        formPanel.add(
                new JLabel("Phone:")
        );

        formPanel.add(
                phoneField
        );


        formPanel.add(
                new JLabel("Address:")
        );

        formPanel.add(
                addressField
        );


        formPanel.add(
                new JLabel("Blood Group:")
        );

        formPanel.add(
                bloodGroupBox
        );


        JPanel buttonPanel =
                new JPanel();


        JButton addButton =
                new JButton("ADD");

        JButton updateButton =
                new JButton("UPDATE");

        JButton deleteButton =
                new JButton("DELETE");

        JButton clearButton =
                new JButton("CLEAR");


        buttonPanel.add(
                addButton
        );

        buttonPanel.add(
                updateButton
        );

        buttonPanel.add(
                deleteButton
        );

        buttonPanel.add(
                clearButton
        );


        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Name",
                                "Age",
                                "Gender",
                                "Phone",
                                "Address",
                                "Blood Group"
                        },
                        0
                );


        patientTable =
                new JTable(
                        tableModel
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        patientTable
                );


        addButton.addActionListener(
                e -> addPatient()
        );

        updateButton.addActionListener(
                e -> updatePatient()
        );

        deleteButton.addActionListener(
                e -> deletePatient()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );


        patientTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> fillFields()
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


    private void addPatient() {

        try {

            String name =
                    nameField.getText();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter patient name"
                );

                return;
            }


            int age =
                    Integer.parseInt(
                            ageField.getText()
                    );


            String gender =
                    genderBox
                            .getSelectedItem()
                            .toString();


            String phone =
                    phoneField.getText();


            String address =
                    addressField.getText();


            String bloodGroup =
                    bloodGroupBox
                            .getSelectedItem()
                            .toString();


            Patient patient =
                    new Patient(
                            name,
                            age,
                            gender,
                            phone,
                            address,
                            bloodGroup
                    );


            boolean result =
                    patientDAO.addPatient(
                            patient
                    );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient Added Successfully!"
                );

                loadPatients();

                clearFields();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number!"
            );
        }
    }


    private void loadPatients() {

        tableModel.setRowCount(0);


        List<Patient> patients =
                patientDAO.getAllPatients();


        for (Patient patient :
                patients) {

            tableModel.addRow(
                    new Object[]{

                            patient.getPatientId(),

                            patient.getName(),

                            patient.getAge(),

                            patient.getGender(),

                            patient.getPhone(),

                            patient.getAddress(),

                            patient.getBloodGroup()
                    }
            );
        }
    }


    private void fillFields() {

        int row =
                patientTable
                        .getSelectedRow();


        if (row >= 0) {

            nameField.setText(
                    tableModel
                            .getValueAt(
                                    row,
                                    1
                            )
                            .toString()
            );


            ageField.setText(
                    tableModel
                            .getValueAt(
                                    row,
                                    2
                            )
                            .toString()
            );


            genderBox.setSelectedItem(
                    tableModel
                            .getValueAt(
                                    row,
                                    3
                            )
                            .toString()
            );


            phoneField.setText(
                    tableModel
                            .getValueAt(
                                    row,
                                    4
                            )
                            .toString()
            );


            addressField.setText(
                    tableModel
                            .getValueAt(
                                    row,
                                    5
                            )
                            .toString()
            );


            bloodGroupBox.setSelectedItem(
                    tableModel
                            .getValueAt(
                                    row,
                                    6
                            )
                            .toString()
            );
        }
    }


    private void updatePatient() {

        int row =
                patientTable
                        .getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a patient first!"
            );

            return;
        }


        try {

            int id =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            row,
                                            0
                                    )
                                    .toString()
                    );


            Patient patient =
                    new Patient(

                            id,

                            nameField
                                    .getText(),

                            Integer.parseInt(
                                    ageField
                                            .getText()
                            ),

                            genderBox
                                    .getSelectedItem()
                                    .toString(),

                            phoneField
                                    .getText(),

                            addressField
                                    .getText(),

                            bloodGroupBox
                                    .getSelectedItem()
                                    .toString()
                    );


            boolean result =
                    patientDAO
                            .updatePatient(
                                    patient
                            );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient Updated Successfully!"
                );

                loadPatients();

                clearFields();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number!"
            );
        }
    }


    private void deletePatient() {

        int row =
                patientTable
                        .getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a patient first!"
            );

            return;
        }


        int id =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );


        int confirm =
                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to delete?",

                        "Confirm Delete",

                        JOptionPane.YES_NO_OPTION
                );


        if (confirm ==
                JOptionPane.YES_OPTION) {


            boolean result =
                    patientDAO
                            .deletePatient(
                                    id
                            );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient Deleted Successfully!"
                );

                loadPatients();

                clearFields();
            }
        }
    }


    private void clearFields() {

        nameField.setText("");

        ageField.setText("");

        phoneField.setText("");

        addressField.setText("");

        genderBox.setSelectedIndex(0);

        bloodGroupBox.setSelectedIndex(0);

        patientTable.clearSelection();
    }


    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> new PatientFrame()
        );
    }
}