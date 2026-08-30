package com.hospital.dao;

import com.hospital.db.DBconnection;
import com.hospital.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // ADD PATIENT
    public boolean addPatient(Patient patient) {

        String sql = "INSERT INTO patients " +
                "(name, age, gender, phone, address, blood_group) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBconnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getAddress());
            statement.setString(6, patient.getBloodGroup());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // GET ALL PATIENTS
    public List<Patient> getAllPatients() {

        List<Patient> patients =
                new ArrayList<>();

        String sql =
                "SELECT * FROM patients";

        try (Connection connection =
                     DBconnection.getConnection();

             Statement statement =
                     connection.createStatement();

             ResultSet resultSet =
                     statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Patient patient =
                        new Patient(

                                resultSet.getInt(
                                        "patient_id"
                                ),

                                resultSet.getString(
                                        "name"
                                ),

                                resultSet.getInt(
                                        "age"
                                ),

                                resultSet.getString(
                                        "gender"
                                ),

                                resultSet.getString(
                                        "phone"
                                ),

                                resultSet.getString(
                                        "address"
                                ),

                                resultSet.getString(
                                        "blood_group"
                                )
                        );

                patients.add(patient);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return patients;
    }


    // UPDATE PATIENT
    public boolean updatePatient(Patient patient) {

        String sql =
                "UPDATE patients SET " +
                        "name = ?, " +
                        "age = ?, " +
                        "gender = ?, " +
                        "phone = ?, " +
                        "address = ?, " +
                        "blood_group = ? " +
                        "WHERE patient_id = ?";

        try (Connection connection =
                     DBconnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    patient.getName()
            );

            statement.setInt(
                    2,
                    patient.getAge()
            );

            statement.setString(
                    3,
                    patient.getGender()
            );

            statement.setString(
                    4,
                    patient.getPhone()
            );

            statement.setString(
                    5,
                    patient.getAddress()
            );

            statement.setString(
                    6,
                    patient.getBloodGroup()
            );

            statement.setInt(
                    7,
                    patient.getPatientId()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // DELETE PATIENT
    public boolean deletePatient(int patientId) {

        String sql =
                "DELETE FROM patients " +
                        "WHERE patient_id = ?";

        try (Connection connection =
                     DBconnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
