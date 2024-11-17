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

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    public static void main(String[] args) {

        DbKonekcija.getConnection();

        // Pokrece JavaFX aplikaciju
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        MojGUI mojGUI = new MojGUI(); // Kreira objekat klase MojGUI

        primaryStage.setScene(mojGUI.getscene(primaryStage)); // Postavlja prvu scenu kao pocetni prozor i prikazuje ga
        primaryStage.setTitle("eServis");
        primaryStage.show();

    }
    public void stop() {
        DbKonekcija.closeConnection();
    }
}