package Glavni;

public class Vozilo {

    private String marka;
    private String model;
    private String godiste;
    private String registracija;

    // Konstruktor, geteri i seteri

    public Vozilo(String marka, String model, String godiste, String registracija){
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.registracija = registracija;
    }
    public String getModel(){
        return model;
    }
    public String getMarka(){
        return marka;
    }
    public String getGodiste(){
        return godiste;
    }
    public String getRegistracija(){
        return registracija;
    }



}
