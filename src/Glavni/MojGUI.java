package Glavni;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.time.LocalDate;

import static Glavni.UnosUBazu.unosServisa;


public class MojGUI {
   /* public MojGUI() throws SQLException {
    }*/
    ProzorZaIzborOpcija prozor3 = new ProzorZaIzborOpcija();
    public Scene getSceneEvidencija(Stage stage) {
        // Dugme za povratak
        Button nazadButton = new Button("Nazad");
        nazadButton.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        nazadButton.setOnAction(e -> stage.setScene(prozor3.getscene3(stage)));

        // Kreiranje TableView za prikaz podataka
        TableView<Vozilo> tabelaEvidencija = new TableView<>();

        // Definisanje kolona
        TableColumn<Vozilo, String> klasaColumn = new TableColumn<>("Klasa");
        klasaColumn.setCellValueFactory(new PropertyValueFactory<>("klasa"));

        TableColumn<Vozilo, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

       /* TableColumn<Vozilo, String> datumColumn = new TableColumn<>("Datum");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));*/

        TableColumn<Vozilo, String> godisteColumn = new TableColumn<>("Godište");
        godisteColumn.setCellValueFactory(new PropertyValueFactory<>("godiste"));

       /* TableColumn<Vozilo, String> opisColumn = new TableColumn<>("Opis");
        opisColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));*/

        TableColumn<Vozilo, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Vozilo, String> registracijaColumn = new TableColumn<>("Registracija");
        registracijaColumn.setCellValueFactory(new PropertyValueFactory<>("registracija"));

        tabelaEvidencija.getColumns().addAll(klasaColumn, modelColumn, /*datumColumn,*/ godisteColumn, /*opisColumn, */emailColumn, registracijaColumn);

        // Učitavanje podataka iz baze
        ObservableList<Vozilo> podaciIzBaze = FXCollections.observableArrayList();

        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, klasa, model, godiste, email, registracija FROM vozila")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String klasa = rs.getString("klasa");
                String model = rs.getString("model");
               // String datum = rs.getString("datum");
                String godiste = rs.getString("godiste");
                //String opis = rs.getString("opis");
                String email = rs.getString("email");
                String registracija = rs.getString("registracija");

                // Kreiranje objekta Vozilo sa id-jem
                Vozilo vozilo = new Vozilo(id, klasa, model, godiste, registracija, email);
                podaciIzBaze.add(vozilo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Postavljanje podataka u tabelu
        tabelaEvidencija.setItems(podaciIzBaze);

        FilteredList<Vozilo> filtriraniPodaci = new FilteredList<>(podaciIzBaze, p -> true);
        TextField pretragaField = new TextField();

        // Inicijalizacija pretrage
        Pretraga.inicijalizujPretragu(pretragaField, filtriraniPodaci, tabelaEvidencija);

        // Kreiranje layout-a
        VBox evidencijaLayout = new VBox(10, pretragaField, tabelaEvidencija, nazadButton);
        evidencijaLayout.setPadding(new Insets(20));

        return new Scene(evidencijaLayout, 800, 500);
    }
}