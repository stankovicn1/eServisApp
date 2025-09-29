package Glavni;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

import static Glavni.UnosUBazu.unosServisa;

public class GlavniMeni {
    public Scene getscene3(Stage stage){  // Prozor za izbor jedne od opcija
        Button zaServisera = new Button("Servisi");

        Button unosServis = new Button("Novi unos");

        Button evidencija = new Button("Evidencija");

        Button zatvori = new Button("Izlaz");

        EvidencijaProzor novi = new EvidencijaProzor();
        ProzorZaUnos ppzu = new ProzorZaUnos();
        // Funkcionalnosti za button
        evidencija.setOnAction(e -> stage.setScene(novi.getSceneEvidencija(stage)));
        zaServisera.setOnAction(e -> otvoriProzorServisi());
        unosServis.setOnAction(e -> stage.setScene(ppzu.getscenaZaUnos(stage)));
        zatvori.setOnAction(e -> Platform.exit());

        VBox treciProzor = new VBox(35, zaServisera, unosServis, evidencija, zatvori);
        treciProzor.setAlignment(Pos.CENTER);

        Scene scene = new Scene(treciProzor, 700, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        return scene;
    }
    private TableView<Vozilo> tabelaServisa; // Deklaracija tabele

    private void otvoriProzorServisi() {
        // Inicijalizacija tabele
        tabelaServisa = new TableView<>(); // Kreira novu instancu TableView za prikaz podataka

        // Kreira listu koja ce cuvati podatke iz baze
        ObservableList<Vozilo> podaciIzBaze = FXCollections.observableArrayList();

        // Dobavljanje podataka iz baze
        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, model, registracija FROM vozila where naCekanju = 1")) { // SQL upit za dobijanje podataka o vozilima

            // Petlja kroz rezultate upita
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String registracija = rs.getString("registracija");

                // Kreira objekat vozilo sa ucitanim podacima
                Vozilo vozilo = new Vozilo(id, "", model, "", registracija,"", "",true);
                podaciIzBaze.add(vozilo); // Dodaje vozilo u listu podataka
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Kreiranje kolona za tabelu
        TableColumn<Vozilo, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vozilo, String> registracijaColumn = new TableColumn<>("Registracija");
        registracijaColumn.setCellValueFactory(new PropertyValueFactory<>("registracija"));

        // Dodavanje kolona u tabelu
        tabelaServisa.getColumns().addAll(modelColumn, registracijaColumn);
        tabelaServisa.setItems(podaciIzBaze);

        // Dodavanje detekcije duplog klika
        tabelaServisa.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Provera da li je kliknut dupli klik
                Vozilo selectedVozilo = tabelaServisa.getSelectionModel().getSelectedItem();
                if (selectedVozilo != null) {
                    otvoriProzorSaDetaljima(selectedVozilo, podaciIzBaze);
                }
            }
        });

        // Kreiranje prozora
        Stage stageServisera = new Stage();
        stageServisera.setTitle("Vozila na cekanju");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().add(tabelaServisa);

        Scene scene = new Scene(layout, 800, 400);
        stageServisera.setScene(scene);
        stageServisera.show();
    }
    private ObservableList<Vozilo> uklonjenaVozila = FXCollections.observableArrayList();

    // Metoda koja otvara novi prozor sa podacima o vozilu
    private void otvoriProzorSaDetaljima(Vozilo vozilo, ObservableList<Vozilo> podaciIzBaze) {
        Stage noviProzor = new Stage();
        noviProzor.setTitle("Detalji o vozilu");

        // Kreiranje labela za prikaz podataka
        Label modelLabel = new Label("Model: " + vozilo.getModel());
        Label registracijaLabel = new Label("Registracija: " + vozilo.getRegistracija());
        modelLabel.setFont(Font.font(18));
        registracijaLabel.setFont(Font.font(18));

        // Labela i TextArea za unos opisa
        Label opisLab = new Label("Unesite opis servisa");
        opisLab.setFont(Font.font(18));
        TextArea opiis = new TextArea();
        opiis.setPromptText("Unesite opis");
        opiis.setPrefWidth(200);
        opiis.setMaxWidth(200);
        opiis.setMinWidth(150);

        opiis.setPrefHeight(100);
        opiis.setMaxHeight(100);
        opiis.setMinHeight(75);

        // Dodavanje DatePicker-a za odabir datuma
        Label datumLab = new Label("Odaberite datum servisa");
        DatePicker datePicker = new DatePicker();  // Kreiranje DatePicker-a

        // Dugme za potvrdu
        Button potvrdiButton = new Button("Potvrdi");
        potvrdiButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px; -fx-text-fill: white; -fx-background-color: linear-gradient(to bottom, #40c6de, #2a9ebd); -fx-border-color: #1d7687; -fx-border-width: 2px; -fx-border-radius: 25px; -fx-background-radius: 25px; -fx-padding: 10px 20px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 5, 0.3, 0, 2);");


        potvrdiButton.setOnAction(e -> {
            String opis = opiis.getText();
            LocalDate datum = datePicker.getValue();

            if (datum != null && !opis.isEmpty()) {
                // Kreiraj Servis objekat sa unesenim podacima
                Servis servis = new Servis(0, opis, datum.toString(), vozilo.getId());

                // Pozivanje metode za unos servisa
                boolean uspeh = unosServisa(servis);
                if (uspeh) {
                    System.out.println("Uspešno ste uneli servis.");
                    // Uklonite vozilo iz trenutne liste podataka
                    podaciIzBaze.remove(vozilo);

                    // Azurira vrednost naCekanju u bazi na 0
                    try (Connection conn = DbKonekcija.getConnection()) {
                        String updateQuery = "UPDATE vozila SET naCekanju = 0 WHERE id = ?";
                        try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                            stmt.setInt(1, vozilo.getId());
                            stmt.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // Dohvati email iz tabele vozila
                   /* try (Connection conn = DbKonekcija.getConnection()) {
                        String emailQuery = "SELECT email FROM vozila WHERE id = ?";
                        try (PreparedStatement stmt = conn.prepareStatement(emailQuery)) {
                            stmt.setInt(1, vozilo.getId());
                            ResultSet rs = stmt.executeQuery();
                            if (rs.next()) {
                                String email = rs.getString("email");

                                // Pošaljite email ako je email dostupan
                                if (email != null && !email.isEmpty()) {
                                    String subject = "Potvrda obavljenog servisa za vozilo";
                                    String message = "Vaše vozilo sa ID: " + vozilo.getId() + " je servisirano. Možete ga preuzeti.\n"
                                            + "Opis servisa: " + opis + "\n"
                                            + "Datum: " + datum;

                                    try {
                                        EmailSender.sendEmail(email, subject, message);
                                        System.out.println("Poruka uspešno poslata na email: " + email);
                                    } catch (Exception ex) {
                                        System.out.println("Došlo je do greške prilikom slanja emaila: " + ex.getMessage());
                                    }
                                } else {
                                    System.out.println("Email nije dostupan za vozilo ID=" + vozilo.getId());
                                }
                            } else {
                                System.out.println("Email nije pronađen za vozilo ID=" + vozilo.getId());
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }*/

                    // Osveži tabelu da prikaze samo vozila sa naCekanju = 1
                    osveziTabelu(podaciIzBaze);

                    // Zatvori prozor nakon uspesnog unosa
                    noviProzor.close();
                } else {
                    System.out.println("Došlo je do greške pri unosu servisa.");
                }
            } else {
                System.out.println("Molimo unesite sve podatke.");
            }
        });







        // Dodavanje svih elemenata u layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(modelLabel, registracijaLabel, opisLab, opiis, datumLab, datePicker, potvrdiButton);

        // Kreiranje scene
        Scene scene = new Scene(layout, 800, 500);
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    private void osveziTabelu(ObservableList<Vozilo> podaciIzBaze) {

        // Ocistite trenutnu listu podataka
        podaciIzBaze.clear();

        // Ponovo ucitajte podatke iz baze, filtrirajući samo vozila sa naCekanju = 1
        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, model, registracija FROM vozila WHERE naCekanju = 1")) { // Samo vozila sa naCekanju = 1

            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String registracija = rs.getString("registracija");

                // Kreira objekat vozilo sa učitanim podacima
                Vozilo vozilo = new Vozilo(id, "", model, "", registracija, "", "", true);
                podaciIzBaze.add(vozilo); // Dodajte vozilo u listu
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}