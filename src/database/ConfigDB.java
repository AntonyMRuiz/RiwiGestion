package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/riwiManager";
            String user = "root";
            String password = "";

            objConnection = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to database");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver not found " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error: Database failed (open)" + e.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection() {
        try {

            if (objConnection != null) {
                objConnection.close();
                System.out.println("Connection closed");
            }

        } catch (SQLException e) {
            System.out.println("Error: Database failed (close)" + e.getMessage());
        }
    }
}
