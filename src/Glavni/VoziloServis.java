package Glavni;

public class VoziloServis{
    private int id;
    private String klasa;
    private String model;
    private String godiste;
    private String registracija;
    private String kilometraza;
    private String email;
    private String opisServis;

    // Konstruktor
    public VoziloServis(int id, String klasa, String model, String godiste, String registracija, String kilometraza, String email, String opisServis) {
        this.id = id;
        this.klasa = klasa;
        this.model = model;
        this.godiste = godiste;
        this.registracija = registracija;
        this.kilometraza = kilometraza;
        this.email = email;
        this.opisServis = opisServis;
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

    public String getKilometraza() {
        return kilometraza;
    }

    public String getEmail() {
        return email;
    }

    public String getOpisServis() {
        return opisServis;
    }
}
