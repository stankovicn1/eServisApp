package Glavni;

import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

public class Pretraga {

    public static void inicijalizujPretragu(TextField pretragaField, FilteredList<Vozilo> filtriraniPodaci, TableView<Vozilo> tabelaEvidencija) {
        pretragaField.setPromptText("Pretraži po bilo kom polju");

        pretragaField.textProperty().addListener((observable, oldValue, newValue) -> {
            filtriraniPodaci.setPredicate(vozilo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Prikaz svih podataka ako polje za pretragu nije popunjeno
                }
                String filter = newValue.toLowerCase();
                return vozilo.getKlasa().toLowerCase().contains(filter) ||
                        vozilo.getModel().toLowerCase().contains(filter) ||
                        vozilo.getGodiste().toLowerCase().contains(filter) ||
                        vozilo.getEmail().toLowerCase().contains(filter) ||
                        vozilo.getRegistracija().toLowerCase().contains(filter);
            });
        });

        // Povezivanje filtriranih podataka sa tabelom
        tabelaEvidencija.setItems(filtriraniPodaci);
    }
}