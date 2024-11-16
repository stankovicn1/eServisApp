package Glavni;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MojGUI {
    public Scene getscene(Stage stage){
        // Prvi prozor
        Label label = new Label("eServisnaKnjizica"); // Kreira labelu
        label.setFont(new Font("Arial", 55)); // Postavlja font i velicinu

        Button dugme = new Button("Prijavi se"); // Kreira dugme koje vodi na stranicu za prijavu
        dugme.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; "); // Stil dugmeta
        dugme.setOnAction(e -> stage.setScene(getscene2(stage))); // Postavlja prelaz na scene2 klikom na dugme
        VBox vbox = new VBox(35, label, dugme); // Kreira VBox i prosledjuje razmak izmedju elemenata i elemente
        vbox.setAlignment(Pos.CENTER); // Pozicionira vBox na centar
        return new Scene(vbox, 700, 400); // vraca Scenu

    }

    public Scene getscene2(Stage stage){
        // Drugi prozor
        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));

        PasswordField lozinka = new PasswordField(); // Polje za unos lozinke
        lozinka.setPromptText("Unesite lozinku");

        Button dugme2 = new Button("Nazad");
        dugme2.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button dugmePrijava = new Button("Potvrdi");
        dugmePrijava.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        dugme2.setOnAction(e -> stage.setScene(getscene(stage)));
        dugmePrijava.setOnAction(e -> stage.setScene(getscene3(stage)));

        VBox vBoxdvojka = new VBox(35,labeldvojka,lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);
        return new Scene(vBoxdvojka, 700, 400);

    }

    public Scene getscene3(Stage stage){
        // Treci prozor
        Button unosServis = new Button("Novi unos");
        unosServis.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button evidencija = new Button("Evidencija");
        evidencija.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button zatvori = new Button("Izlaz");
        zatvori.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        zatvori.setOnAction(e -> Platform.exit()); // Zatvara aplikaciju

        VBox treciProzor = new VBox(35,unosServis,evidencija, zatvori);
        treciProzor.setAlignment(Pos.CENTER);
        return new Scene(treciProzor, 700,400);

    }
}
