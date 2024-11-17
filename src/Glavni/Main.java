package Glavni;
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

        MojGUI mojGUI = new MojGUI(); // Kreira objekat klase MojGUI

        primaryStage.setScene(mojGUI.getscene(primaryStage)); // Postavlja prvu scenu kao pocetni prozor i prikazuje ga
        primaryStage.setTitle("eServis");
        primaryStage.show();

    }
    public void stop() {
        DbKonekcija.closeConnection();
    } // Poziva metodu singleton klase za zatvaranje konekcije
}