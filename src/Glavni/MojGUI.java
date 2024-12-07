package Glavni;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MojGUI {
    public Scene getscene(Stage stage){
        // Prvi prozor , pocetni prozor sa naslovom i dugmetom koje vodi na prozor za prijavu

        Label label = new Label("eServisnaKnjizica"); // Kreira labelu
        label.setFont(new Font("Arial", 55)); // Postavlja font i velicinu

        Button dugme = new Button("Prijavi se"); // Kreira dugme koje vodi na stranicu za prijavu
        dugme.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; "); // Stil dugmeta
        dugme.setOnAction(e -> stage.setScene(getscene2(stage))); // Postavlja prelaz na prozor za prijavu klikom na dugme
        VBox vbox = new VBox(35, label, dugme); // Kreira VBox i prosledjuje razmak izmedju elemenata i elemente
        vbox.setAlignment(Pos.CENTER); // Pozicionira vBox na centar
        return new Scene(vbox, 700, 400); // vraca Scenu

    }

    public Scene getscene2(Stage stage){
        // Drugi prozor za prijavu
        Label labeldvojka = new Label("Unesi lozinku");
        labeldvojka.setFont(new Font("Arial", 35));

        PasswordField lozinka = new PasswordField(); // Polje za unos lozinke
        lozinka.setPromptText("Unesite lozinku");

        Button dugme2 = new Button("Nazad");
        dugme2.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button dugmePrijava = new Button("Potvrdi");
        dugmePrijava.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");


        ProveraLozinke proveraLozinke = new ProveraLozinke(); // Kreira instancu klase ProveraLozinke, da bi pozvali metodu iste klase dalje u nastavku


        dugmePrijava.setOnAction(e -> { // Postavljamo akciju za dugme za prijavu koje treba da proveri da li je uneta lozinka ista kao i lozinka iz tabele korisnici u bazi
            String unesenaLozinka = lozinka.getText(); // Uzima unesenu lozinku iz polja


            if (proveraLozinke.checkPassword(unesenaLozinka)) { // Pozivanje metode za proveru lozinke

                stage.setScene(getscene3(stage)); // Ako je lozinka tacna, prelazak na sledecu scenu
            } else {

                prikaziPoruku("Greška", "Pogrešna lozinka."); // Ako je lozinka pogresna, prikazuje poruku o gresci
            }
        });


        dugme2.setOnAction(e -> stage.setScene(getscene(stage))); // Dugme nazad koje prelazi na prvi prozor


        VBox vBoxdvojka = new VBox(35, labeldvojka, lozinka, dugmePrijava, dugme2);
        vBoxdvojka.setAlignment(Pos.CENTER);
        return new Scene(vBoxdvojka, 700, 400);

    }

    private void prikaziPoruku(String naslov, String poruka) { // Metoda za prikaz poruke tipa Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }


    public Scene getscene3(Stage stage){
        // Treci prozor sa opcijama za Novi unos i Evidenciju kao i za zatvaranje aplikacije
        Button zaServisera = new Button("Servisi");
        zaServisera.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");


        Button unosServis = new Button("Novi unos");
        unosServis.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button evidencija = new Button("Evidencija");
        evidencija.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button zatvori = new Button("Izlaz");
        zatvori.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        unosServis.setOnAction(e -> stage.setScene(getscenaZaUnos(stage)));
        zatvori.setOnAction(e -> Platform.exit()); // Zatvara aplikaciju metoda exit()

        VBox treciProzor = new VBox(35,zaServisera,unosServis,evidencija, zatvori);
        treciProzor.setAlignment(Pos.CENTER);
        return new Scene(treciProzor, 700,400);

    }
    public Scene getscenaZaUnos(Stage stage){
        //Prozor za Novi unos

        // Label za marku automobila
        Label markaL = new Label("Izaberite klasu");
        markaL.setFont(Font.font(18));

// ComboBox za marku automobila
        ComboBox<String> marka = new ComboBox<>();
        marka.getItems().addAll("A", "B", "C", "E", "S", "G","CLA","CLS","GLA","GLB","GLC", "GLE", "GLS");
        marka.setPromptText("Izaberite klasu automobila");

// Label za model automobila
        Label modelL = new Label("Izaberite model");
        modelL.setFont(Font.font(18));

// ComboBox za model automobila
        ComboBox<String> model = new ComboBox<>();
        model.setPromptText("Izaberite model automobila");

// Dodavanje primera modela u zavisnosti od marke
        marka.setOnAction(e -> {
            model.getItems().clear(); // Očisti prethodne opcije
            String selectedBrand = marka.getValue();
            if ("A".equals(selectedBrand)) {
                model.getItems().addAll("A 140");
                model.getItems().addAll("A 150");
                model.getItems().addAll("A 160");
                model.getItems().addAll("A 170");
                model.getItems().addAll("A 180");
                model.getItems().addAll("A 190");
                model.getItems().addAll("A 200");
                model.getItems().addAll("A 210");
                model.getItems().addAll("A 220");
                model.getItems().addAll("A 250");
                model.getItems().addAll("A 35 AMG");
                model.getItems().addAll("A 45 AMG");
            } else if ("B".equals(selectedBrand)) {
                model.getItems().addAll("B 150");
                model.getItems().addAll("B 160");
                model.getItems().addAll("B 170");
                model.getItems().addAll("B 180");
                model.getItems().addAll("B 200");
                model.getItems().addAll("B 220");
                model.getItems().addAll("B 250");
            } else if ("C".equals(selectedBrand)) {
                model.getItems().addAll("C 180");
                model.getItems().addAll("C 200");
                model.getItems().addAll("C 220");
                model.getItems().addAll("C 230");
                model.getItems().addAll("C 240");
                model.getItems().addAll("C 250");
                model.getItems().addAll("C 270");
                model.getItems().addAll("C 280");
                model.getItems().addAll("C 30 AMG");
                model.getItems().addAll("C 300");
                model.getItems().addAll("C 320");
                model.getItems().addAll("C 32 AMG");
                model.getItems().addAll("C 43 AMG");
                model.getItems().addAll("C 63 AMG");
            } else if ("E".equals(selectedBrand)) {
                model.getItems().addAll("E 200");
                model.getItems().addAll("E 220");
                model.getItems().addAll("E 230");
                model.getItems().addAll("E 240");
                model.getItems().addAll("E 250");
                model.getItems().addAll("E 260");
                model.getItems().addAll("E 270");
                model.getItems().addAll("E 280");
                model.getItems().addAll("E 290");
                model.getItems().addAll("E 300");
                model.getItems().addAll("E 320");
                model.getItems().addAll("E 350");
                model.getItems().addAll("E 400");
                model.getItems().addAll("E 43 AMG");
                model.getItems().addAll("E 450");
                model.getItems().addAll("E 500");
                model.getItems().addAll("E 53 AMG");
                model.getItems().addAll("E 63 AMG");
            } else if ("S".equals(selectedBrand)) {
                model.getItems().addAll("S 250");
                model.getItems().addAll("S 280");
                model.getItems().addAll("S 300");
                model.getItems().addAll("S 320");
                model.getItems().addAll("S 350");
                model.getItems().addAll("S 400");
                model.getItems().addAll("S 420");
                model.getItems().addAll("S 450");
                model.getItems().addAll("S 500");
                model.getItems().addAll("S 550");
                model.getItems().addAll("S 580");
                model.getItems().addAll("S 63 AMG");
            } else if ("G".equals(selectedBrand)) {
                model.getItems().addAll("G 290");
                model.getItems().addAll("G 300");
                model.getItems().addAll("G 320");
                model.getItems().addAll("G 350");
                model.getItems().addAll("G 400");
                model.getItems().addAll("G 450");
                model.getItems().addAll("G 500");
                model.getItems().addAll("G 63 AMG");
            } else if ("CLA".equals(selectedBrand)) {
                model.getItems().addAll("CLA 180");
                model.getItems().addAll("CLA 200");
                model.getItems().addAll("CLA 220");
                model.getItems().addAll("CLA 250");
                model.getItems().addAll("CLA 35 AMG");
                model.getItems().addAll("CLA 45 AMG");

            } else if ("CLS".equals(selectedBrand)) {
                model.getItems().addAll("CLS 220");
                model.getItems().addAll("CLS 250");
                model.getItems().addAll("CLS 300");
                model.getItems().addAll("CLS 320");
                model.getItems().addAll("CLS 350");
                model.getItems().addAll("CLS 400");
                model.getItems().addAll("CLS 450");
                model.getItems().addAll("CLS 500");
                model.getItems().addAll("CLS 53 AMG");
                model.getItems().addAll("CLS 63 AMG");
            } else if ("GLA".equals(selectedBrand)) {
                model.getItems().addAll("GLA 35 AMG");
                model.getItems().addAll("GLA 45 AMG");
                model.getItems().addAll("GLA 180");
                model.getItems().addAll("GLA 200");
                model.getItems().addAll("GLA 220");
                model.getItems().addAll("GLA 250");
            } else if ("GLB".equals(selectedBrand)) {
                model.getItems().addAll("GLB 180");
                model.getItems().addAll("GLB 200");
                model.getItems().addAll("GLB 220");
            } else if ("GLC".equals(selectedBrand)) {
                model.getItems().addAll("GLC 43 AMG");
                model.getItems().addAll("GLC 200");
                model.getItems().addAll("GLC 220");
                model.getItems().addAll("GLC 250");
                model.getItems().addAll("GLC 300");
                model.getItems().addAll("GLC 350");
            } else if ("GLE".equals(selectedBrand)) {
                model.getItems().addAll("GLE 43 AMG");
                model.getItems().addAll("GLE 53 AMG");
                model.getItems().addAll("GLE 63 AMG");
                model.getItems().addAll("GLE 250");
                model.getItems().addAll("GLE 300");
                model.getItems().addAll("GLE 350");
                model.getItems().addAll("GLE 400");
                model.getItems().addAll("GLE 450");
            } else if ("GLS".equals(selectedBrand)) {
                model.getItems().addAll("GLS 63 AMG");
                model.getItems().addAll("GLS 400");
                model.getItems().addAll("GLS 450");
                model.getItems().addAll("GLS 580");

        }
        });

        Label godisteL = new Label("Unesite godiste");
        godisteL.setFont(Font.font(18));
        TextField godiste = new TextField();
        godiste.setPromptText("Unesite godiste");

        godiste.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                godiste.setText(oldValue);
            }
        });

        Label registracijaL = new Label("Unesite registarske oznake");
        registracijaL.setFont(Font.font(18));
        TextField registracija = new TextField();;
        registracija.setPromptText("Unesite registarske oznake");

        Label opisL = new Label("Unesite opis");
        opisL.setFont(Font.font(18));
        TextArea opis = new TextArea();
        opis.setPromptText("Unesite opis servisa");

        Label emailL = new Label("Unesite email");
        emailL.setFont(Font.font(18));
        TextArea email = new TextArea();
        email.setPromptText("Unesite email");



        Label datumL = new Label("Izaberite datum");
        datumL.setFont(Font.font(18));
        DatePicker datum = new DatePicker();
        datum.setPromptText("Izaberite datum");

        Button nazad = new Button("Nazad");
        nazad.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        Button unos = new Button("Potrvdi");
        unos.setStyle("-fx-font: 18 arial; -fx-backround-color:#40c6de; -fx-text-fill: black; -fx-background-radius: 50px; -fx-padding: 10px 20px; ");

        nazad.setOnAction(e -> stage.setScene(getscene3(stage)));
        unos.setOnAction(e ->{
            try {
                String izabranaKlasa = marka.getValue();
                String izabraniModel = model.getValue();
                int unesenoGodiste = Integer.parseInt(godiste.getText());
                String unesenaRegistracija = registracija.getText();
                String uneseniEmail = email.getText();
                String uneseniOpis = opis.getText();
                String izabranDatum = datum.getValue().toString(); // Pretvara LocalDate u String

                // Proveri da li su svi obavezni podaci uneti
                if (izabranaKlasa == null || izabraniModel == null || unesenaRegistracija.isEmpty() || uneseniEmail.isEmpty() || izabranDatum == null) {
                    prikaziPoruku("Greška", "Molimo popunite sva polja.");
                    return;

        };
                UnosUBazu vozilaDAO = new UnosUBazu();
                UnosUBazu.dodajVozilo(izabranaKlasa, izabraniModel, unesenoGodiste, unesenaRegistracija, uneseniEmail, uneseniOpis, izabranDatum);

                prikaziPoruku("Uspeh", "Podaci su uspešno dodati u bazu.");
                stage.setScene(getscene3(stage)); // Povratak na prethodnu scenu

            } catch (NumberFormatException ex) {
                prikaziPoruku("Greška", "Godiste mora biti broj.");
            }
        });

        VBox noviUnos = new VBox(10,  markaL,marka, modelL, model, godisteL, godiste, registracijaL,registracija, datumL,datum, opisL, opis,emailL,email, unos, nazad);
        noviUnos.setAlignment(Pos.CENTER);
        return new Scene(noviUnos, 1100, 900);

    }
}