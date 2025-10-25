package Glavni.service;

import Glavni.db.DbKonekcija;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveraLozinke {

    public boolean checkPassword(String password) {
        try (Connection connection = DbKonekcija.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT 1 FROM admintab WHERE ime = ? AND lozinka = ?")) {

            pstmt.setString(1, "admin");
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
