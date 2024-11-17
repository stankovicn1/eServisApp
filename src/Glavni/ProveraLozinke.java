package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveraLozinke {

    public boolean checkPassword(String password) {
        boolean isValid = false;
        try {
            Connection connection = DbKonekcija.getConnection();
            String query = "SELECT * FROM korisnici WHERE lozinka = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, password);
            ResultSet rs = pstmt.executeQuery();
            isValid = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
