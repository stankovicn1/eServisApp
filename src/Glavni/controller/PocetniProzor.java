package Glavni.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class PocetniProzor {
    public Scene getscene(Stage stage){ // Pocetni prozor sa prikazom poruke dobrodošlice i dugmetom za prelaz na sledeći prozor
        Label label = new Label("eServisnaKnjizica Volvo Servis");
        //label.setFont(new Font("Arial", 55));
        label.getStyleClass().add("large-label");

        Button dugme = new Button("Prijavi se");
        dugme.setOnAction(e -> {
                    LoginProzor prozorzaloz = new LoginProzor();
                    stage.setScene(prozorzaloz.getscene2(stage));
                });
        VBox vbox = new VBox(35, label, dugme);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }

}
