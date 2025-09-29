package Glavni.service;

import Glavni.config.DbKonekcija;
import Glavni.model.Servis;
import Glavni.model.Vozilo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UnosUBazu {

    // Metoda za unos novog vozila u bazu
    public static boolean unosVozila(Vozilo vozilo) {
        // SQL upit za unos vozila u tabelu "vozila"
        String sql = "INSERT INTO vozila (klasa, model, godiste, registracija, kilometraza, email, naCekanju) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Try-with-resources automatski zatvara konekciju i statement nakon izvršenja
        try (Connection konekcija = DbKonekcija.getConnection();
             PreparedStatement stmt = konekcija.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Postavljanje vrednosti u SQL upit iz objekta vozilo
            stmt.setString(1, vozilo.getKlasa());
            stmt.setString(2, vozilo.getModel());
            stmt.setString(3, vozilo.getGodiste());
            stmt.setString(4, vozilo.getRegistracija());
            stmt.setString(5, vozilo.getKilometraza());
            stmt.setString(6, vozilo.getEmail());
            stmt.setBoolean(7, true); // Automatski postavljamo da je na čekanju za servis

            // Izvršavanje upita
            int rezultat = stmt.executeUpdate();

            // Ako je unos uspešan
            if (rezultat > 0) {
                // Dohvatanje ID-a novog vozila iz baze (auto-generisan)
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        vozilo.setId(rs.getInt(1)); // Čuvamo ID u objektu vozilo
                    }
                }
                return true; // Uspešan unos
            }

            return false; // Ako nije ništa ubačeno
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ako dođe do greške u unosu
        }
    }

    // Metoda za unos servisa u bazu
    public static boolean unosServisa(Servis servis) {
        // SQL upit za unos podataka u tabelu "servis"
        String sql = "INSERT INTO servis (opisServis, datum, vozilo_id) VALUES (?, ?, ?)";

        try (Connection konekcija = DbKonekcija.getConnection();
             PreparedStatement stmt = konekcija.prepareStatement(sql)) {

            // Postavljanje vrednosti u SQL upit iz objekta servis
            stmt.setString(1, servis.getOpisServis());
            stmt.setString(2, servis.getDatum());
            stmt.setInt(3, servis.getVoziloId()); // Povezujemo servis sa vozilom preko ID-a

            // Izvršavanje upita
            int rezultat = stmt.executeUpdate();

            // Ako je unos uspešan
            if (rezultat > 0) {
                // Dohvatanje email-a vlasnika vozila radi eventualnog slanja obaveštenja
                String emailSql = "SELECT email, model, registracija FROM vozila WHERE id = ?";
                try (PreparedStatement emailStmt = konekcija.prepareStatement(emailSql)) {
                    emailStmt.setInt(1, servis.getVoziloId());
                    try (ResultSet rs = emailStmt.executeQuery()) {
                        if (rs.next()) {
                            String email = rs.getString("email");
                            String model = rs.getString("model");
                            String registracija = rs.getString("registracija");
                            // Ovde bi se kasnije dodalo slanje email poruke korisniku
                        }
                    }
                }
                return true; // Uspešno dodavanje servisa
            }

            return false; // Ako nije ubačen nijedan red
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ako dođe do greške
        }
    }

}
