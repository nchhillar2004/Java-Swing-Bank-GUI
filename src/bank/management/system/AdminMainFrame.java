package bank.management.system;

import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class AdminMainFrame extends JFrame{
	AdminMainFrame(){
		fetchDatabase();
		this.setTitle("Admin* Paytm Java bank");
		this.setSize(780, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\nchhi\\Desktop\\Paytm Java Bank\\src\\bank\\management\\system/bank_icon.png");
	    this.setIconImage(icon.getImage());

        JPanel lightBlue = new JPanel();
        lightBlue.setBackground(new Color(0, 186, 242));
        lightBlue.setBounds(0, 0, 780, 40);
        this.add(lightBlue);

        JPanel darkBlue = new JPanel();
        darkBlue.setBackground(new Color(15,74,138));
        darkBlue.setBounds(0, 40, 780, 20);
        this.add(darkBlue);

        JLabel label = new JLabel();
        label.setText("Welcome to Paytm Java Bank");
        this.add(label);
        ImageIcon image = new ImageIcon("C:\\Users\\nchhi\\Desktop\\Paytm Java Bank\\src\\bank\\management\\system/bank_icon.png");
        label.setIcon(image);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AdminMainFrame();
	}
	
	public static void fetchDatabase(){
        String url = "jdbc:mysql://localhost/bank"; // Assuming the database is running locally
        String username = "root"; // Replace with your MySQL username
        String password = "nchhillar"; // Replace with your MySQL password
        
        try {
            // Step 1: Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Establish the database connection
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // Step 3: Create a statement object to execute SQL queries
            Statement statement = connection.createStatement();
            
            // Step 4: Execute the SQL query to fetch data from the "users" table
            String query = "SELECT * FROM users";
            String updateBalanceQuery = "UPDATE `bank`.`users` SET `balance` = '?' WHERE (`id` = '?')";
            String deleteUserQuery = "DELETE FROM `bank`.`users` WHERE (`id` = '?')";
            String addNewUserQuery = "INSERT INTO `bank`.`users` (`id`, `name`, `dob`, `address`, `email`, `phone`, `account`, `pin`, `balance`) VALUES ('?', '?', '?', '?', '?', '?', '?', '?', '?')";
            ResultSet resultSet = statement.executeQuery(query);
            
            // Step 5: Process the query results
            while (resultSet.next()) {
                // Assuming "users" table has columns "id" and "name"
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                String account = resultSet.getString("account");
                String balance = resultSet.getString("balance");
                System.out.println("ID: " + id + ", Name: " + name + ", Account Number: " + account + ", Balance: " + balance);
            }
            
            // Step 6: Close the connections
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed. Check username, password, and database URL");
            e.printStackTrace();
        }
    }

}
