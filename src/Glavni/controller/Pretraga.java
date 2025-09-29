/*
package Glavni;

import Glavni.model.VoziloServis;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

public class Pretraga {

    // Generička metoda koja radi sa bilo kojim tipom objekta
    public static <T> void inicijalizujPretragu(TextField pretragaField, FilteredList<T> filtriraniPodaci, TableView<T> tabelaEvidencija) {
        pretragaField.setPromptText("Pretraži po bilo kom polju");

        pretragaField.textProperty().addListener((observable, oldValue, newValue) -> {
            filtriraniPodaci.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Prikaz svih podataka ako polje za pretragu nije popunjeno
                }
                String filter = newValue.toLowerCase();

                // Prilagoditi za svaki objekat u zavisnosti od tipa
                if (item instanceof VoziloServis) {
                    VoziloServis vozilo = (VoziloServis) item;
                    return vozilo.getKlasa().toLowerCase().contains(filter) ||
                            vozilo.getModel().toLowerCase().contains(filter) ||
                            vozilo.getGodiste().toLowerCase().contains(filter) ||
                            vozilo.getEmail().toLowerCase().contains(filter) ||
                            vozilo.getRegistracija().toLowerCase().contains(filter) ||
                            vozilo.getKilometraza().toLowerCase().contains(filter) ||
                            vozilo.getOpisServis().toLowerCase().contains(filter);
                }
                return false;
            });
        });

        // Povezivanje filtriranih podataka sa tabelom
        tabelaEvidencija.setItems(filtriraniPodaci);
    }
}
*/
