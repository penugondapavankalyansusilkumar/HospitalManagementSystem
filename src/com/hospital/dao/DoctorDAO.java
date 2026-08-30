package com.hospital.dao;

import com.hospital.db.DBconnection;
import com.hospital.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // ADD DOCTOR
    public boolean addDoctor(Doctor doctor) {

        String sql =
                "INSERT INTO doctors " +
                        "(name, specialization, phone, experience) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DBconnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    doctor.getName()
            );

            statement.setString(
                    2,
                    doctor.getSpecialization()
            );

            statement.setString(
                    3,
                    doctor.getPhone()
            );

            statement.setInt(
                    4,
                    doctor.getExperience()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // GET ALL DOCTORS
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors =
                new ArrayList<>();

        String sql =
                "SELECT * FROM doctors";

        try (Connection connection =
                     DBconnection.getConnection();

             Statement statement =
                     connection.createStatement();

             ResultSet resultSet =
                     statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Doctor doctor =
                        new Doctor(

                                resultSet.getInt(
                                        "doctor_id"
                                ),

                                resultSet.getString(
                                        "name"
                                ),

                                resultSet.getString(
                                        "specialization"
                                ),

                                resultSet.getString(
                                        "phone"
                                ),

                                resultSet.getInt(
                                        "experience"
                                )
                        );

                doctors.add(doctor);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return doctors;
    }


    // UPDATE DOCTOR
    public boolean updateDoctor(Doctor doctor) {

        String sql =
                "UPDATE doctors SET " +
                        "name = ?, " +
                        "specialization = ?, " +
                        "phone = ?, " +
                        "experience = ? " +
                        "WHERE doctor_id = ?";

        try (Connection connection =
                     DBconnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    doctor.getName()
            );

            statement.setString(
                    2,
                    doctor.getSpecialization()
            );

            statement.setString(
                    3,
                    doctor.getPhone()
            );

            statement.setInt(
                    4,
                    doctor.getExperience()
            );

            statement.setInt(
                    5,
                    doctor.getDoctorId()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // DELETE DOCTOR
    public boolean deleteDoctor(int doctorId) {

        String sql =
                "DELETE FROM doctors " +
                        "WHERE doctor_id = ?";

        try (Connection connection =
                     DBconnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    doctorId
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}
