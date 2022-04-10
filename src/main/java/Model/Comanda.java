package Model;

public class Comanda {
    private int id;
    private int clientID;
    private int produsID;
    private int cantitate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProdusID() {
        return produsID;
    }

    public void setProdusID(int produsID) {
        this.produsID = produsID;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Comanda(int id, int clientID, int produsID, int cantitate){
        this.id = id;
        this.clientID = clientID;
        this.produsID = produsID;
        this.cantitate = cantitate;
    }

    public Comanda(){

    }
}
