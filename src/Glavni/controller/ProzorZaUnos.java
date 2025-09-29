
package Glavni.controller;

import Glavni.service.UnosUBazu;
import Glavni.model.Vozilo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class ProzorZaUnos {
    public Scene getscenaZaUnos(Stage stage){
        //Prozor za Novi unos

        // Label za marku automobila
        Label markaL = new Label("Izaberite klasu");

        // ComboBox za marku automobila
        ComboBox<String> klasa = new ComboBox<>();
        klasa.getItems().addAll("V", "S", "XC");
        klasa.setPromptText("Izaberite klasu automobila");

        // Label za model automobila
        Label modelL = new Label("Izaberite model");

        // ComboBox za model automobila
        ComboBox<String> model = new ComboBox<>();
        model.setPromptText("Izaberite model automobila");

        // Dodavanje primera modela u zavisnosti od marke
        klasa.setOnAction(e -> {
            model.getItems().clear(); // Očisti prethodne opcije
            String selectedBrand = klasa.getValue();
            if ("V".equals(selectedBrand)) {
                model.getItems().addAll("V 40");
                model.getItems().addAll("V 50");
                model.getItems().addAll("V 60");
                model.getItems().addAll("V 70");
                model.getItems().addAll("V 90");

            } else if ("S".equals(selectedBrand)) {
                model.getItems().addAll("S 40");
                model.getItems().addAll("S 50");
                model.getItems().addAll("S 60");
                model.getItems().addAll("S 90");

            } else if ("XC".equals(selectedBrand)) {
                model.getItems().addAll("XC 40");
                model.getItems().addAll("XC 60");
                model.getItems().addAll("XC 90");


            }
        });

        Label godisteL = new Label("Unesite godiste");
        TextField godiste = new TextField();
        godiste.setPromptText("Unesite godiste");

        godiste.setPrefWidth(200);
        godiste.setMaxWidth(200);
        godiste.setMinWidth(150);

        godiste.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                godiste.setText(oldValue);
            }
        });

        Label registracijaL = new Label("Unesite registarske oznake");
        TextField registracija = new TextField();
        registracija.setPromptText("Unesite registarske oznake");

        registracija.setPrefWidth(200);
        registracija.setMaxWidth(200);
        registracija.setMinWidth(150);


        Label kilometrazaL = new Label("Unesite kilometrazu");
        TextField kilometraza = new TextField();
        kilometraza.setPromptText("Unesite kilometrazu");

        kilometraza.setPrefWidth(200);
        kilometraza.setMaxWidth(200);
        kilometraza.setMinWidth(150);

        Label emailL = new Label("Unesite email");
        TextField email = new TextField();
        email.setPromptText("Unesite email");

        email.setPrefWidth(200);
        email.setMaxWidth(200);
        email.setMinWidth(150);


/*// Dodavanje stilskih klasa
        godiste.getStyleClass().add("godiste-textfield");
        registracija.getStyleClass().add("registracija-textfield");
        kilometraza.getStyleClass().add("kilometraza-textfield");
        email.getStyleClass().add("email-textarea");

// Dodavanje stilskih klasa za labele
        godisteL.getStyleClass().add("small-label");
        registracijaL.getStyleClass().add("small-label");
        kilometrazaL.getStyleClass().add("small-label");
        emailL.getStyleClass().add("small-label");*/





// Dodavanje dugmadi
        Button nazad = new Button("Nazad");
        Button unos = new Button("Potvrdi");

        GlavniMeni proz = new GlavniMeni();
// Akcija dugmeta "Nazad"
        nazad.setOnAction(e -> stage.setScene(proz.getscene3(stage)));

        LoginProzor pzul = new LoginProzor();
// Akcija dugmeta "Unos"
        unos.setOnAction(e -> {
            try {
                // Dohvata vrednosti iz polja
                String izabranaKlasa = klasa.getValue();
                String izabraniModel = model.getValue();
                String unesenoGodiste = godiste.getText();
                String unesenaRegistracija = registracija.getText();
                String unesenaKilometraza = kilometraza.getText();
                String uneseniEmail = email.getText();
                Boolean naCekanju = true;


                // Provera obaveznih polja
                if (izabranaKlasa == null || izabraniModel == null || unesenaRegistracija.isEmpty()) {
                    pzul.prikaziPoruku("Greška", "Molimo popunite obavezna polja.");
                    return;
                }

                // Provera validnosti godine
                if (!unesenoGodiste.matches("\\d{4}") || Integer.parseInt(unesenoGodiste) < 1920 || Integer.parseInt(unesenoGodiste) > 2100) {
                    pzul.prikaziPoruku("Greška", "Godiste mora biti u rasponu od 1920 do 2100.");
                    return;
                }

                // Provera validnosti registarskih oznaka
                if (!unesenaRegistracija.matches("[A-Za-z]{2}-\\d{2,5}-[A-Za-z]{2}")) {
                    pzul.prikaziPoruku("Greška", "Registarske oznake moraju biti u formatu (XX-1234-XX).");
                    return;
                }

                // Provera validnosti email adrese
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$";
                if (!uneseniEmail.matches(emailRegex)) {
                    pzul.prikaziPoruku("Greška", "Email mora biti u validnom formatu (npr. ime@example.com).");
                    return;
                }

                // Kreiramo objekat vozila i unosimo ga u bazu
                int generisanId = 0;
                Vozilo novoVozilo = new Vozilo(
                        generisanId,           // Prosledjujemo generisan ID
                        izabranaKlasa,
                        izabraniModel,
                        unesenoGodiste,
                        unesenaRegistracija,
                        unesenaKilometraza,
                        uneseniEmail,
                        naCekanju
                );

                // Pozivamo metodu za unos u bazu
                boolean unosUspesan = UnosUBazu.unosVozila(novoVozilo);

                // Obavestavamo korisnika o uspešnom unosu ili grešci
                if (unosUspesan) {
                    pzul.prikaziPoruku("Uspešno", "Vozilo je uspešno uneto u bazu.");
                } else {
                    pzul.prikaziPoruku("Greška", "Došlo je do greške pri unosu podataka.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                pzul.prikaziPoruku("Greška", "Došlo je do greške: " + ex.getMessage());
            }
        });



        VBox noviUnos = new VBox(15,  markaL,klasa, modelL, model, godisteL, godiste, registracijaL,registracija, kilometrazaL, kilometraza /*datumL,datum, opisL, opis*/,emailL,email, unos, nazad);
        noviUnos.setAlignment(Pos.CENTER);

        Scene scene = new Scene(noviUnos, 700, 600); // Vaš VBox ili drugi layout
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        return scene;

    }
}
