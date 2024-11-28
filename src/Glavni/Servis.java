package Glavni;

public class Servis {

    // Konstruktor se poziva prilikom kreiranja novog objekta klase Servis, prima dva parametra i inicijalizuje privatne atribute klase sa prosledjenim vrednostima
    private String datumServisa;
    private String opis;

    // Konstruktor i geteri
    public Servis(String datumServisa, String opis){
        this.datumServisa = datumServisa;
        this.opis = opis;
    }
    public String getDatumServisa(){
        return datumServisa;
    }
    public String getOpis(){
        return opis;
    }
}
