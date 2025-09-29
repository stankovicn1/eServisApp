package Glavni.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbKonekcija {
    private static Connection connection; // Statička promenljiva koja čuva jedinstvenu instancu

    private DbKonekcija() {} // Privatni konstruktor kako bi se sprečilo dalje instanciranje

    public static Connection getConnection() { // Metoda koja omogućava pristup konekciji sa bazom
        try {
            if (connection == null || connection.isClosed()) { // Proverava da li konekcija jos nije kreirana ili je zatvorena
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EServisnaknjigaMercedes", "root", ""); // Ako je konekcija null ili zatvorena kreira novu konekciju sa bazom
            }
        } catch (SQLException e) {
            System.err.println("Greska prilikom koneckcije na bazu " + e.getMessage()); // Ako je konekcija neuspešna prikazuje poruku
            e.printStackTrace(); // Ispisuje detalje o grešci
        }
        return connection; // Vraća konekciju koja može da bude nova ili postojeća
    }

    public static void closeConnection() { // Metoda koja zatvara konekciju
        try {
            if (connection != null && !connection.isClosed()) { // Proverava da li je konekcija otvorena ako jeste zatvara je
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Greska prilikom zatvaranja konekcije " + e.getMessage()); // Ako se desi greška ispisuje poruku
            e.printStackTrace();  // Pirkaz detalja o grešci
        }
    }
}

