package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveraLozinke {

    public boolean checkPassword(String password) {
        boolean isValid = false; // Definise promenljivu koja ce oznaciti da li je lozinka ispravna ili ne

        try {
            Connection connection = DbKonekcija.getConnection();
            String query = "SELECT * FROM korisnici WHERE ime = ? AND lozinka = ?"; // Upit trazi korisnika sa imenom admin i lozinkom koja je uneta u passwordField
            PreparedStatement pstmt = connection.prepareStatement(query); // Kreira PreparedStatement za izvrsavanje SQL upita sa lozinkomm
            pstmt.setString(1, "admin"); // Postavlja vrednost za ime korisnika " admin "
            pstmt.setString(2, password); // Postavlja vrednost za lozinku na vrednost koju korisnik unese u passwordField
            ResultSet rs = pstmt.executeQuery(); // Izvrsava upit i dobija rezultat kao ResultSet
            isValid = rs.next(); // Ako upit vrati bar jedan red sto zanci da postoji korisnik sa imenom " admin " i tacnom lozinkom, metoda vraca true sto znaci da je lozinka tacnas
            rs.close(); // Zatvara ReslutSet da bi oslobodio resurse
            pstmt.close(); // Zatvara PreparedStatement kako bi oslobodio resurse
        } catch (SQLException e) { // Ako dodje do greske ispisuje je
            e.printStackTrace();
        }
        return isValid; // Vraca rezultat true ako je lozinka validna
    }
}
