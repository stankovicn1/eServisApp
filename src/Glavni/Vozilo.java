
package Glavni;

public class Vozilo {

    // Privatni atributi
    private int id; // Jedinstveni identifikator
    private String klasa;
    private String model;
    private String godiste;
    private String registracija;
    private String kilometraza;

    private String email;


    // Konstruktor za inicijalizaciju sa ID-jem
    public Vozilo(int id, String klasa, String model, String godiste, String registracija,String kilometraza, String email) {
        this.id = id;
        this.klasa = klasa;
        this.model = model;
        this.godiste = godiste;
        this.registracija = registracija;
        this.kilometraza = kilometraza;
        this.email = email;


    }

    // Getteri
    public int getId() {
        return id;
    }

    public String getKlasa() {
        return klasa;
    }

    public String getModel() {
        return model;
    }

    public String getGodiste() {
        return godiste;
    }

    public String getRegistracija() {
        return registracija;
    }

    public String getKilometraza(){
        return kilometraza;
    }

    public String getEmail() {
        return email;
    }


    // Setteri
    public void setId(int id) {
        this.id = id;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setGodiste(String godiste) {
        this.godiste = godiste;
    }

    public void setKilometraza(String kilometraza){
        this.kilometraza = kilometraza;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }



    public void setEmail(String email) {
        this.email = email;
    }

}
