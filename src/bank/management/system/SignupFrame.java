package bank.management.system;

import javax.swing.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;

public class SignupFrame extends JFrame{
    SignupFrame(){
        ImageIcon icon = new ImageIcon("C:\\Users\\nchhi\\Desktop\\Paytm Java Bank\\src\\bank\\management\\system/bank_icon.png");

	    JPanel lightBlue = new JPanel();
        lightBlue.setBackground(new Color(0, 186, 242));
        lightBlue.setBounds(0, 0, 800, 40);

        JPanel darkBlue = new JPanel();
        darkBlue.setBackground(new Color(13, 74, 138));
        darkBlue.setBounds(0, 40, 800, 20);
        
        JPanel panel = new JPanel();
        
        JPanel signupForm = new JPanel();
        signupForm.setBackground(Color.white);
        signupForm.setBounds(0, 0, 0, 0);
        signupForm.setPreferredSize(new Dimension(425, 215));
        signupForm.setLayout(new BorderLayout());
        
        // Create a sub-panel for the input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JTextField nameField = new JTextField();
        inputPanel.add(nameField);
        TitledBorder nameBorder = BorderFactory.createTitledBorder("Enter Full Name");
        nameField.setBorder(nameBorder);
        
        JTextField dobField = new JTextField(10);
        inputPanel.add(dobField);
        TitledBorder dobBorder = BorderFactory.createTitledBorder("Enter DOB");
        dobField.setBorder(dobBorder);
        
        JTextField addressField = new JTextField(10);
        inputPanel.add(addressField);
        TitledBorder addressBorder = BorderFactory.createTitledBorder("Permanent Address");
        addressField.setBorder(addressBorder);
        
        JTextField contactField = new JTextField(10);
        inputPanel.add(contactField);
        TitledBorder contactBorder = BorderFactory.createTitledBorder("Contact Number");
        contactField.setBorder(contactBorder);
        
        JTextField emailField = new JTextField(10);
        inputPanel.add(emailField);
        TitledBorder emailBorder = BorderFactory.createTitledBorder("Email Address");
        emailField.setBorder(emailBorder);
        
        JTextField pinField = new JTextField(10);
        inputPanel.add(pinField);
        TitledBorder pinBorder = BorderFactory.createTitledBorder("Create a PIN");
        pinField.setBorder(pinBorder);
        
       
        // Create a sub-panel for the submit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        JButton loginButton = new JButton("Login");
        buttonPanel.add(submitButton);
        buttonPanel.add(loginButton);

        // Add the sub-panels to the main panel
        signupForm.add(inputPanel, BorderLayout.CENTER);
        signupForm.add(buttonPanel, BorderLayout.SOUTH);
        
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Create a New Paytm Java Account");
        
        // Set the title position and alignment (optional)
        titledBorder.setTitlePosition(TitledBorder.CENTER);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        Border lineBorder = BorderFactory.createLineBorder(new Color(13, 74, 138), 2);

        // Set the custom border as the border of the titled border
        titledBorder.setBorder(lineBorder);
        
        // Set the titled border as the panel's border
        signupForm.setBorder(titledBorder);
        
       
        
		setTitle("Signup - Paytm Java");
		setSize(800, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setIconImage(icon.getImage());
		setLayout(null);
        add(lightBlue);
        add(darkBlue);
        int x = (getWidth() - signupForm.getPreferredSize().width) / 2;
        int y = (getHeight() - signupForm.getPreferredSize().height) / 2;
        signupForm.setBounds(x, y, signupForm.getPreferredSize().width, signupForm.getPreferredSize().height);
        add(signupForm);
        
        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random uid = new Random();
                int uidNumber = uid.nextInt(9000) + 1000; // Generate a random number between 1000 and 9999
                System.out.println("Random 4-digit number: " + uidNumber);
                int idValue = uidNumber;

                Random account = new Random();
                int accountNumber = account.nextInt(900000000) + 100000000; // Generate a random number between 1000 and 9999
                System.out.println("Random 16-digit number: " + accountNumber);
                String accountValue = String.valueOf(accountNumber);

                String nameValue = nameField.getText();
                String dobValue = dobField.getText();
                String addressValue = addressField.getText();
                String emailValue = emailField.getText();
                String contactValue = contactField.getText();
                String pinString = pinField.getText();
                int pinValue = Integer.parseInt(pinString);
                
                if (validateFields(nameValue, dobValue, addressValue, emailValue, contactValue)) {
                    if(validateEmail(emailValue)){
                        if(validatePhoneNumber(contactValue)){
                            if(validatePin(pinValue)){
                                saveToDatabase(idValue, nameValue, dobValue, addressValue, emailValue, contactValue, accountValue, pinValue);
                                JOptionPane.showMessageDialog(panel,"Sucessfully created account. " + idValue +" is your Unique ID and will be asked during Login process");
                                openMainFrame();
                                dispose(); // Close the current frame
                            }else{
                                JOptionPane.showMessageDialog(panel,"Create a valid 4 digit PIN Code. Please check your PIN.");
                            }
                        }else{
                            JOptionPane.showMessageDialog(panel,"Invalid Contact Number. Please check your number.");
                        }
                    }else{
                        JOptionPane.showMessageDialog(panel, "Invalid Email Address. Please check your email.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "All fields are required. Please check your input.");
                }
            }
        });
        
     // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginFrame();
                dispose(); // Close the current frame
            }
        });
    }

	public static void main(String[] args) {
		new SignupFrame();
	}

    private static boolean validateEmail(String emailValue) {
        // Regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        // Create a Pattern object from the regular expression
        Pattern pattern = Pattern.compile(emailRegex);
        
        // Create a Matcher object for the input email string
        Matcher matcher = pattern.matcher(emailValue);
        
        // Perform the matching and return the result
        return matcher.matches();
    }
    
    private static boolean validatePhoneNumber(String contactValue) {
        
        // Regular expression pattern for phone number validation
        String phoneRegex = "^\\d{10}$";
        
        // Create a Pattern object from the regular expression
        Pattern pattern = Pattern.compile(phoneRegex);
        
        // Create a Matcher object for the input phone number string
        Matcher matcher = pattern.matcher(contactValue);
        
        // Perform the matching and return the result
        return matcher.matches();
    }

    //Check whether all fields are filled or not
    private static boolean validateFields(String nameValue, String dobValue, String addressValue, String emailValue, String contactValue){
        if(nameValue.isEmpty() || dobValue.isEmpty() || addressValue.isEmpty() || emailValue.isEmpty() || contactValue.isEmpty() ){
            return false;
            
        }
        else{
            return true;
        }
    }

	// Validate the data entered in the input fields
    private static boolean validatePin(int pinValue) {
        if (pinValue>9999 || pinValue<1000){
            return false;
        }
        else{
            return true;
        }
    }

    // Save the data to the database
    private static void saveToDatabase(int idValue, String nameValue, String dobValue, String addressValue, String emailValue, String contactValue, String accountValue, int pinValue) {
        // Implement your database saving logic here
        // Use JDBC or any other database access mechanism to save the data
        // Example using JDBC:
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "nchhillar");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `bank`.`users` (`id`, `name`, `dob`, `address`, `email`, `phone`, `account`, `pin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, idValue);
            statement.setString(2, nameValue);
            statement.setString(3, dobValue);
            statement.setString(4, addressValue);
            statement.setString(5, emailValue);
            statement.setString(6, contactValue);
            statement.setString(7, accountValue);
            statement.setInt(8, pinValue);
            statement.executeUpdate();
            statement.close();
            connection.close();
            System.out.println("Data sucessfully saved");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in saving data");
        }
    }

    // Open the MainFrame
    private static void openMainFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame mainFrame = new LoginFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    // Open the LoginFrame
    private static void openLoginFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }    
}
