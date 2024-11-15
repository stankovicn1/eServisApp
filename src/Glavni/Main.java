package Glavni;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        Label label = new Label("eServisnaKnjizica"); // Kreira labelu
        label.setFont(new Font("Arial", 55));

        Button dugme = new Button("Prijavi se"); // Kreira dugme koje vodi na stranicu za prijavu
        dugme.setStyle("-fx-font: 35 arial; -fx-base: #40c6de;"); //Stil dugmeta

        VBox vBox = new VBox(35, label, dugme); // Kreira vBox sa parametrom koji postavlja razmak izmedju elemenata u vBox - u
        vBox.setAlignment(Pos.CENTER); // Pozicionira vBox elemente na centar

        // Kreiranje scene i prikaz
        Scene scene = new Scene(vBox, 700, 500);
        primaryStage.setTitle("Test JavaFX vBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}