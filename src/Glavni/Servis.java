package Glavni;



public class Servis {
    private int idServis;
    private String opisServis;
    private String datum;
    private int vozilo_id;

    // Konstruktor
    public Servis(int idServis, String opisServis, String datum, int vozilo_id) {
        this.idServis = idServis;
        this.opisServis = opisServis;
        this.datum = datum;
        this.vozilo_id = vozilo_id;
    }

    // Getteri i setteri
    public int getIdServis() {
        return idServis;
    }
    public String getOpisServis() {
        return opisServis;
    }
    public String getDatum() {
        return datum;
    }
    public int getVoziloId(){
        return vozilo_id;
    }
    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }
    public void setOpisServis(String opisServis) {
        this.opisServis = opisServis;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }
    public void setVoziloId(int vozilo_id){
        this.vozilo_id = vozilo_id;
    }
}