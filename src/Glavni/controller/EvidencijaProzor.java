package Glavni.controller;

import Glavni.model.VoziloServis;
import Glavni.config.DbKonekcija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class EvidencijaProzor {

    // Referenca na prethodni prozor, sada private + getter
    private GlavniMeni prozor3 = new GlavniMeni();

    public GlavniMeni getProzor3() {
        return prozor3;
    }

    // Glavna metoda koja pravi scenu za unos registarskih oznaka
    public Scene getSceneEvidencija(Stage stage) {

        Label label = new Label("Unesite registarske oznake:");
        TextField unosRegistracijeField = new TextField();
        unosRegistracijeField.setPromptText("npr. BG-123-AA");

        Button potvrdiButton = new Button("Potvrdi");
        potvrdiButton.setOnAction(e -> {
            String unos = unosRegistracijeField.getText().trim();
            if (!unos.isEmpty()) {
                stage.setScene(getSceneRezultat(stage, unos));
            }
        });

        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(getProzor3().getscene3(stage))); // sada koristi getter

        VBox unosLayout = new VBox(10, label, unosRegistracijeField, potvrdiButton);
        unosLayout.setPadding(new Insets(20));
        unosLayout.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(20, unosLayout, nazadButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }

    // Metoda koja pravi scenu sa tabelom rezultata pretrage po registraciji
    private Scene getSceneRezultat(Stage stage, String registracija) {

        TableView<VoziloServis> tabela = new TableView<>();

        TableColumn<VoziloServis, String> klasaColumn = new TableColumn<>("Klasa");
        klasaColumn.setCellValueFactory(new PropertyValueFactory<>("klasa"));

        TableColumn<VoziloServis, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<VoziloServis, String> godisteColumn = new TableColumn<>("Godište");
        godisteColumn.setCellValueFactory(new PropertyValueFactory<>("godiste"));

        TableColumn<VoziloServis, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<VoziloServis, String> registracijaColumn = new TableColumn<>("Registracija");
        registracijaColumn.setCellValueFactory(new PropertyValueFactory<>("registracija"));

        TableColumn<VoziloServis, String> kilometrazaColumn = new TableColumn<>("Kilometraža");
        kilometrazaColumn.setCellValueFactory(new PropertyValueFactory<>("kilometraza"));

        TableColumn<VoziloServis, String> opisServisColumn = new TableColumn<>("Opis Servisa");
        opisServisColumn.setCellValueFactory(new PropertyValueFactory<>("opisServis"));

        tabela.getColumns().addAll(
                klasaColumn, modelColumn, godisteColumn,
                emailColumn, registracijaColumn,
                kilometrazaColumn, opisServisColumn
        );

        ObservableList<VoziloServis> rezultat = FXCollections.observableArrayList();

        try (Connection conn = DbKonekcija.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT v.id, v.klasa, v.model, v.godiste, v.kilometraza, v.email, v.registracija, s.opisServis " +
                             "FROM vozila v " +
                             "JOIN servis s ON v.id = s.vozilo_id " +
                             "WHERE v.registracija = ?")) {

            stmt.setString(1, registracija);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String klasa = rs.getString("klasa");
                String model = rs.getString("model");
                String godiste = rs.getString("godiste");
                String email = rs.getString("email");
                String reg = rs.getString("registracija");
                String kilometraza = rs.getString("kilometraza");
                String opisServis = rs.getString("opisServis");

                rezultat.add(new VoziloServis(id, klasa, model, godiste, reg, kilometraza, email, opisServis));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tabela.setItems(rezultat);

        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(getSceneEvidencija(stage)));

        VBox layout = new VBox(15, tabela, nazadButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }
}
