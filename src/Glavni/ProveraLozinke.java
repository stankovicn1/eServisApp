package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveraLozinke {

    public boolean checkPassword(String password) {
        try (Connection connection = DbKonekcija.getConnection(); // Kreiranje konekcije
             PreparedStatement pstmt = connection.prepareStatement( // Priprema SQL upita
                     "SELECT 1 FROM admintab WHERE ime = ? AND lozinka = ?")) {

            pstmt.setString(1, "admin");
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) { // Izvrsava upit i vraca rezultat u vidu ResultSet objekta
                return rs.next(); // Vraca true ako postoji korisnik sa ovim podacima
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
