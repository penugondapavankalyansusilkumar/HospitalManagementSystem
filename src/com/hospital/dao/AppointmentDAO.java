package com.hospital.dao;

import com.hospital.db.DBconnection;
import com.hospital.model.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // BOOK APPOINTMENT
    public boolean addAppointment(
            Appointment appointment
    ) {

        String sql =
                "INSERT INTO appointments " +
                        "(patient_id, doctor_id, appointment_date, " +
                        "appointment_time, status) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DBconnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    appointment.getPatientId()
            );

            statement.setInt(
                    2,
                    appointment.getDoctorId()
            );

            statement.setDate(
                    3,
                    Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            statement.setTime(
                    4,
                    Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );

            statement.setString(
                    5,
                    appointment.getStatus()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // GET ALL APPOINTMENTS
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments";

        try (
                Connection connection =
                        DBconnection.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {

                Appointment appointment =
                        new Appointment(

                                resultSet.getInt(
                                        "appointment_id"
                                ),

                                resultSet.getInt(
                                        "patient_id"
                                ),

                                resultSet.getInt(
                                        "doctor_id"
                                ),

                                resultSet.getDate(
                                        "appointment_date"
                                ).toLocalDate(),

                                resultSet.getTime(
                                        "appointment_time"
                                ).toLocalTime(),

                                resultSet.getString(
                                        "status"
                                )
                        );

                appointments.add(
                        appointment
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return appointments;
    }


    // CANCEL APPOINTMENT
    public boolean cancelAppointment(
            int appointmentId
    ) {

        String sql =
                "UPDATE appointments " +
                        "SET status = 'Cancelled' " +
                        "WHERE appointment_id = ?";

        try (
                Connection connection =
                        DBconnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    appointmentId
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}
