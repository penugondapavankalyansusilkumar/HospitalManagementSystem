package com.hospital.gui;

import com.hospital.dao.DoctorDAO;
import com.hospital.model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorFrame extends JFrame {

    private JTextField nameField;
    private JTextField specializationField;
    private JTextField phoneField;
    private JTextField experienceField;

    private JTable doctorTable;
    private DefaultTableModel tableModel;

    private DoctorDAO doctorDAO;


    public DoctorFrame() {

        doctorDAO =
                new DoctorDAO();

        setTitle(
                "Hospital Management System - Doctors"
        );

        setSize(
                800,
                550
        );

        setLocationRelativeTo(
                null
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createGUI();

        loadDoctors();

        setVisible(
                true
        );
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
                        "Doctor Details"
                )
        );


        nameField =
                new JTextField();

        specializationField =
                new JTextField();

        phoneField =
                new JTextField();

        experienceField =
                new JTextField();


        formPanel.add(
                new JLabel(
                        "Name:"
                )
        );

        formPanel.add(
                nameField
        );


        formPanel.add(
                new JLabel(
                        "Specialization:"
                )
        );

        formPanel.add(
                specializationField
        );


        formPanel.add(
                new JLabel(
                        "Phone:"
                )
        );

        formPanel.add(
                phoneField
        );


        formPanel.add(
                new JLabel(
                        "Experience:"
                )
        );

        formPanel.add(
                experienceField
        );


        JPanel buttonPanel =
                new JPanel();


        JButton addButton =
                new JButton(
                        "ADD"
                );

        JButton updateButton =
                new JButton(
                        "UPDATE"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE"
                );

        JButton clearButton =
                new JButton(
                        "CLEAR"
                );


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
                                "Specialization",
                                "Phone",
                                "Experience"
                        },

                        0
                );


        doctorTable =
                new JTable(
                        tableModel
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        doctorTable
                );


        addButton.addActionListener(
                e ->
                        addDoctor()
        );


        updateButton.addActionListener(
                e ->
                        updateDoctor()
        );


        deleteButton.addActionListener(
                e ->
                        deleteDoctor()
        );


        clearButton.addActionListener(
                e ->
                        clearFields()
        );


        doctorTable
                .getSelectionModel()
                .addListSelectionListener(
                        e ->
                                fillFields()
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


    private void addDoctor() {

        try {

            String name =
                    nameField
                            .getText();


            String specialization =
                    specializationField
                            .getText();


            String phone =
                    phoneField
                            .getText();


            int experience =
                    Integer.parseInt(
                            experienceField
                                    .getText()
                    );


            if (name.isEmpty()
                    ||
                    specialization.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Name and specialization are required!"
                );

                return;
            }


            Doctor doctor =
                    new Doctor(

                            name,

                            specialization,

                            phone,

                            experience
                    );


            boolean result =
                    doctorDAO
                            .addDoctor(
                                    doctor
                            );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor Added Successfully!"
                );

                loadDoctors();

                clearFields();
            }

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Experience must be a number!"
            );
        }
    }


    private void loadDoctors() {

        tableModel
                .setRowCount(
                        0
                );


        List<Doctor> doctors =
                doctorDAO
                        .getAllDoctors();


        for (
                Doctor doctor :
                doctors
        ) {

            tableModel.addRow(

                    new Object[]{

                            doctor
                                    .getDoctorId(),

                            doctor
                                    .getName(),

                            doctor
                                    .getSpecialization(),

                            doctor
                                    .getPhone(),

                            doctor
                                    .getExperience()
                    }
            );
        }
    }


    private void fillFields() {

        int row =
                doctorTable
                        .getSelectedRow();


        if (row >= 0) {

            nameField
                    .setText(
                            tableModel
                                    .getValueAt(
                                            row,
                                            1
                                    )
                                    .toString()
                    );


            specializationField
                    .setText(
                            tableModel
                                    .getValueAt(
                                            row,
                                            2
                                    )
                                    .toString()
                    );


            phoneField
                    .setText(
                            tableModel
                                    .getValueAt(
                                            row,
                                            3
                                    )
                                    .toString()
                    );


            experienceField
                    .setText(
                            tableModel
                                    .getValueAt(
                                            row,
                                            4
                                    )
                                    .toString()
                    );
        }
    }


    private void updateDoctor() {

        int row =
                doctorTable
                        .getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a doctor first!"
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


            Doctor doctor =
                    new Doctor(

                            id,

                            nameField
                                    .getText(),

                            specializationField
                                    .getText(),

                            phoneField
                                    .getText(),

                            Integer.parseInt(
                                    experienceField
                                            .getText()
                            )
                    );


            boolean result =
                    doctorDAO
                            .updateDoctor(
                                    doctor
                            );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor Updated Successfully!"
                );

                loadDoctors();

                clearFields();
            }

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Experience must be a number!"
            );
        }
    }


    private void deleteDoctor() {

        int row =
                doctorTable
                        .getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a doctor first!"
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


        if (
                confirm
                        ==
                        JOptionPane.YES_OPTION
        ) {

            boolean result =
                    doctorDAO
                            .deleteDoctor(
                                    id
                            );


            if (result) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor Deleted Successfully!"
                );

                loadDoctors();

                clearFields();
            }
        }
    }


    private void clearFields() {

        nameField
                .setText(
                        ""
                );

        specializationField
                .setText(
                        ""
                );

        phoneField
                .setText(
                        ""
                );

        experienceField
                .setText(
                        ""
                );

        doctorTable
                .clearSelection();
    }


    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () ->
                        new DoctorFrame()
        );
    }
}