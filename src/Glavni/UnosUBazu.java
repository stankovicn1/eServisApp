package Glavni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class UnosUBazu {

    // Unos podataka u bazu
    public static boolean unosVozila(Vozilo vozilo) {
        String sql = "INSERT INTO vozila (klasa, model, godiste, registracija, kilometraza, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection konekcija = DbKonekcija.getConnection();
             PreparedStatement stmt = konekcija.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, vozilo.getKlasa());
            stmt.setString(2, vozilo.getModel());
            stmt.setString(3, vozilo.getGodiste());
            stmt.setString(4, vozilo.getRegistracija());
            stmt.setString(5, vozilo.getEmail());

            int rezultat = stmt.executeUpdate();

            if (rezultat > 0) {
                // Ako je unos uspešan, uzimamo poslednji generisani ID
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        vozilo.setId(rs.getInt(1)); // Postavljamo ID u objekat vozilo
                    }
                }
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean unosServisa(Servis servis) {
        String sql = "INSERT INTO servis (opisServis, datum, vozilo_id) VALUES (?, ?, ?)";

        try (Connection konekcija = DbKonekcija.getConnection();
             PreparedStatement stmt = konekcija.prepareStatement(sql)) {

            stmt.setString(1, servis.getOpisServis());
            stmt.setString(2, servis.getDatum());
            stmt.setInt(3, servis.getVoziloId()); // Vozilo ID iz objekta servis

            int rezultat = stmt.executeUpdate();
            return rezultat > 0; // Vraća true ako je unos uspešan
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Vraća false u slučaju greške
        }
    }
    }

