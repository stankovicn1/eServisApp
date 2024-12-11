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
    public MojGUI() throws SQLException {
    }

    public Scene getscene(Stage stage){ // Pocetni prozor sa prikazom poruke dobrodoslice i dugmetom za prelaz na sledeci prozor
        Label label = new Label("eServisnaKnjizica");
        label.setFont(new Font("Arial", 55));

        Button dugme = new Button("Prijavi se");
        dugme.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");
        dugme.setOnAction(e -> stage.setScene(getscene2(stage)));
        VBox vbox = new VBox(35, label, dugme);
        vbox.setAlignment(Pos.CENTER);
        return new Scene(vbox, 700, 400);
    }

    public Scene getscene2(Stage stage){ // Prozor za unos lozinke sa dugmetom nazad i potvrdi
        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));
        labeldvojka.setPadding(new Insets(20));

        PasswordField lozinka = new PasswordField();
        lozinka.setPromptText("Unesite lozinku");

        Button dugme2 = new Button("Nazad");
        dugme2.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button dugmePrijava = new Button("Potvrdi");
        dugmePrijava.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        ProveraLozinke proveraLozinke = new ProveraLozinke(); // Kreira objekat klase ProveraLozinke

        // Dugme prihvata unos teksta i smesta ga u promenljivu tipa String
        dugmePrijava.setOnAction(e -> {
            String unesenaLozinka = lozinka.getText();

            // Prosledjuje se String sa unosom na proveru i poziva se metoda za proveru lozinke iz klase ProveraLozinke, ako je uspesna postavlja sledeci prozor, odnosno prikazuje gresku
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
    // Metoda za prikaz poruke tipa Alert
    private void prikaziPoruku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    public Scene getscene3(Stage stage){  // Prozor za izbor jedne od opcija
        Button zaServisera = new Button("Servisi");
        zaServisera.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button unosServis = new Button("Novi unos");
        unosServis.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button evidencija = new Button("Evidencija");
        evidencija.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        Button zatvori = new Button("Izlaz");
        zatvori.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        // Funkcionalnosti za button
        evidencija.setOnAction(e -> stage.setScene(getSceneEvidencija(stage)));
        zaServisera.setOnAction(e -> otvoriProzorServisi());
        unosServis.setOnAction(e -> stage.setScene(getscenaZaUnos(stage)));
        zatvori.setOnAction(e -> Platform.exit());

        VBox treciProzor = new VBox(35, zaServisera, unosServis, evidencija, zatvori);
        treciProzor.setAlignment(Pos.CENTER);
        return new Scene(treciProzor, 700, 400);
    }
    private TableView<Vozilo> tabelaServisa; // Deklaracija tabele

    private void otvoriProzorServisi() {
        // Inicijalizacija tabele
        tabelaServisa = new TableView<>(); // Kreira novu instancu TableView za prikaz podataka

        // Kreira listu koja ce cuvati podatke iz baze
        ObservableList<Vozilo> podaciIzBaze = FXCollections.observableArrayList();

        // Dobavljanje podataka iz baze
        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, model, registracija FROM vozila")) { // SQL upit za dobijanje podataka o vozilima

            // Petlja kroz rezultate upita
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String registracija = rs.getString("registracija");

                // Kreira objekat vozilo sa ucitanim podacima
                Vozilo vozilo = new Vozilo(id, "", model, "", registracija, "");
                podaciIzBaze.add(vozilo); // Dodaje vozilo u listu podataka
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Kreiranje kolona za tabelu
        TableColumn<Vozilo, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vozilo, String> registracijaColumn = new TableColumn<>("Registracija");
        registracijaColumn.setCellValueFactory(new PropertyValueFactory<>("registracija"));

        // Dodavanje kolona u tabelu
        tabelaServisa.getColumns().addAll(modelColumn, registracijaColumn);
        tabelaServisa.setItems(podaciIzBaze);

        // Dodavanje detekcije duplog klika
        tabelaServisa.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Provera da li je kliknut dupli klik
                Vozilo selectedVozilo = tabelaServisa.getSelectionModel().getSelectedItem();
                if (selectedVozilo != null) {
                    otvoriProzorSaDetaljima(selectedVozilo);
                }
            }
        });

        // Kreiranje prozora
        Stage stageServisera = new Stage();
        stageServisera.setTitle("Podaci o Servisima");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().add(tabelaServisa);

        Scene scene = new Scene(layout, 800, 400);
        stageServisera.setScene(scene);
        stageServisera.show();
    }

    // Metoda koja otvara novi prozor sa podacima o vozilu
    private void otvoriProzorSaDetaljima(Vozilo vozilo) {
        Stage noviProzor = new Stage();
        noviProzor.setTitle("Detalji o vozilu");

        // Kreiranje labela za prikaz podataka
        Label modelLabel = new Label("Model: " + vozilo.getModel());
        Label registracijaLabel = new Label("Registracija: " + vozilo.getRegistracija());
        modelLabel.setFont(Font.font(18));
        registracijaLabel.setFont(Font.font(18));

        // Labela i TextArea za unos opisa
        Label opisLab = new Label("Unesite opis servisa");
        opisLab.setFont(Font.font(18));
        TextArea opiis = new TextArea();
        opiis.setPromptText("Unesite opis");

        // Dodavanje DatePicker-a za odabir datuma
        Label datumLab = new Label("Odaberite datum servisa");
        DatePicker datePicker = new DatePicker();  // Kreiranje DatePicker-a

        // Dugme za potvrdu
        Button potvrdiButton = new Button("Potvrdi");
        potvrdiButton.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        potvrdiButton.setOnAction(e -> {
            // Prikupljanje podataka iz TextArea i DatePicker-a
            String opis = opiis.getText();
            LocalDate datum = datePicker.getValue();

            if (datum != null && !opis.isEmpty()) {
                // Kreiranje Servis objekta sa unesenim podacima
                // Pretpostavljamo da je idServis automatski generisan u bazi, pa šaljemo 0 kao placeholder
                Servis servis = new Servis(0, opis, datum.toString(), vozilo.getId()); // Koristi vozilo ID iz objekta vozilo

                // Pozivanje metode za unos servisa
                boolean uspeh = unosServisa(servis);
                if (uspeh) {
                    System.out.println("Uspešno ste uneli servis.");
                    noviProzor.close();  // Zatvori prozor nakon uspešnog unosa
                } else {
                    System.out.println("Došlo je do greške pri unosu servisa.");
                }
            } else {
                System.out.println("Molimo unesite sve podatke.");
            }
        });

        // Dodavanje svih elemenata u layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(modelLabel, registracijaLabel, opisLab, opiis, datumLab, datePicker, potvrdiButton);

        // Kreiranje scene
        Scene scene = new Scene(layout, 800, 500);
        noviProzor.setScene(scene);
        noviProzor.show();
    }



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
        TextField registracija = new TextField();
        registracija.setPromptText("Unesite registarske oznake");

        Label emailL = new Label("Unesite email");
        emailL.setFont(Font.font(18));
        TextArea email = new TextArea();
        email.setPromptText("Unesite email");

// Dodavanje dugmadi
        Button nazad = new Button("Nazad");
        nazad.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");
        Button unos = new Button("Potvrdi");
        unos.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

// Akcija dugmeta "Nazad"
        nazad.setOnAction(e -> stage.setScene(getscene3(stage)));

// Akcija dugmeta "Unos"
        unos.setOnAction(e -> {
            try {
                // Dohvata vrednosti iz polja
                String izabranaKlasa = klasa.getValue();
                String izabraniModel = model.getValue();
                String unesenoGodiste = godiste.getText();
                String unesenaRegistracija = registracija.getText();
                String uneseniEmail = email.getText();

                // Provera obaveznih polja
                if (izabranaKlasa == null || izabraniModel == null || unesenaRegistracija.isEmpty()) {
                    prikaziPoruku("Greška", "Molimo popunite obavezna polja.");
                    return;
                }

                // Provera validnosti godine
                if (!unesenoGodiste.matches("\\d{4}") || Integer.parseInt(unesenoGodiste) < 1920 || Integer.parseInt(unesenoGodiste) > 2100) {
                    prikaziPoruku("Greška", "Godiste mora biti u rasponu od 1920 do 2100.");
                    return;
                }

                // Provera validnosti registarskih oznaka
                if (!unesenaRegistracija.matches("[A-Za-z]{2}-\\d{2,5}-[A-Za-z]{2}")) {
                    prikaziPoruku("Greška", "Registarske oznake moraju biti u formatu (XX-1234-XX).");
                    return;
                }

                // Provera validnosti email adrese
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$";
                if (!uneseniEmail.matches(emailRegex)) {
                    prikaziPoruku("Greška", "Email mora biti u validnom formatu (npr. ime@example.com).");
                    return;
                }

                // Kreiramo objekat vozila i unosimo ga u bazu
                int generisanId = 0;
                Vozilo novoVozilo = new Vozilo(
                        generisanId,           // Prosleđujemo generisan ID
                        izabranaKlasa,
                        izabraniModel,
                        unesenoGodiste,
                        unesenaRegistracija,
                        uneseniEmail
                );

                // Pozivamo metodu za unos u bazu
                boolean unosUspesan = UnosUBazu.unosVozila(novoVozilo);

                // Obavestavamo korisnika o uspešnom unosu ili grešci
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



        VBox noviUnos = new VBox(10,  markaL,klasa, modelL, model, godisteL, godiste, registracijaL,registracija /*datumL,datum, opisL, opis*/,emailL,email, unos, nazad);
        noviUnos.setAlignment(Pos.CENTER);
        return new Scene(noviUnos, 1100, 900);

    }
    public Scene getSceneEvidencija(Stage stage) {
        // Dugme za povratak
        Button nazadButton = new Button("Nazad");
        nazadButton.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");

        nazadButton.setOnAction(e -> stage.setScene(getscene3(stage)));

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