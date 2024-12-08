package Glavni;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.sql.*;

public class MojGUI {
    public MojGUI() throws SQLException {
    }

    public Scene getscene(Stage stage){
        Label label = new Label("eServisnaKnjizica");
        label.setFont(new Font("Arial", 55));

        Button dugme = new Button("Prijavi se");
        dugme.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");
        dugme.setOnAction(e -> stage.setScene(getscene2(stage)));
        VBox vbox = new VBox(35, label, dugme);
        vbox.setAlignment(Pos.CENTER);
        return new Scene(vbox, 700, 400);
    }

    public Scene getscene2(Stage stage){
        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));

        PasswordField lozinka = new PasswordField();
        lozinka.setPromptText("Unesite lozinku");

        Button dugme2 = new Button("Nazad");
        dugme2.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button dugmePrijava = new Button("Potvrdi");
        dugmePrijava.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        ProveraLozinke proveraLozinke = new ProveraLozinke();

        dugmePrijava.setOnAction(e -> {
            String unesenaLozinka = lozinka.getText();

            if (proveraLozinke.checkPassword(unesenaLozinka)) {
                stage.setScene(getscene3(stage));
            } else {
                prikaziPoruku("Greška", "Pogrešna lozinka.");
            }
        });

        dugme2.setOnAction(e -> stage.setScene(getscene(stage)));

        VBox vBoxdvojka = new VBox(35, labeldvojka, lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);
        return new Scene(vBoxdvojka, 700, 400);
    }

    private void prikaziPoruku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    public Scene getscene3(Stage stage){
        Button zaServisera = new Button("Servisi");
        zaServisera.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button unosServis = new Button("Novi unos");
        unosServis.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button evidencija = new Button("Evidencija");
        evidencija.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button zatvori = new Button("Izlaz");
        zatvori.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

       // evidencija.setOnAction(e -> otvoriProzorServisi());
        zaServisera.setOnAction(e -> otvoriProzorServisi());
        unosServis.setOnAction(e -> stage.setScene(getscenaZaUnos(stage)));
        zatvori.setOnAction(e -> Platform.exit());

        VBox treciProzor = new VBox(35, zaServisera, unosServis, evidencija, zatvori);
        treciProzor.setAlignment(Pos.CENTER);
        return new Scene(treciProzor, 700, 400);
    }
    private void otvoriProzorServisi() {
        // Inicijalizacija tabele
        tabelaServisa = new TableView<>(); // Kreiramo instancu TableView pre bilo kakvog korišćenja

        ObservableList<Vozilo> podaciIzBaze = FXCollections.observableArrayList();

        // Dobavljanje podataka iz baze
        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT model, datum, opis FROM vozila")) {

            while (rs.next()) {
                String model = rs.getString("model");
                String datum = rs.getString("datum");
                String opis = rs.getString("opis");

                Vozilo vozilo = new Vozilo("", model, "", "", opis, "", datum);
                podaciIzBaze.add(vozilo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Kreiranje kolona za tabelu
        TableColumn<Vozilo, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vozilo, String> datumColumn = new TableColumn<>("Datum");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<Vozilo, String> opisColumn = new TableColumn<>("Opis");
        opisColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));

        // Dodavanje kolona u tabelu
        tabelaServisa.getColumns().addAll(modelColumn, datumColumn, opisColumn);

        // Postavljanje podataka u tabelu
        tabelaServisa.setItems(podaciIzBaze);

        // Priprema prozora
        Stage stageServisera = new Stage();
        stageServisera.setTitle("Podaci o Servisima");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().add(tabelaServisa);

        Scene scene = new Scene(layout, 600, 400);
        stageServisera.setScene(scene);
        stageServisera.show();
    }
    private TableView<Vozilo> tabelaServisa; // Deklaracija tabele


    public Scene getscenaZaUnos(Stage stage){
        //Prozor za Novi unos

        // Label za marku automobila
        Label markaL = new Label("Izaberite klasu");
        markaL.setFont(Font.font(18));

// ComboBox za marku automobila
        ComboBox<String> klasa = new ComboBox<>();
        klasa.getItems().addAll("A", "B", "C", "E", "S", "G","CLA","CLS","GLA","GLB","GLC", "GLE", "GLS");
        klasa.setPromptText("Izaberite klasu automobila");

// Label za model automobila
        Label modelL = new Label("Izaberite model");
        modelL.setFont(Font.font(18));

// ComboBox za model automobila
        ComboBox<String> model = new ComboBox<>();
        model.setPromptText("Izaberite model automobila");

// Dodavanje primera modela u zavisnosti od marke
        klasa.setOnAction(e -> {
            model.getItems().clear(); // Očisti prethodne opcije
            String selectedBrand = klasa.getValue();
            if ("A".equals(selectedBrand)) {
                model.getItems().addAll("A 140");
                model.getItems().addAll("A 150");
                model.getItems().addAll("A 160");
                model.getItems().addAll("A 170");
                model.getItems().addAll("A 180");
                model.getItems().addAll("A 190");
                model.getItems().addAll("A 200");
                model.getItems().addAll("A 210");
                model.getItems().addAll("A 220");
                model.getItems().addAll("A 250");
                model.getItems().addAll("A 35 AMG");
                model.getItems().addAll("A 45 AMG");
            } else if ("B".equals(selectedBrand)) {
                model.getItems().addAll("B 150");
                model.getItems().addAll("B 160");
                model.getItems().addAll("B 170");
                model.getItems().addAll("B 180");
                model.getItems().addAll("B 200");
                model.getItems().addAll("B 220");
                model.getItems().addAll("B 250");
            } else if ("C".equals(selectedBrand)) {
                model.getItems().addAll("C 180");
                model.getItems().addAll("C 200");
                model.getItems().addAll("C 220");
                model.getItems().addAll("C 230");
                model.getItems().addAll("C 240");
                model.getItems().addAll("C 250");
                model.getItems().addAll("C 270");
                model.getItems().addAll("C 280");
                model.getItems().addAll("C 30 AMG");
                model.getItems().addAll("C 300");
                model.getItems().addAll("C 320");
                model.getItems().addAll("C 32 AMG");
                model.getItems().addAll("C 43 AMG");
                model.getItems().addAll("C 63 AMG");
            } else if ("E".equals(selectedBrand)) {
                model.getItems().addAll("E 200");
                model.getItems().addAll("E 220");
                model.getItems().addAll("E 230");
                model.getItems().addAll("E 240");
                model.getItems().addAll("E 250");
                model.getItems().addAll("E 260");
                model.getItems().addAll("E 270");
                model.getItems().addAll("E 280");
                model.getItems().addAll("E 290");
                model.getItems().addAll("E 300");
                model.getItems().addAll("E 320");
                model.getItems().addAll("E 350");
                model.getItems().addAll("E 400");
                model.getItems().addAll("E 43 AMG");
                model.getItems().addAll("E 450");
                model.getItems().addAll("E 500");
                model.getItems().addAll("E 53 AMG");
                model.getItems().addAll("E 63 AMG");
            } else if ("S".equals(selectedBrand)) {
                model.getItems().addAll("S 250");
                model.getItems().addAll("S 280");
                model.getItems().addAll("S 300");
                model.getItems().addAll("S 320");
                model.getItems().addAll("S 350");
                model.getItems().addAll("S 400");
                model.getItems().addAll("S 420");
                model.getItems().addAll("S 450");
                model.getItems().addAll("S 500");
                model.getItems().addAll("S 550");
                model.getItems().addAll("S 580");
                model.getItems().addAll("S 63 AMG");
            } else if ("G".equals(selectedBrand)) {
                model.getItems().addAll("G 290");
                model.getItems().addAll("G 300");
                model.getItems().addAll("G 320");
                model.getItems().addAll("G 350");
                model.getItems().addAll("G 400");
                model.getItems().addAll("G 450");
                model.getItems().addAll("G 500");
                model.getItems().addAll("G 63 AMG");
            } else if ("CLA".equals(selectedBrand)) {
                model.getItems().addAll("CLA 180");
                model.getItems().addAll("CLA 200");
                model.getItems().addAll("CLA 220");
                model.getItems().addAll("CLA 250");
                model.getItems().addAll("CLA 35 AMG");
                model.getItems().addAll("CLA 45 AMG");

            } else if ("CLS".equals(selectedBrand)) {
                model.getItems().addAll("CLS 220");
                model.getItems().addAll("CLS 250");
                model.getItems().addAll("CLS 300");
                model.getItems().addAll("CLS 320");
                model.getItems().addAll("CLS 350");
                model.getItems().addAll("CLS 400");
                model.getItems().addAll("CLS 450");
                model.getItems().addAll("CLS 500");
                model.getItems().addAll("CLS 53 AMG");
                model.getItems().addAll("CLS 63 AMG");
            } else if ("GLA".equals(selectedBrand)) {
                model.getItems().addAll("GLA 35 AMG");
                model.getItems().addAll("GLA 45 AMG");
                model.getItems().addAll("GLA 180");
                model.getItems().addAll("GLA 200");
                model.getItems().addAll("GLA 220");
                model.getItems().addAll("GLA 250");
            } else if ("GLB".equals(selectedBrand)) {
                model.getItems().addAll("GLB 180");
                model.getItems().addAll("GLB 200");
                model.getItems().addAll("GLB 220");
            } else if ("GLC".equals(selectedBrand)) {
                model.getItems().addAll("GLC 43 AMG");
                model.getItems().addAll("GLC 200");
                model.getItems().addAll("GLC 220");
                model.getItems().addAll("GLC 250");
                model.getItems().addAll("GLC 300");
                model.getItems().addAll("GLC 350");
            } else if ("GLE".equals(selectedBrand)) {
                model.getItems().addAll("GLE 43 AMG");
                model.getItems().addAll("GLE 53 AMG");
                model.getItems().addAll("GLE 63 AMG");
                model.getItems().addAll("GLE 250");
                model.getItems().addAll("GLE 300");
                model.getItems().addAll("GLE 350");
                model.getItems().addAll("GLE 400");
                model.getItems().addAll("GLE 450");
            } else if ("GLS".equals(selectedBrand)) {
                model.getItems().addAll("GLS 63 AMG");
                model.getItems().addAll("GLS 400");
                model.getItems().addAll("GLS 450");
                model.getItems().addAll("GLS 580");

            }
        });

        Label godisteL = new Label("Unesite godiste");
        godisteL.setFont(Font.font(18));
        TextField godiste = new TextField();
        godiste.setPromptText("Unesite godiste");

        godiste.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                godiste.setText(oldValue);
            }
        });

        Label registracijaL = new Label("Unesite registarske oznake");
        registracijaL.setFont(Font.font(18));
        TextField registracija = new TextField();;
        registracija.setPromptText("Unesite registarske oznake");

        Label opisL = new Label("Unesite opis");
        opisL.setFont(Font.font(18));
        TextArea opis = new TextArea();
        opis.setPromptText("Unesite opis servisa");

        Label emailL = new Label("Unesite email");
        emailL.setFont(Font.font(18));
        TextArea email = new TextArea();
        email.setPromptText("Unesite email");



        Label datumL = new Label("Izaberite datum");
        datumL.setFont(Font.font(18));
        DatePicker datum = new DatePicker();
        datum.setPromptText("Izaberite datum");

        Button nazad = new Button("Nazad");
        nazad.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button unos = new Button("Potrvdi");
        unos.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        nazad.setOnAction(e -> stage.setScene(getscene3(stage)));
        UnosUBazu u = new UnosUBazu(); // Kreira objekat klase za unos u bazu

        unos.setOnAction(e -> {
            try { // Dohvata vrednosti iz polja
                String izabranaKlasa = klasa.getValue();
                String izabraniModel = model.getValue();
                String unesenoGodiste = godiste.getText();
                String unesenaRegistracija = registracija.getText();
                String uneseniOpis = opis.getText();
                String uneseniEmail = email.getText();
                String izabranDatum = datum.getValue().toString();

                if (izabranaKlasa == null || izabraniModel == null || unesenaRegistracija.isEmpty()) {
                    prikaziPoruku("Greška", "Molimo popunite obavezna polja.");
                    return;
                }

                // Vrednosti se prosledjuju objektu preko konstruktora
                Vozilo novoVozilo = new Vozilo(
                        izabranaKlasa,
                        izabraniModel,
                        unesenoGodiste,
                        unesenaRegistracija,
                        uneseniOpis,
                        uneseniEmail,
                        izabranDatum
                );

                boolean unosUspesan = UnosUBazu.unosVozila(novoVozilo); // Objekat Vozilo se prosledjuje metodi za unos

                if (unosUspesan) {
                    prikaziPoruku("Uspešno", "Vozilo je uspešno uneto u bazu.");
                } else {
                    prikaziPoruku("Greška", "Došlo je do greške pri unosu podataka.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                prikaziPoruku("Greška", "Došlo je do greške: " + ex.getMessage());
            }
        });


        VBox noviUnos = new VBox(10,  markaL,klasa, modelL, model, godisteL, godiste, registracijaL,registracija, datumL,datum, opisL, opis,emailL,email, unos, nazad);
        noviUnos.setAlignment(Pos.CENTER);
        return new Scene(noviUnos, 1100, 900);

    }
}