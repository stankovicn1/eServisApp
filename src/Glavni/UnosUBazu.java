package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnosUBazu {

    // Metoda za unos podataka u tabelu Vozila
    public static void dodajVozilo(String klasa, String model, int godiste, String registracija, String email, String opis, String datum) {
        String sql = "INSERT INTO Vozila (klasa, model, godiste, registracija, email, opis, datum) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbKonekcija.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, klasa);
            pstmt.setString(2, model);
            pstmt.setInt(3, godiste);
            pstmt.setString(4, registracija);
            pstmt.setString(5, email);
            pstmt.setString(6, opis);
            pstmt.setString(7, datum);

            pstmt.executeUpdate();
            System.out.println("Podaci su uspešno uneseni u bazu.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
