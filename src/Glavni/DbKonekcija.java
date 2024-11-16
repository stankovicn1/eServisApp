package Glavni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbKonekcija {
    private static Connection connection;

    private DbKonekcija() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eServis", "root", "");
            }
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
    }
}

