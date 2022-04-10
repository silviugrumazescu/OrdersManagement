package Model;

public class Produs {

    private int id;
    private String nume;
    private int pret;
    private String descriere;
    private int cantitate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Produs(int id, String nume, int pret, String descriere, int cantitate){
        this.id = id;
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Produs(){
    }
}
