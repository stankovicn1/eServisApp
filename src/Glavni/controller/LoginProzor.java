package Glavni.controller;

import Glavni.model.ProveraLozinke;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginProzor {

    // Metoda koja pravi prozor za unos lozinke
    public Scene getscene2(Stage stage) {
        // Naslovna labela na vrhu prozora
        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));         // Podešavanje fonta i veličine teksta
        labeldvojka.setPadding(new Insets(20));             // Razmak oko teksta

        // Polje za unos lozinke (tekst se ne vidi jer je sakriven)
        PasswordField lozinka = new PasswordField();
        lozinka.setPromptText("Unesite lozinku");           // Tekst koji se vidi dok je polje prazno
        lozinka.setPrefWidth(200);                          // Željena širina polja
        lozinka.setMaxWidth(200);                           // Maksimalna širina
        lozinka.setMinWidth(150);                           // Minimalna širina

        // Dugme za povratak na početni prozor
        Button dugme2 = new Button("Nazad");

        // Dugme za potvrdu unosa lozinke
        Button dugmePrijava = new Button("Potvrdi");

        // Klasa koja proverava da li je lozinka ispravna
        ProveraLozinke proveraLozinke = new ProveraLozinke();

        // Akcija na klik dugmeta "Potvrdi"
        dugmePrijava.setOnAction(e -> {
            String unesenaLozinka = lozinka.getText();          // Čitanje unete lozinke

            GlavniMeni prozorzaopcije = new GlavniMeni();
            // Ako je lozinka tačna, prelazi na sledeći prozor
            // Ako nije, prikazuje poruku o grešci
            if (proveraLozinke.checkPassword(unesenaLozinka)) {
                stage.setScene(prozorzaopcije.getscene3(stage));
            } else {
                prikaziPoruku("Greška", "Pogrešna lozinka.");
            }
        });

        // Dugme "Nazad" vraća korisnika na početni prozor
        PocetniProzor pocetniProzor = new PocetniProzor();
        dugme2.setOnAction(e -> stage.setScene(pocetniProzor.getscene(stage)));

        // Glavni raspored elemenata u koloni
        VBox vBoxdvojka = new VBox(35, labeldvojka, lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);                 // Centrira sve elemente u VBox-u

        // Kreiranje scene sa dimenzijama 700x400 piksela
        Scene scene = new Scene(vBoxdvojka, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }

    // Metoda za prikaz iskačuće poruke (Alert dijalog)
    public void prikaziPoruku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  // Tip poruke: informacija
        alert.setTitle(naslov);                                // Naslov dijaloga
        alert.setContentText(poruka);                          // Tekst poruke
        alert.showAndWait();                                   // Prikazuje dijalog i čeka korisnikov odgovor
    }
}
