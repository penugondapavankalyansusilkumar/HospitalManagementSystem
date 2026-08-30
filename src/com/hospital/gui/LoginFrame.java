package com.hospital.gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Hospital Management System - Login");

        setSize(450, 300);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // Main Panel
        JPanel panel =
                new JPanel();

        panel.setLayout(null);


        // Title
        JLabel titleLabel =
                new JLabel(
                        "HOSPITAL MANAGEMENT SYSTEM"
                );

        titleLabel.setBounds(
                80,
                20,
                300,
                30
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        panel.add(
                titleLabel
        );


        // Username Label
        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        usernameLabel.setBounds(
                70,
                80,
                100,
                25
        );

        panel.add(
                usernameLabel
        );


        // Username Field
        usernameField =
                new JTextField();

        usernameField.setBounds(
                180,
                80,
                180,
                25
        );

        panel.add(
                usernameField
        );


        // Password Label
        JLabel passwordLabel =
                new JLabel(
                        "Password:"
                );

        passwordLabel.setBounds(
                70,
                120,
                100,
                25
        );

        panel.add(
                passwordLabel
        );


        // Password Field
        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                180,
                120,
                180,
                25
        );

        panel.add(
                passwordField
        );


        // Login Button
        JButton loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setBounds(
                160,
                180,
                120,
                35
        );

        panel.add(
                loginButton
        );


        // Login Button Action
        loginButton.addActionListener(
                e ->
                        login()
        );


        add(
                panel
        );


        setVisible(
                true
        );
    }


    private void login() {

        String username =
                usernameField
                        .getText();


        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        // Username and Password
        if (
                username.equals(
                        "penugondahospital"
                )

                        &&

                        password.equals(
                                "hospital@123"
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Login Successful!"
            );


            // Open Dashboard
            new Dashboard();


            // Close Login Window
            dispose();

        } else {

            JOptionPane.showMessageDialog(

                    this,

                    "Invalid Username or Password",

                    "Login Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(

                () ->
                        new LoginFrame()
        );
    }
}