package Glavni;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        //Pokrece JavaFX aplikaciju
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Testiranje JavaFX
        VBox vBox = new VBox();

        Label label = new Label("FX test");
        label.setFont(new Font("Arial", 20));
        vBox.getChildren().add(label);

        Button dugme = new Button("Pritisni");
        dugme.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        vBox.getChildren().add(dugme);

        // Kreiranje scene i prikaz
        Scene scene = new Scene(vBox, 400, 300);

        primaryStage.setTitle("Test JavaFX vBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}