
package Glavni;

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
        klasa.getItems().addAll("A", "B", "C", "E", "S", "G","CLA","CLS","GLA","GLB","GLC", "GLE", "GLS");
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
