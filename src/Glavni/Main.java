package Glavni;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        // Pokrece JavaFX aplikaciju
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Prvi prozor
        Label label = new Label("eServisnaKnjizica"); // Kreira labelu
        label.setFont(new Font("Arial", 55)); // Postavlja font i velicinu

        Button dugme = new Button("Prijavi se"); // Kreira dugme koje vodi na stranicu za prijavu
        dugme.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; "); // Stil dugmeta

        VBox vBox = new VBox(35, label, dugme); // Kreira vBox sa parametrom koji postavlja razmak izmedju elemenata u vBox - u
        vBox.setAlignment(Pos.CENTER); // Pozicionira vBox elemente na centar
        Scene scene = new Scene(vBox, 700, 500); // Kreira scenu

        // Drugi prozor
        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));

        PasswordField lozinka = new PasswordField(); // Polje za unos lozinke
        lozinka.setPromptText("Unesite lozinku");

        Button dugme2 = new Button("Nazad");
        dugme2.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button dugmePrijava = new Button("Potvrdi");
        dugmePrijava.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        VBox vBoxdvojka = new VBox(35,labeldvojka,lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(vBoxdvojka, 700, 500); // Kreira drugi prozor

        // Treci prozor
        Button unosServis = new Button("Novi unos");
        unosServis.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button evidencija = new Button("Evidencija");
        evidencija.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button zatvori = new Button("Izlaz");
        zatvori.setStyle("-fx-font: 28 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        VBox treciProzor = new VBox(35,unosServis,evidencija, zatvori);
        treciProzor.setAlignment(Pos.CENTER);
        Scene scene3 = new Scene(treciProzor, 700, 500); // Kreira treci prozor


        dugme.setOnAction(e -> primaryStage.setScene(scene2)); // Klikom na dugme prelazi na drugi prozor
        dugme2.setOnAction(e -> primaryStage.setScene(scene)); // Klikom na dugme prelazi na prvi prozor, odnosno vraca se nazad
        dugmePrijava.setOnAction(e -> primaryStage.setScene(scene3));
        zatvori.setOnAction(e -> Platform.exit()); // Dugme koje zatvara aplikaciju



        // Prikaza interfejsa
        primaryStage.setTitle("Test JavaFX vBox");
        primaryStage.setScene(scene);
        primaryStage.show();





    }
}