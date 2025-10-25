package Glavni;

import Glavni.db.DbKonekcija;
import Glavni.gui.PocetniProzor;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        DbKonekcija.getConnection();

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        PocetniProzor pocetak = new PocetniProzor();

        primaryStage.setScene(pocetak.getscene(primaryStage));
        primaryStage.setTitle("eServis");
        primaryStage.show();

    }

    public void stop() {
        DbKonekcija.closeConnection();
    }
}