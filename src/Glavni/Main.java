package Glavni;
import Glavni.config.DbKonekcija;
import Glavni.controller.PocetniProzor;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        DbKonekcija.getConnection(); // Pozivanje singleton klase za konekciju

        // Pokrece JavaFX aplikaciju
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {


        PocetniProzor pocetak = new PocetniProzor(); // Kreira objekat klase PocetniProzor


        primaryStage.setScene(pocetak.getscene(primaryStage)); // Postavlja prvu scenu kao pocetni prozor i prikazuje ga
        primaryStage.setTitle("eServis");
        primaryStage.show();

    }
    public void stop() {
        DbKonekcija.closeConnection();
    } // Poziva metodu singleton klase za zatvaranje konekcije
}