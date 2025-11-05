package Glavni.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbKonekcija {
    private static Connection connection;

    private DbKonekcija() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eservis", "root", "");
            }
        } catch (SQLException e) {
            System.err.println("Greska prilikom koneckcije na bazu " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Greska prilikom zatvaranja konekcije " + e.getMessage());
            e.printStackTrace();
        }
    }
}

