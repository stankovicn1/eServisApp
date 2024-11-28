package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveraLozinke {

    public boolean checkPassword(String password) {
        try (Connection connection = DbKonekcija.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT 1 FROM korisnici WHERE ime = ? AND lozinka = ?")) {

            pstmt.setString(1, "admin");
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Vraca true ako postoji korisnik sa ovim podacima
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Vraca false u slucaju greske ili neispravnih podataka
    }

}
