
package Glavni.gui;

import Glavni.service.UnosUBazu;
import Glavni.model.Vozilo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class ProzorZaUnos {
    public Scene getscenaZaUnos(Stage stage) {


        Label markaL = new Label("Volvo - izaberite klasu");


        ComboBox<String> klasa = new ComboBox<>();
        klasa.getItems().addAll("V", "S", "XC");
        klasa.setPromptText("Izaberite klasu automobila");


        Label modelL = new Label("Izaberite model");


        ComboBox<String> model = new ComboBox<>();
        model.setPromptText("Izaberite model automobila");


        klasa.setOnAction(e -> {
            model.getItems().clear();
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


        Button nazad = new Button("Nazad");
        Button unos = new Button("Potvrdi");

        GlavniMeni proz = new GlavniMeni();

        nazad.setOnAction(e -> stage.setScene(proz.getscene3(stage)));

        LoginProzor pzul = new LoginProzor();

        unos.setOnAction(e -> {
            try {

                String izabranaKlasa = klasa.getValue();
                String izabraniModel = model.getValue();
                String unesenoGodiste = godiste.getText();
                String unesenaRegistracija = registracija.getText();
                String unesenaKilometraza = kilometraza.getText();
                String uneseniEmail = email.getText();
                Boolean naCekanju = true;


                if (izabranaKlasa == null || izabraniModel == null || unesenaRegistracija.isEmpty()) {
                    pzul.prikaziPoruku("Greska", "Molimo popunite obavezna polja.");
                    return;
                }


                if (!unesenoGodiste.matches("\\d{4}") || Integer.parseInt(unesenoGodiste) < 1920 || Integer.parseInt(unesenoGodiste) > 2100) {
                    pzul.prikaziPoruku("Greska", "Godiste mora biti u rasponu od 1920 do 2100.");
                    return;
                }


                if (!unesenaRegistracija.matches("[A-Za-z]{2}-\\d{2,5}-[A-Za-z]{2}")) {
                    pzul.prikaziPoruku("Greska", "Registarske oznake moraju biti u formatu (XX-1234-XX).");
                    return;
                }


                String emailProvera = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$";
                if (!uneseniEmail.matches(emailProvera)) {
                    pzul.prikaziPoruku("Greska", "Email mora biti u validnom formatu (npr. ime@example.com).");
                    return;
                }


                int generisanId = 0;
                Vozilo novoVozilo = new Vozilo(
                        generisanId,
                        izabranaKlasa,
                        izabraniModel,
                        unesenoGodiste,
                        unesenaRegistracija,
                        unesenaKilometraza,
                        uneseniEmail,
                        naCekanju
                );


                boolean unosUspesan = UnosUBazu.unosVozila(novoVozilo);


                if (unosUspesan) {
                    pzul.prikaziPoruku("Uspesno", "Vozilo je uspesno uneto u bazu.");
                } else {
                    pzul.prikaziPoruku("Greska", "Doslo je do greske pri unosu podataka.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                pzul.prikaziPoruku("Greska", "Doslo je do greske: " + ex.getMessage());
            }
        });


        VBox noviUnos = new VBox(15, markaL, klasa, modelL, model, godisteL, godiste, registracijaL, registracija, kilometrazaL, kilometraza /*datumL,datum, opisL, opis*/, emailL, email, unos, nazad);
        noviUnos.setAlignment(Pos.CENTER);

        Scene scene = new Scene(noviUnos, 700, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        return scene;

    }
}