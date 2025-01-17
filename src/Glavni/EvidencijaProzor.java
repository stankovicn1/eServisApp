package Glavni;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.util.Objects;


public class EvidencijaProzor {

    ProzorZaIzborOpcija prozor3 = new ProzorZaIzborOpcija();

    public Scene getSceneEvidencija(Stage stage) {
        // Dugme za povratak
        Button nazadButton = new Button("Nazad");
        nazadButton.setOnAction(e -> stage.setScene(prozor3.getscene3(stage)));

        // Kreiranje TableView za prikaz podataka
        TableView<VoziloServis> tabelaEvidencija = new TableView<>();

        // Definisanje kolona
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

        // Nova kolona za opis servisa
        TableColumn<VoziloServis, String> opisServisColumn = new TableColumn<>("Opis Servisa");
        opisServisColumn.setCellValueFactory(new PropertyValueFactory<>("opisServis"));

        // Dodavanje kolona u TableView
        tabelaEvidencija.getColumns().addAll(klasaColumn, modelColumn, godisteColumn, emailColumn, registracijaColumn, kilometrazaColumn, opisServisColumn);

        // Ucitavanje podataka iz baze
        ObservableList<VoziloServis> podaciIzBaze = FXCollections.observableArrayList();

        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT v.id, v.klasa, v.model, v.godiste, v.kilometraza, v.email, v.registracija, s.opisServis " +
                     "FROM vozila v " +
                     "JOIN servis s ON v.id = s.vozilo_id")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String klasa = rs.getString("klasa");
                String model = rs.getString("model");
                String godiste = rs.getString("godiste");
                String email = rs.getString("email");
                String registracija = rs.getString("registracija");
                String kilometraza = rs.getString("kilometraza");
                String opisServis = rs.getString("opisServis");  // Opis servisa iz tabele servis

                // Kreiranje objekta VoziloServisDTO sa svim podacima
                VoziloServis voziloDTO = new VoziloServis(id, klasa, model, godiste, registracija, kilometraza, email, opisServis);
                podaciIzBaze.add(voziloDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Postavljanje podataka u tabelu
        tabelaEvidencija.setItems(podaciIzBaze);

        // Filter za pretragu
        FilteredList<VoziloServis> filtriraniPodaci = new FilteredList<>(podaciIzBaze, p -> true);
        TextField pretragaField = new TextField();
        Pretraga.inicijalizujPretragu(pretragaField, filtriraniPodaci, tabelaEvidencija);

        // Kreiranje layout-a
        VBox evidencijaLayout = new VBox(10, pretragaField, tabelaEvidencija, nazadButton);
        evidencijaLayout.setPadding(new Insets(20));


        Scene scene = new Scene(evidencijaLayout, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }
}

