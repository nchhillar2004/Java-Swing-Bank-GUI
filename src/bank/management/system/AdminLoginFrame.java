package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;

public class AdminLoginFrame extends JFrame{
    AdminLoginFrame(){
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
        buttonPanel.add(submitButton);
        buttonPanel.add(signupButton);

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
        
        JFrame frame = new JFrame();
		frame.setTitle("Admin* Login - Paytm Java");
		frame.setSize(800, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setIconImage(icon.getImage());
		frame.setLayout(null);
        frame.add(lightBlue);
        frame.add(darkBlue);
        int x = (frame.getWidth() - loginForm.getPreferredSize().width) / 2;
        int y = (frame.getHeight() - loginForm.getPreferredSize().height) / 2;
        loginForm.setBounds(x, y, loginForm.getPreferredSize().width, loginForm.getPreferredSize().height);
        frame.add(loginForm);
        
        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform data validation and save to database
                String uidString = uidField.getText();
                String pinString = pinField.getText();
                int uidValue = Integer.parseInt(uidString);
                int pinValue = Integer.parseInt(pinString);
                boolean isAuthenticated = authenticateUser(uidValue, pinValue);

                if (validateData(uidString, pinString)){
                    if (isAuthenticated) {

                        openMainFrame();
                        frame.dispose();
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
                frame.dispose(); // Close the current frame
            }
        });
    }

	public static void main(String[] args) {
		new AdminLoginFrame();
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
    private static boolean authenticateUser(int id, int pin) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "nchhillar");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `admin` WHERE `id` = ? AND `pin` = ?");
            statement.setInt(1, id);
            statement.setInt(2, pin);
            ResultSet resultSet = statement.executeQuery();
            boolean isAuthenticated = resultSet.next();

            String name = resultSet.getString("name");
            String contact = resultSet.getString("contact");
            String position = resultSet.getString("position");
            System.out.println("ID: " + id + ", Name: " + name + ", Contact" + contact +", Position: " + position);

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
                AdminMainFrame mainFrame = new AdminMainFrame();
                mainFrame.setVisible(true);
                
            }
        });
    }
    
    // Open the LoginFrame
    private static void openSignupFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SignupFrame signupFrame = new SignupFrame();
                signupFrame.setVisible(true);
            }
        });
    }
    
    
}
