package Glavni;

public class Vozilo {

    // Privatni atributi
    private String klasa;
    private String model;
    private String godiste;
    private String registracija;
    private String opis;
    private String email;
    private String datum; // Pretvoren u String za lakšu obradu datuma

    // Konstruktor za inicijalizaciju
    public Vozilo(String klasa, String model, String godiste, String registracija, String opis, String email, String datum) {
        this.klasa = klasa;
        this.model = model;
        this.godiste = godiste;
        this.registracija = registracija;
        this.opis = opis;
        this.email = email;
        this.datum = datum;
    }

    // Getteri
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

    public String getOpis() {
        return opis;
    }

    public String getEmail() {
        return email;
    }

    public String getDatum() {
        return datum;
    }

    // Setteri
    public void setMarka(String klasa) {
        this.klasa = klasa;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setGodiste(String godiste) {
        this.godiste = godiste;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}