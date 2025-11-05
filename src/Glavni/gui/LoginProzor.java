package Glavni.gui;

import Glavni.service.ProveraLozinke;
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


    public Scene getscene2(Stage stage) {

        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));
        labeldvojka.setPadding(new Insets(20));

        PasswordField lozinka = new PasswordField();
        lozinka.setPromptText("Unesite lozinku");
        lozinka.setPrefWidth(200);
        lozinka.setMaxWidth(200);
        lozinka.setMinWidth(150);

        Button dugme2 = new Button("Nazad");
        Button dugmePrijava = new Button("Potvrdi");

        ProveraLozinke proveraLozinke = new ProveraLozinke();

        dugmePrijava.setOnAction(e -> {
            String unesenaLozinka = lozinka.getText();

            GlavniMeni prozorzaopcije = new GlavniMeni();
            if (proveraLozinke.checkPassword(unesenaLozinka)) {
                stage.setScene(prozorzaopcije.getscene3(stage));
            } else {
                prikaziPoruku("Greska", "Pogresna lozinka.");
            }
        });

        PocetniProzor pocetniProzor = new PocetniProzor();
        dugme2.setOnAction(e -> stage.setScene(pocetniProzor.getscene(stage)));

        VBox vBoxdvojka = new VBox(35, labeldvojka, lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBoxdvojka, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }

    public void prikaziPoruku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }
}
