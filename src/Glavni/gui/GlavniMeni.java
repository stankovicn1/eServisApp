package Glavni.gui;
import Glavni.model.Servis;
import Glavni.model.Vozilo;
import Glavni.db.DbKonekcija;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import static Glavni.service.UnosUBazu.unosServisa;
import java.util.List;
import java.util.Arrays;

public class GlavniMeni {
    public Scene getscene3(Stage stage) {
        Button zaServisera = new Button("Servisi na cekanju");

        Button unosServis = new Button("Novi unos");

        Button evidencija = new Button("Evidencija");

        Button zatvori = new Button("Izlaz");

        EvidencijaProzor novi = new EvidencijaProzor();
        ProzorZaUnos ppzu = new ProzorZaUnos();

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
    private TableView<Vozilo> tabelaServisa;

    private void otvoriProzorServisi() {

        tabelaServisa = new TableView<>();
        ObservableList<Vozilo> podaciIzBaze = FXCollections.observableArrayList();

        try (Connection conn = DbKonekcija.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, model, registracija FROM vozila where naCekanju = 1")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String registracija = rs.getString("registracija");

                Vozilo vozilo = new Vozilo(id, "", model, "", registracija, "", "", true);
                podaciIzBaze.add(vozilo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        TableColumn<Vozilo, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vozilo, String> registracijaColumn = new TableColumn<>("Registracija");
        registracijaColumn.setCellValueFactory(new PropertyValueFactory<>("registracija"));

        tabelaServisa.getColumns().addAll(modelColumn, registracijaColumn);
        tabelaServisa.setItems(podaciIzBaze);

        tabelaServisa.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Vozilo selectedVozilo = tabelaServisa.getSelectionModel().getSelectedItem();
                if (selectedVozilo != null) {
                    otvoriProzorSaDetaljima(selectedVozilo, podaciIzBaze);
                }
            }
        });

        Stage stageServisera = new Stage();
        stageServisera.setTitle("Vozila na cekanju");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().add(tabelaServisa);

        Scene scene = new Scene(layout, 800, 400);
        stageServisera.setScene(scene);
        stageServisera.show();
    }
    //private final ObservableList<Vozilo> uklonjenaVozila = FXCollections.observableArrayList();
    private void otvoriProzorSaDetaljima(Vozilo vozilo, ObservableList<Vozilo> podaciIzBaze) {
        Stage noviProzor = new Stage();
        noviProzor.setTitle("Detalji o vozilu");

        Label modelLabel = new Label("Model: " + vozilo.getModel());
        Label registracijaLabel = new Label("Registracija: " + vozilo.getRegistracija());
        modelLabel.setFont(Font.font(18));
        registracijaLabel.setFont(Font.font(18));

        Label opisLab = new Label("Unesite opis servisa");
        opisLab.setFont(Font.font(16));

        TextArea opiis = new TextArea();
        opiis.setPromptText("Unesite opis");
        opiis.setPrefWidth(150);
        opiis.setPrefHeight(70);
        opiis.setWrapText(true);

        CheckBox maliServis = new CheckBox("Mali servis");
        CheckBox velikiServis = new CheckBox("Veliki servis");
        CheckBox ostaloServis = new CheckBox("Ostalo");

        List<CheckBox> sviCheckBoxovi = Arrays.asList(maliServis, velikiServis, ostaloServis);
        for (CheckBox cb : sviCheckBoxovi) {
            cb.setOnAction(e -> {
                if (cb.isSelected()) {
                    for (CheckBox drugi : sviCheckBoxovi) {
                        if (drugi != cb) drugi.setSelected(false);
                    }
                }
            });
        }

        VBox checkboxBox = new VBox(5, maliServis, velikiServis, ostaloServis);
        checkboxBox.setAlignment(Pos.TOP_LEFT);

        HBox opisIcheckboxBox = new HBox(20, opiis, checkboxBox);
        opisIcheckboxBox.setAlignment(Pos.CENTER_LEFT);

        Label datumLab = new Label("Odaberite datum servisa");
        DatePicker datePicker = new DatePicker();

        Label kmLab = new Label("Kilometraža za sledeći servis:");
        TextField kmField = new TextField();
        kmField.setPromptText("npr. 150000");
        //kmField.setPrefWidth(120);

        Label cenaLab = new Label("Cena servisa (RSD):");
        TextField cenaField = new TextField();
        cenaField.setPromptText("npr. 10000");
        //cenaField.setPrefWidth(120);

        VBox kmCenaBox = new VBox(20, kmLab, kmField, cenaLab, cenaField);
        kmCenaBox.setAlignment(Pos.CENTER_LEFT);

        Button potvrdiButton = new Button("Potvrdi");
        potvrdiButton.setOnAction(e -> {
            String opis = opiis.getText();
            LocalDate datum = datePicker.getValue();
            String kilometraza = kmField.getText();
            String cena = cenaField.getText();

            String tipServisa = null;
            if (maliServis.isSelected()) tipServisa = "Mali servis";
            else if (velikiServis.isSelected()) tipServisa = "Veliki servis";
            else if (ostaloServis.isSelected()) tipServisa = "Ostalo";

            if (datum != null && !opis.isEmpty() && tipServisa != null && !kilometraza.isEmpty() && !cena.isEmpty()) {
                try {
                    Servis servis = new Servis(
                            0,
                            opis,
                            datum.toString(),
                            vozilo.getId(),
                            tipServisa,
                            Integer.parseInt(kilometraza),
                            Double.parseDouble(cena)
                    );

                    boolean uspeh = unosServisa(servis);
                    if (uspeh) {
                        System.out.println("Uspešno ste uneli servis: " + tipServisa
                                + " | Cena: " + cena + " | Sledeći servis na: " + kilometraza + " km");

                        podaciIzBaze.remove(vozilo);

                        try (Connection conn = DbKonekcija.getConnection()) {
                            String updateQuery = "UPDATE vozila SET naCekanju = 0 WHERE id = ?";
                            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                                stmt.setInt(1, vozilo.getId());
                                stmt.executeUpdate();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        osveziTabelu(podaciIzBaze);
                        noviProzor.close();
                    } else {
                        System.out.println("Došlo je do greške pri unosu servisa.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Molimo unesite validne brojeve za kilometražu i cenu.");
                }
            } else {
                System.out.println("Molimo unesite sve podatke i odaberite tip servisa.");
            }
        });

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                modelLabel, registracijaLabel,
                opisLab, opisIcheckboxBox,
                datumLab, datePicker,
                kmCenaBox,
                potvrdiButton
        );

        Scene scene = new Scene(layout, 800, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    private void osveziTabelu(ObservableList<Vozilo> podaciIzBaze) {
    }


}
