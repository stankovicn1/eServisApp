package Glavni;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PocetniProzor {
    public Scene getscene(Stage stage){ // Pocetni prozor sa prikazom poruke dobrodoslice i dugmetom za prelaz na sledeci prozor
        Label label = new Label("eServisnaKnjizica");
        label.setFont(new Font("Arial", 55));

        Button dugme = new Button("Prijavi se");
        dugme.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px;");
        dugme.setOnAction(e -> {

                    ProzorZaUnosLozinke prozorzaloz = new ProzorZaUnosLozinke();
                    stage.setScene(prozorzaloz.getscene2(stage));
                });
        VBox vbox = new VBox(35, label, dugme);
        vbox.setAlignment(Pos.CENTER);
        return new Scene(vbox, 700, 400);
    }

}
