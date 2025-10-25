package Glavni.service;

import Glavni.db.DbKonekcija;
import Glavni.model.Servis;
import Glavni.model.Vozilo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UnosUBazu {


    public static boolean unosVozila(Vozilo vozilo) {

        String sql = "INSERT INTO vozila (klasa, model, godiste, registracija, kilometraza, email, naCekanju) VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (Connection konekcija = DbKonekcija.getConnection();
             PreparedStatement stmt = konekcija.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, vozilo.getKlasa());
            stmt.setString(2, vozilo.getModel());
            stmt.setString(3, vozilo.getGodiste());
            stmt.setString(4, vozilo.getRegistracija());
            stmt.setString(5, vozilo.getKilometraza());
            stmt.setString(6, vozilo.getEmail());
            stmt.setBoolean(7, true);


            int rezultat = stmt.executeUpdate();


            if (rezultat > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        vozilo.setId(rs.getInt(1));
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
            stmt.setInt(3, servis.getVoziloId());


            int rezultat = stmt.executeUpdate();


            if (rezultat > 0) {

                String emailSql = "SELECT email, model, registracija FROM vozila WHERE id = ?";
                try (PreparedStatement emailStmt = konekcija.prepareStatement(emailSql)) {
                    emailStmt.setInt(1, servis.getVoziloId());
                    try (ResultSet rs = emailStmt.executeQuery()) {
                        if (rs.next()) {
                            String email = rs.getString("email");
                            String model = rs.getString("model");
                            String registracija = rs.getString("registracija");

                        }
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

}
