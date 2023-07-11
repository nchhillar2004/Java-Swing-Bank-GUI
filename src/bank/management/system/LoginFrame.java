package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;

public class LoginFrame extends JFrame{
	
    LoginFrame(){
        ImageIcon icon = new ImageIcon("C:\\Users\\nchhi\\Desktop\\Paytm Java Bank\\src\\bank\\management\\system/bank_icon.png");
		
	    JPanel lightBlue = new JPanel();
        lightBlue.setBackground(new Color(0, 186, 242));
        lightBlue.setBounds(0, 0, 800, 40);
        
        JPanel darkBlue = new JPanel();
        darkBlue.setBackground(new Color(13, 74, 138));
        darkBlue.setBounds(0, 40, 800, 20);
        
        JPanel panel = new JPanel();
        
        JPanel loginForm = new JPanel();
        loginForm.setBackground(Color.white);
        loginForm.setBounds(0, 0, 0, 0);
        loginForm.setPreferredSize(new Dimension(425, 215));
        loginForm.setLayout(new BorderLayout());
        
        // Create a sub-panel for the input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JTextField uidField = new JTextField();
        inputPanel.add(uidField);
        TitledBorder uidBorder = BorderFactory.createTitledBorder("Enter your Unique ID");
        uidField.setBorder(uidBorder);
        
        JTextField pinField = new JTextField(10);
        inputPanel.add(pinField);
        TitledBorder pinBorder = BorderFactory.createTitledBorder("Enter your PIN");
        pinField.setBorder(pinBorder);
        
       
        // Create a sub-panel for the submit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        JButton signupButton = new JButton("Signup");
        JButton adminLoginButton = new JButton("Admin Login");
        buttonPanel.add(submitButton);
        buttonPanel.add(signupButton);
        buttonPanel.add(adminLoginButton);

        // Add the sub-panels to the main panel
        loginForm.add(inputPanel, BorderLayout.CENTER);
        loginForm.add(buttonPanel, BorderLayout.SOUTH);
        
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Log In to your Paytm Java Account");
        
        // Set the title position and alignment (optional)
        titledBorder.setTitlePosition(TitledBorder.CENTER);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        Border lineBorder = BorderFactory.createLineBorder(new Color(13, 74, 138), 2);

        // Set the custom border as the border of the titled border
        titledBorder.setBorder(lineBorder);
        
        // Set the titled border as the panel's border
        loginForm.setBorder(titledBorder);
        
		setTitle("Login - Paytm Java");
		setSize(800, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setIconImage(icon.getImage());
		setLayout(null);
        add(lightBlue);
        add(darkBlue);
        int x = (getWidth() - loginForm.getPreferredSize().width) / 2;
        int y = (getHeight() - loginForm.getPreferredSize().height) / 2;
        loginForm.setBounds(x, y, loginForm.getPreferredSize().width, loginForm.getPreferredSize().height);
        add(loginForm);
        
        adminLoginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openAdminLoginFrame();
        	}
        });
        
        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform data validation and save to database
                String uidString = uidField.getText();
                String pinString = pinField.getText();
                int uidValue = Integer.parseInt(uidString);
                int pinValue = Integer.parseInt(pinString);
                String userName = getUserName(uidValue);
                boolean isAuthenticated = authenticateUser(uidValue, pinValue);

                if (validateData(uidString, pinString)){
                    if (isAuthenticated) {
                    	MainFrame mainFrame = new MainFrame(uidValue, userName);
                        openMainFrame();
                        dispose();
                     // Close the current frame
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid data entered. Please check your input.");
                    }
                }else{
                    JOptionPane.showMessageDialog(panel, "All fields are required. Please check your input");
                }

                
            }
        });
        
     // Add action listener to the login button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignupFrame();
                dispose(); // Close the current frame
            }
        });
    }

	public static void main(String[] args) {
		new LoginFrame();
	}
	
	// Validate the data entered in the input fields
    private static boolean validateData(String uidString, String pinString) {
        if(uidString.isEmpty() || pinString.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    // Save the data to the database
    public static boolean authenticateUser(int id, int pin) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "nchhillar");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `users` WHERE `id` = ? AND `pin` = ?");
            statement.setInt(1, id);
            statement.setInt(2, pin);
            ResultSet resultSet = statement.executeQuery();
            boolean isAuthenticated = resultSet.next();

            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String account = resultSet.getString("account");
            String balance = resultSet.getString("balance");
            //System.out.println("ID: " + id + ", Name: " + name + ", Account Number: " + account + ", Balance: " + balance + email);

            resultSet.close();
            statement.close();
            connection.close();

            return isAuthenticated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Open the MainFrame
    private static void openMainFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Open the MainFrame and pass the user data
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                
            }
        });
    }
    
    // Open the SignupFrame
    private static void openSignupFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SignupFrame signupFrame = new SignupFrame();
                signupFrame.setVisible(true);
            }
        });
    }
    
    // Open the AdminLoginFrame
    private static void openAdminLoginFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminLoginFrame adminLoginFrame = new AdminLoginFrame();
                adminLoginFrame.setVisible(true);
            }
        });
    }
    
    private String getUserName(int userId) {
        String userName = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "nchhillar");
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM users WHERE id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                userName = resultSet.getString("name");
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userName;
    }
}
