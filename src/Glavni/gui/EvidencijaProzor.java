package Glavni.gui;

import Glavni.model.VoziloServis;
import Glavni.db.DbKonekcija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Date;
import java.util.Objects;

public class EvidencijaProzor {

    private GlavniMeni prozor3 = new GlavniMeni();

    public GlavniMeni getProzor3() {
        return prozor3;
    }

    public Scene getSceneEvidencija(Stage stage) {

        Label label = new Label("Unesite registarske oznake:");
        TextField unosRegistracijeField = new TextField();
        unosRegistracijeField.setPromptText("npr. BG-123-AA");

        Button potvrdiButton = new Button("Potvrdi");
        potvrdiButton.setOnAction(e -> {
            String unos = unosRegistracijeField.getText().trim();
            if (!unos.isEmpty()) {
                stage.setScene(getScenePrikazPodataka(stage, unos));
            }
        });

        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(getProzor3().getscene3(stage)));

        VBox unosLayout = new VBox(10, label, unosRegistracijeField, potvrdiButton);
        unosLayout.setPadding(new Insets(20));
        unosLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, unosLayout, nazadButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }


    private Scene getScenePrikazPodataka(Stage stage, String registracija) {

        Label lblKlasa = new Label();
        Label lblModel = new Label();
        Label lblGodiste = new Label();
        Label lblReg = new Label();
        Label lblEmail = new Label();

        try (Connection conn = DbKonekcija.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, klasa, model, godiste, kilometraza, email, registracija " +
                             "FROM vozila WHERE registracija = ?")) {

            stmt.setString(1, registracija);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lblKlasa.setText("Klasa: " + rs.getString("klasa"));
                lblModel.setText("Model: " + rs.getString("model"));
                lblGodiste.setText("Godište: " + rs.getString("godiste"));
                lblReg.setText("Registracija: " + rs.getString("registracija"));
                lblEmail.setText("Email: " + rs.getString("email"));
            } else {
                lblKlasa.setText("Nije pronadjeno vozilo sa unetom registracijom");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Button istorijaButton = new Button("Istorija radova");
        Button nazadButton = new Button("Nazad");

        istorijaButton.setOnAction(e -> stage.setScene(getSceneIstorijaRadova(stage, registracija)));
        nazadButton.setOnAction(e -> stage.setScene(getSceneEvidencija(stage)));

        VBox layout = new VBox(15,
                lblKlasa, lblModel, lblGodiste,
                lblReg, lblEmail,
                istorijaButton, nazadButton
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }


    private Scene getSceneIstorijaRadova(Stage stage, String registracija) {

        TableView<VoziloServis> tabela = new TableView<>();

        TableColumn<VoziloServis, String> opisServisColumn = new TableColumn<>("Opis servisa");
        opisServisColumn.setCellValueFactory(new PropertyValueFactory<>("opisServis"));

        TableColumn<VoziloServis, String> kilometrazaColumn = new TableColumn<>("Kilometraža");
        kilometrazaColumn.setCellValueFactory(new PropertyValueFactory<>("kilometraza"));

        TableColumn<VoziloServis, Date> datumColumn = new TableColumn<>("Datum");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

        // ===== Nova kolona za cenu =====
        TableColumn<VoziloServis, Double> cenaColumn = new TableColumn<>("Cena (RSD)");
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));  // mora postojati getCena() u VoziloServis

        tabela.getColumns().addAll(opisServisColumn, kilometrazaColumn, datumColumn, cenaColumn);

        ObservableList<VoziloServis> rezultat = FXCollections.observableArrayList();

        try (Connection conn = DbKonekcija.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT s.opisServis, v.kilometraza, s.datum, s.cena " +
                             "FROM servis s " +
                             "JOIN vozila v ON v.id = s.vozilo_id " +
                             "WHERE v.registracija = ?")) {

            stmt.setString(1, registracija);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String opis = rs.getString("opisServis");
                String km = rs.getString("kilometraza");
                Date datum = rs.getDate("datum");
                double cena = rs.getDouble("cena"); // preuzimanje cene
                rezultat.add(new VoziloServis(0, "", "", "", registracija, km, "", opis, datum, cena));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tabela.setItems(rezultat);

        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(getScenePrikazPodataka(stage, registracija)));

        VBox layout = new VBox(15, tabela, nazadButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }

}
