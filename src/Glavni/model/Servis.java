package Glavni.model;


public class Servis {
    private int idServis;
    private String opisServis;
    private String datum;
    private int vozilo_id;
    private String tipServisa;
    private int sledecaKilometraza;
    private double cena;

    public Servis(int idServis, String opisServis, String datum, int vozilo_id, String tipServisa, int sledecaKilometraza, double cena) {
        this.idServis = idServis;
        this.opisServis = opisServis;
        this.datum = datum;
        this.vozilo_id = vozilo_id;
        this.tipServisa = tipServisa;
        this. sledecaKilometraza = sledecaKilometraza;
        this.cena = cena;
    }

    public int getIdServis() {
        return idServis;
    }

    public String getOpisServis() {
        return opisServis;
    }

    public String getDatum() {
        return datum;
    }

    public int getVoziloId() {
        return vozilo_id;
    }
    public String getTipServisa(){return tipServisa;}
    public int getSledecaKilometraza(){return sledecaKilometraza;}
    public double getCena(){return cena;}

    public void setIdServis(int idServis) {
        this.idServis = idServis;
    }

    public void setOpisServis(String opisServis) {
        this.opisServis = opisServis;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setVoziloId(int vozilo_id) {
        this.vozilo_id = vozilo_id;
    }
    public void setTipServisa(String tipServisa){this.tipServisa = tipServisa;}
    public void setSledecaKilometraza(int sledecaKilometraza){this.sledecaKilometraza = sledecaKilometraza;}
    public void setCena(double cena){this.cena = cena;}
}