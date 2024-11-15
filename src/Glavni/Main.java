package Glavni;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        //Pokrece JavaFX aplikaciju
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Test JavaFX");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Test JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}