/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package online.reservation.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Ananda
 */
public class Reservation extends JFrame implements ActionListener{
    private JTextField nameField;
    private JTextField pnrField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JTextField classTypeField;
    private JTextField dateField;
    private JTextField fromField;
    private JTextField toField;
    private JButton insertButton;
    private JButton cancelBookingButton;

    public Reservation() {
        setTitle("Reservation Form");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel pnrLabel = new JLabel("PNR Number:");
        JLabel trainNumberLabel = new JLabel("Train Number:");
        JLabel trainNameLabel = new JLabel("Train Name:");
        JLabel classTypeLabel = new JLabel("Class Type:");
        JLabel dateLabel = new JLabel("Date of Journey:");
        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        nameField = new JTextField();
        pnrField = new JTextField();
        trainNumberField = new JTextField();
        trainNameField = new JTextField();
        trainNameField.setEditable(false);
        classTypeField = new JTextField();
        dateField = new JTextField();
        fromField = new JTextField();
        toField = new JTextField();
        insertButton = new JButton("Insert");
        cancelBookingButton = new JButton ("Cancel Booking");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(pnrLabel);
        panel.add(pnrField);
        panel.add(trainNumberLabel);
        panel.add(trainNumberField);
        panel.add(trainNameLabel);
        panel.add(trainNameField);
        panel.add(classTypeLabel);
        panel.add(classTypeField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(fromLabel);
        panel.add(fromField);
        panel.add(toLabel);
        panel.add(toField);
        panel.add(cancelBookingButton);
        panel.add(insertButton);

        trainNumberField.addActionListener(this);
        insertButton.addActionListener(this);
        cancelBookingButton.addActionListener(this);

        add(panel);
        setVisible(true);     
 }
 
    public static void main (String args[]){
        new Reservation();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == trainNumberField) {
            String trainNumber = trainNumberField.getText();
            String trainName = getTrainName(trainNumber);
            trainNameField.setText(trainName);
        } else if (e.getSource() == insertButton) {
            String name = nameField.getText();
            String pnrNumber = pnrField.getText();
            String trainNumber = trainNumberField.getText();
            String trainName = trainNameField.getText();
            String classType = classTypeField.getText();
            String date = dateField.getText();
            String from = fromField.getText();
            String to = toField.getText();

            if (insertReservation(name, pnrNumber, trainNumber, trainName, classType, date, from, to)) {
                JOptionPane.showMessageDialog(this, "Reservation successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Reservation failed. Please try again.");
            }
        }
        else if (e.getSource() == cancelBookingButton) {
            cancellationform obj = new cancellationform();
            obj.setVisible(true);
            this.setVisible(false);
        }
    }
    
private String getTrainName(String trainNumber) {
        String jdbcURL = "jdbc:mysql://localhost:3306/reservation";
        String dbUsername = "root";
        String dbPassword = "";
        String trainName = "";
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword)) {
            String query = "SELECT train_name FROM trains WHERE train_number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, trainNumber);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                trainName = resultSet.getString("train_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return trainName;
    }

private boolean insertReservation(String name, String pnrNumber, String trainNumber, String trainName, String classType, String date, String from, String to) {
        String jdbcURL = "jdbc:mysql://localhost:3306/reservation";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword)) {
            String query = "INSERT INTO reservations (name, pnr_number, train_number, train_name, class_type, date, from_place, to_place) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, pnrNumber);
            statement.setString(3, trainNumber);
            statement.setString(4, trainName);
            statement.setString(5, classType);
            statement.setString(6, date);
            statement.setString(7, from);
            statement.setString(8, to);
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

