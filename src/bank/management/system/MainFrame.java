package bank.management.system;

import javax.swing.*;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.sql.*;

public class MainFrame extends JFrame{
	
	private int userId;
	private JLabel nameLabel; 
    
    public MainFrame(int userId, String name) {
        this.userId = userId;
        String nameValue = name;
        initComponents(name);
        //System.out.println(userId);
        fetchDatabase(userId);
    }
    
    MainFrame(){
    	this.setVisible(false);
    }

    public static void main(String[] args){
        new MainFrame();
    }

    public static void fetchDatabase(int userId){
    	String name = "";
    	try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "nchhillar");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `users` WHERE `id` = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean isAuthenticated = resultSet.next();
            int id  = resultSet.getInt("id");
            name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String account = resultSet.getString("account");
            String balance = resultSet.getString("balance");
            int pin = resultSet.getInt("pin");
            
            //System.out.println("ID: " + id + ", Name: " + name + ", Account Number: " + account + ", Balance: " + balance + ", Email: " + email + ", PIN: " + pin);

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Unable to fetch Database.");
        }
    }
    
    private void initComponents(String name) {
    	
    	try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "nchhillar");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `users` WHERE `name` = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            boolean isAuthenticated = resultSet.next();
            int id  = resultSet.getInt("id");
            //String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String account = resultSet.getString("account");
            String balance = resultSet.getString("balance");
            int pin = resultSet.getInt("pin");
            JLabel nameLabel = new JLabel("Welcome, " + balance);
            System.out.println("ID: " + id + ", Name: " + name + ", Account Number: " + account + ", Balance: " + balance + ", Email: " + email + ", PIN: " + pin);

            resultSet.close();
            statement.close();
            connection.close();
            

        } catch (SQLException e) {
            System.out.println("Unable to fetch Database.");
        }

    	JPanel lightBlue = new JPanel();
        lightBlue.setBackground(new Color(0, 186, 242));
        lightBlue.setBounds(0, 0, 780, 40);

        JPanel darkBlue = new JPanel();
        darkBlue.setBackground(new Color(15,74,138));
        darkBlue.setBounds(0, 40, 780, 20);

        JPanel signupForm = new JPanel();
        signupForm.setBackground(Color.white);
        signupForm.setBounds(0, 0, 0, 0);
        signupForm.setPreferredSize(new Dimension(425, 215));
        signupForm.setLayout(new BorderLayout());
        
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Welcome to Paytm Java " + name);
        
        // Set the title position and alignment (optional)
        titledBorder.setTitlePosition(TitledBorder.CENTER);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        Border lineBorder = BorderFactory.createLineBorder(new Color(13, 74, 138), 2);

        titledBorder.setBorder(lineBorder);
        
        // Set the titled border as the panel's border
        signupForm.setBorder(titledBorder);

        setTitle("Paytm Java Bank");
        setSize(780, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        setLayout(null);
        add(lightBlue);
        ImageIcon icon = new ImageIcon("C:\\Users\\nchhi\\Desktop\\Paytm Java Bank\\src\\bank\\management\\system/bank_icon.png");
	    setIconImage(icon.getImage());
        add(darkBlue);
        add(nameLabel);

        int x = (getWidth() - signupForm.getPreferredSize().width) / 2;
        int y = (getHeight() - signupForm.getPreferredSize().height) / 2;
        signupForm.setBounds(x, y, signupForm.getPreferredSize().width, signupForm.getPreferredSize().height);
        add(signupForm);
    }
    
}
