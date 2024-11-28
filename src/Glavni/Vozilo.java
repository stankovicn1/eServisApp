package Glavni;

public class Vozilo {

    // Privatni atributi sto znaci da im se moze pristupiti samo unutar klase vozilo, princip Enkapsulacije stiti podatke od neovlascenih izmena spolja
    private String marka;
    private String model;
    private String godiste;
    private String registracija;

    // Konstruktor se poziva prilikom kreiranja novog objekta klase Vozilo, prima cetiri parametra i inicijalizuje privatne atribute klase sa prosledjenim vrednostima

    public Vozilo(String marka, String model, String godiste, String registracija){
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.registracija = registracija;
    }
    // Getteri omogucavaju pristup privatnim atributima klase
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
