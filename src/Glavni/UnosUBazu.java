package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnosUBazu {

    // Unos podataka u bazu
    public static boolean unosVozila(Vozilo vozilo) {
        String sql = "INSERT INTO vozila (klasa, model, godiste, registracija, opis, email, datum) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection konekcija = DbKonekcija.getConnection();
             PreparedStatement stmt = konekcija.prepareStatement(sql)) {

            stmt.setString(1, vozilo.getKlasa());
            stmt.setString(2, vozilo.getModel());
            stmt.setString(3, vozilo.getGodiste());
            stmt.setString(4, vozilo.getRegistracija());
            stmt.setString(5, vozilo.getOpis());
            stmt.setString(6, vozilo.getEmail());
            stmt.setString(7, vozilo.getDatum());

            int rezultat = stmt.executeUpdate();
            return rezultat > 0; // Vraća true ako je unos uspešan
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Vraća false u slučaju greške
        }
    }
}
