package bank.management.system;

import java.sql.*;

public class test {
    public static void main(String[] args) {
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
            ResultSet resultSet = statement.executeQuery(query);
            
            // Step 5: Process the query results
            while (resultSet.next()) {
                // Assuming "users" table has columns "id" and "name"
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                System.out.println("ID: " + id + ", Name: " + name);
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
