
package Glavni;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProzorZaUnos {
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

        Label kilometrazaL = new Label("Unesite kilometrazu");
        kilometrazaL.setFont(Font.font(18));
        TextField kilometraza = new TextField();
        kilometraza.setPromptText("Unestie kilometrazu");


        Label emailL = new Label("Unesite email");
        emailL.setFont(Font.font(18));
        TextArea email = new TextArea();
        email.setPromptText("Unesite email");

// Dodavanje dugmadi
        Button nazad = new Button("Nazad");
        nazad.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");
        Button unos = new Button("Potvrdi");
        unos.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        ProzorZaIzborOpcija proz = new ProzorZaIzborOpcija();
// Akcija dugmeta "Nazad"
        nazad.setOnAction(e -> stage.setScene(proz.getscene3(stage)));

        ProzorZaUnosLozinke pzul = new ProzorZaUnosLozinke();
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
                        generisanId,           // Prosleđujemo generisan ID
                        izabranaKlasa,
                        izabraniModel,
                        unesenoGodiste,
                        unesenaRegistracija,
                        unesenaKilometraza,
                        uneseniEmail
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



        VBox noviUnos = new VBox(10,  markaL,klasa, modelL, model, godisteL, godiste, registracijaL,registracija, kilometrazaL, kilometraza /*datumL,datum, opisL, opis*/,emailL,email, unos, nazad);
        noviUnos.setAlignment(Pos.CENTER);
        return new Scene(noviUnos, 1100, 900);

    }
}
