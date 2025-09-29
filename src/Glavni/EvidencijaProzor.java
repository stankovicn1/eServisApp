package Glavni;

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

    // Referenca na prethodni prozor, da možemo da se vratimo nazad
    GlavniMeni prozor3 = new GlavniMeni();

    // Glavna metoda koja pravi scenu za unos registarskih oznaka
    public Scene getSceneEvidencija(Stage stage) {

        // Tekst iznad polja za unos
        Label label = new Label("Unesite registarske oznake:");

        // Polje za unos registarskih oznaka vozila
        TextField unosRegistracijeField = new TextField();
        unosRegistracijeField.setPromptText("npr. BG-123-AA"); // Tekst koji se vidi dok je prazno

        // Dugme koje potvrđuje unos
        Button potvrdiButton = new Button("Potvrdi");
        potvrdiButton.setOnAction(e -> {
            String unos = unosRegistracijeField.getText().trim(); // Uzima tekst iz polja
            if (!unos.isEmpty()) {
                // Ako je polje popunjeno, prelazi na sledeću scenu sa rezultatima
                stage.setScene(getSceneRezultat(stage, unos));
            }
        });

        // Dugme za povratak na prethodni prozor
        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(prozor3.getscene3(stage)));

        // Layout za unos i dugme (raspored u koloni, centrirano)
        VBox unosLayout = new VBox(10, label, unosRegistracijeField, potvrdiButton);
        unosLayout.setPadding(new Insets(20));
        unosLayout.setPrefWidth(300);
        unosLayout.setStyle("-fx-alignment: center;"); // Centriranje

        // Glavni layout prozora
        VBox layout = new VBox(20, unosLayout, nazadButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        // Kreiranje scene dimenzija 700x400
        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }

    // Metoda koja pravi scenu sa tabelom rezultata pretrage po registraciji
    private Scene getSceneRezultat(Stage stage, String registracija) {

        // Kreiranje TableView za prikaz podataka
        TableView<VoziloServis> tabela = new TableView<>();

        // Definisanje kolona i vezivanje sa atributima klase VoziloServis
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

        // Dodavanje svih kolona u tabelu
        tabela.getColumns().addAll(
                klasaColumn, modelColumn, godisteColumn,
                emailColumn, registracijaColumn,
                kilometrazaColumn, opisServisColumn
        );

        // ObservableList služi za čuvanje podataka koje tabela prikazuje
        ObservableList<VoziloServis> rezultat = FXCollections.observableArrayList();

        // SQL upit koji vraća podatke o vozilu i njegovom servisu
        try (Connection conn = DbKonekcija.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT v.id, v.klasa, v.model, v.godiste, v.kilometraza, v.email, v.registracija, s.opisServis " +
                             "FROM vozila v " +
                             "JOIN servis s ON v.id = s.vozilo_id " +
                             "WHERE v.registracija = ?")) {

            stmt.setString(1, registracija); // Postavlja vrednost za upit
            ResultSet rs = stmt.executeQuery(); // Izvršava upit

            // Čitanje podataka iz ResultSet-a i dodavanje u listu
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
            e.printStackTrace(); // Ispis greške ako nešto pođe po zlu
        }

        // Postavljanje rezultata u tabelu
        tabela.setItems(rezultat);

        // Dugme za povratak nazad na unos registarskih oznaka
        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(getSceneEvidencija(stage)));

        // Layout za tabelu i dugme
        VBox layout = new VBox(15, tabela, nazadButton);
        layout.setPadding(new Insets(20));

        // Kreiranje scene dimenzija 700x400
        Scene scene = new Scene(layout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }
}
