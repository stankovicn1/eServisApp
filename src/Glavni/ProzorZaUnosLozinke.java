package Glavni;

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

public class ProzorZaUnosLozinke {
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

            ProzorZaIzborOpcija prozorzaopcije = new ProzorZaIzborOpcija();
            // Prosledjuje se String sa unosom na proveru i poziva se metoda za proveru lozinke iz klase ProveraLozinke, ako je uspesna postavlja sledeci prozor, odnosno prikazuje gresku
            if (proveraLozinke.checkPassword(unesenaLozinka)) {
                stage.setScene(prozorzaopcije.getscene3(stage));
            } else {
                prikaziPoruku("Greška", "Pogrešna lozinka.");
            }
        });
        PocetniProzor pocetniProzor = new PocetniProzor();
        dugme2.setOnAction(e -> stage.setScene(pocetniProzor.getscene(stage)));

        VBox vBoxdvojka = new VBox(35, labeldvojka, lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);
        return new Scene(vBoxdvojka, 700, 400);
    }
    // Metoda za prikaz poruke tipa Alert
    void prikaziPoruku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }



}

