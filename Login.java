/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package online.reservation.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Ananda
 */
public class Login extends JFrame implements ActionListener{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(this);

        add(panel);
        setVisible(true);
    }
    public static void main (String args[]){
        new Login();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                Reservation obj = new Reservation();
                obj.setVisible(true);
                this.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        }
    }
    
    private boolean authenticate(String username,String password){
        String jdbcurl = "jdbc:mysql://localhost:3306/reservation";
        String dbusername = "root";
        String dbpassword = "";
        
        try (Connection conn = DriverManager.getConnection(jdbcurl, dbusername, dbpassword)){
            String query="select * from users where username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs =statement.executeQuery();
            return rs.next();
            
            
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
                
       return false; 
    }
    
}

