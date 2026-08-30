package com.hospital.dao;

import com.hospital.db.DBconnection;
import com.hospital.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {


    // ADD BILL
    public boolean addBill(Bill bill) {

        String sql =
                "INSERT INTO bills " +
                        "(patient_id, doctor_id, consultation_fee, " +
                        "medicine_charges, room_charges, other_charges, " +
                        "total_amount, bill_date, payment_status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (
                Connection connection =
                        DBconnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    bill.getPatientId()
            );

            statement.setInt(
                    2,
                    bill.getDoctorId()
            );

            statement.setDouble(
                    3,
                    bill.getConsultationFee()
            );

            statement.setDouble(
                    4,
                    bill.getMedicineCharges()
            );

            statement.setDouble(
                    5,
                    bill.getRoomCharges()
            );

            statement.setDouble(
                    6,
                    bill.getOtherCharges()
            );

            statement.setDouble(
                    7,
                    bill.getTotalAmount()
            );

            statement.setDate(
                    8,
                    Date.valueOf(
                            bill.getBillDate()
                    )
            );

            statement.setString(
                    9,
                    bill.getPaymentStatus()
            );


            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // GET ALL BILLS
    public List<Bill> getAllBills() {

        List<Bill> bills =
                new ArrayList<>();


        String sql =
                "SELECT * FROM bills";


        try (
                Connection connection =
                        DBconnection.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(sql)
        ) {


            while (
                    resultSet.next()
            ) {

                Bill bill =
                        new Bill(

                                resultSet.getInt(
                                        "bill_id"
                                ),

                                resultSet.getInt(
                                        "patient_id"
                                ),

                                resultSet.getInt(
                                        "doctor_id"
                                ),

                                resultSet.getDouble(
                                        "consultation_fee"
                                ),

                                resultSet.getDouble(
                                        "medicine_charges"
                                ),

                                resultSet.getDouble(
                                        "room_charges"
                                ),

                                resultSet.getDouble(
                                        "other_charges"
                                ),

                                resultSet.getDouble(
                                        "total_amount"
                                ),

                                resultSet.getDate(
                                        "bill_date"
                                ).toLocalDate(),

                                resultSet.getString(
                                        "payment_status"
                                )
                        );


                bills.add(
                        bill
                );
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return bills;
    }


    // MARK BILL AS PAID
    public boolean markAsPaid(
            int billId
    ) {

        String sql =
                "UPDATE bills " +
                        "SET payment_status = 'Paid' " +
                        "WHERE bill_id = ?";


        try (
                Connection connection =
                        DBconnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    billId
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}
