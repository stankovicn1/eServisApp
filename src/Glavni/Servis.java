package Glavni;

public class Servis {

    private String datumServisa;
    private String opis;

    // Konstruktor, geteri i seteri
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
